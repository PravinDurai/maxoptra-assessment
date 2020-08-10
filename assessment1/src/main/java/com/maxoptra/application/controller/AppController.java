package com.maxoptra.application.controller;

import static org.assertj.core.api.Assertions.not;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.maxoptra.application.dto.CSVFileDto;
import com.maxoptra.application.dto.CardDto;
import com.maxoptra.application.model.CardModel;
import com.maxoptra.application.model.FileUploadModel;
import com.maxoptra.application.model.LoginModel;
import com.maxoptra.application.service.AppService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Controller
public class AppController {

	@Autowired
	AppService appService;

	private HttpSession session;

	private boolean nofile = false;

	@GetMapping(value = "/auth/login")
	public String loginGetMapping(@ModelAttribute("Login") LoginModel login, HttpServletRequest request, Model model) {
		session = request.getSession();
		List<CardDto> cardList = new ArrayList<CardDto>();
		Map<Long, CardDto> cardMap = new TreeMap<Long, CardDto>();
		session.setAttribute("cardList", cardList);
		session.setAttribute("cardMap", cardMap);
		session.setMaxInactiveInterval(240);
		return ("login");
	}

	@PostMapping(value = "/auth/user")
	public String login(@ModelAttribute("Login") LoginModel login, Model model) {
		if (login.geteMail().equals("guest") && login.getPassword().equals("Password@1234")) {
			model.addAttribute("Card", new CardModel());
			model.addAttribute("File", new FileUploadModel());
			model.addAttribute("eMail", login.geteMail());
			session.setAttribute("eMail", login.geteMail());
			model.addAttribute("message", "Upload a file that contains Card information");
			model.addAttribute("status", true);
			session.setAttribute("uploadError", true);

			return ("card");
		} else {
			model.addAttribute("Login", new LoginModel());
			return ("redirect:/auth/login");
		}
	}

	@GetMapping(value = "/display")
	public String display(@ModelAttribute("Card") CardModel card, Model model) {
		model.addAttribute("message", "Upload a file that contains Card information");
		model.addAttribute("status", true);
		if (nofile) {
			nofile=false;
			session.setAttribute("uploadError", true);
			session.setAttribute("message", "Please upload a file that has data");
		}
		return ("card");
	}

	@PostMapping(value = "/add/card")
	public String addCard(@ModelAttribute("Card") CardModel card, Model model) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		CardDto c1 = mapper.map(card, CardDto.class);

		Map<Long, CardDto> cardMap = (Map<Long, CardDto>) session.getAttribute("cardMap");

		c1.setEncCardNumber(appService.convertCardNumber(c1.getCardNumber()));
		Date date = new Date();
		date.setMonth(Integer.parseInt(c1.getExpiryMonth()));
		date.setYear(Integer.parseInt(c1.getExpiryYear()));
		c1.setExpiryDate(appService.getOutputExpiryDate(date));
		cardMap.put(date.getTime(), c1);

		model.addAttribute("message", "Upload a file that contains Card information");
		model.addAttribute("status", true);

		return ("redirect:/display");
	}

	@PostMapping(value = "/file/upload")
	public String fileUpload(@RequestParam("file") MultipartFile file, Model model) {
		Map<Long, CardDto> cardMap = (Map<Long, CardDto>) session.getAttribute("cardMap");
		if (file.isEmpty()) {
			session.setAttribute("uploadError", nofile);
			session.setAttribute("message", "Please upload a file that has data");
		} else {
			nofile=true;
			session.setAttribute("uploadError", true);
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
//				CsvToBean<CSVFileDto> csvToBean=new CsvToBeanBuilder(reader)
//						.withType(CSVFileDto.class)
//						.withIgnoreLeadingWhiteSpace(true)
//						.build();
				String line = "";
				String tempArr[];
				int i = 0;
				while ((line = reader.readLine()) != null) {
					if (i++ == 0)
						continue;
					tempArr = line.split(",");
					CardDto csvFileDto = new CardDto();
					csvFileDto.setBankName(tempArr[0]);
					csvFileDto.setCardNumber(tempArr[1]);
					csvFileDto.setEncCardNumber(appService.convertCardNumber(tempArr[1]));
					csvFileDto.setExpiryDate(tempArr[2]);
					Long time = appService.getExpiryTimeInMillSec(tempArr[2]);
					cardMap.put(time, csvFileDto);
				}
				List<CardDto> invalidRecords = appService.validateCardinFile(cardMap);
				invalidRecords.forEach(temp -> {
					temp.toString();
				});

				model.addAttribute("status", true);
				if (invalidRecords == null || invalidRecords.isEmpty()) {
					session.setAttribute("invalidDataStatus", true);
					session.setAttribute("invalidData", true);
				} else {
					session.setAttribute("invalidDataStatus", false);
					session.setAttribute("invalidData", false);
				}

			} catch (Exception exc) {
				model.addAttribute("message", "Error occured while processing the CSV File");
				model.addAttribute("status", false);
				session.setAttribute("uploadError", false);
				session.setAttribute("message", "Please upload a file that has data");
			}
		}
		return ("redirect:/display");
	}

	@GetMapping(value = "/logout")
	public String logout(Model model) {
		model.addAttribute("Login", new LoginModel());
		return ("login");
	}

}
