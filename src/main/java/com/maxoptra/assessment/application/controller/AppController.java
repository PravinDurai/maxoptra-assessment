package com.maxoptra.assessment.application.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxoptra.assessment.application.model.CardDetails;
import com.maxoptra.assessment.application.service.AppService;

@RestController
@RequestMapping("/validate")
public class AppController {
	
	@Autowired
	AppService appService;
	
	@PostMapping(value="/single/cardInfo")
	public ResponseEntity<String> validateSingleCard(@Valid @ModelAttribute("card") CardDetails cardDetails){
		
		HttpStatus status=HttpStatus.OK;
		String outputStatus="valid";
		
		return(new ResponseEntity<String>(outputStatus,status));
	}

}
