package com.maxoptra.application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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

@Controller
public class AppController {
	
	private HttpSession session;
	
	@GetMapping(value="/auth/login")
	public String loginGetMapping(@ModelAttribute("Login") LoginModel login, HttpServletRequest request) {
		session=request.getSession();
		List<CardDto> cardList=new ArrayList<CardDto>();
		session.setAttribute("cardList", cardList);
		session.setMaxInactiveInterval(240);
		return("login");
	}
	
	@PostMapping(value="/auth/user")
	public String login(@ModelAttribute("Login") LoginModel login, Model model) {
		if(login.geteMail().equals("guest@maxoptra.com")&&login.getPassword().equals("Password@1234")) {
			model.addAttribute(new CardModel());
			session.setAttribute("eMail", login.geteMail());
			//List<CardDto> cardList=(List<CardDto>) session.getAttribute("cardList");
			//session.setAttribute("cardList", cardList);
			return("card");
		}else {
			model.addAttribute(new LoginModel());
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
		cardList.add(c1);
		cardList.forEach(temp->{System.out.println(temp.toString());});
		return("redirect:/display");
	}
	
}
