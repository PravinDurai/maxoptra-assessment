package com.maxoptra.assessment.application.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxoptra.assessment.application.dto.LoginDto;
import com.maxoptra.assessment.application.model.Login;
import com.maxoptra.assessment.application.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthService authService;
	
	public String validateUser(@Valid @ModelAttribute("Login") Login login) {
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		LoginDto loginDto=modelMapper.map(login, LoginDto.class);
		if(authService.validateUser(loginDto)) {
			
		}
		return("Card");
	}
	
}
