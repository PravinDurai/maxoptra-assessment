package com.maxoptra.application.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.maxoptra.application.dto.CardDto;
import com.maxoptra.application.model.CardModel;
import com.maxoptra.application.model.LoginModel;
import com.maxoptra.application.service.AppService;

@Controller
public class AppController {
	
	@Autowired
	AppService appService;
	
	private HttpSession session;
	
	
//	private HttpSessionListener sessionListener;
	
	@GetMapping(value="/auth/login")
	public String loginGetMapping(@ModelAttribute("Login") LoginModel login, HttpServletRequest request, Model model) {
		session=request.getSession();
//		sessionListener.sessionCreated(new HttpSessionEvent(session));
		List<CardDto> cardList=new ArrayList<CardDto>();
		Map<Long, CardDto> cardMap=new TreeMap<Long, CardDto>();
		
		
//		model.addAttribute("cardList",cardList);
		session.setAttribute("cardList", cardList);
		session.setAttribute("cardMap", cardMap);
		session.setMaxInactiveInterval(240);
		return("login");
	}
	
	@PostMapping(value="/auth/user")
	public String login(@ModelAttribute("Login") LoginModel login, Model model) {
		if(login.geteMail().equals("guest")&&login.getPassword().equals("Password@1234")) {
			model.addAttribute("Card",new CardModel());
			model.addAttribute("eMail",login.geteMail());
			session.setAttribute("eMail", login.geteMail());
			//List<CardDto> cardList=(List<CardDto>) session.getAttribute("cardList");
			//session.setAttribute("cardList", cardList);
			return("card");
		}else {
			model.addAttribute("Login",new LoginModel());
			return("redirect:/auth/login");
		}	
	}
	
	@GetMapping(value="/display")
	public String display(@ModelAttribute("Card") CardModel card) {
		return("card");
	}
	
	@PostMapping(value="/add/card")
	public String addCard(@ModelAttribute("Card") CardModel card,Model model) {
		ModelMapper mapper=new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		CardDto c1=mapper.map(card, CardDto.class);
		List<CardDto> cardList=(List<CardDto>) session.getAttribute("cardList");
		Map<Long,CardDto> cardMap=(Map<Long, CardDto>) session.getAttribute("cardMap");
		
		c1.setEncCardNumber(appService.convertCardNumber(c1.getCardNumber()));
		Date date=new Date();
		date.setMonth(Integer.parseInt(c1.getExpiryMonth()));
		date.setYear(Integer.parseInt(c1.getExpiryYear()));
		
		c1.setExpiryDate(appService.getOutputExpiryDate(date));
		cardList.add(c1);
		cardMap.put(date.getTime(), c1);
		cardList.forEach(temp->{System.out.println(temp.toString());});
		System.out.println("Printing the value using map");
		for(Long temp:cardMap.keySet()) {
			System.out.println(cardMap.get(temp).toString());
		}
		return("redirect:/display");
	}
	
	@GetMapping(value="/logout")
	public String logout(Model model) {
		model.addAttribute("Login", new LoginModel());
		
		return("login");
	}
	
}
