package com.maxoptra.application.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.maxoptra.application.service.AppService;

@Service
public class AppServiceImpl implements AppService {

	@Override
	public String convertCardNumber(String cardNumber) {
		String newEncCardNumber;
		newEncCardNumber = "xxxx-xxxx-xxxx" + cardNumber.substring(cardNumber.lastIndexOf("-"), cardNumber.length());
		return (newEncCardNumber);
	}

	@Override
	public String getOutputExpiryDate(Date date) {
		String expiryDate="";
		switch (date.getMonth()) {
		case 0:
			expiryDate+="Jan-";
			break;
		case 1:
			expiryDate+="Feb-";
			break;
		case 2:
			expiryDate+="Mar-";
			break;
		case 3:
			expiryDate+="Apr-";
			break;
		case 4:
			expiryDate+="May-";
			break;
		case 5:
			expiryDate+="Jun-";
			break;
		case 6:
			expiryDate+="Jul-";
			break;
		case 7:
			expiryDate+="Aug-";
			break;
		case 8:
			expiryDate+="Sep-";
			break;
		case 9:
			expiryDate+="Oct-";
			break;
		case 10:
			expiryDate+="Nov-";
			break;
		case 11:
			expiryDate+="Dec-";
			break;
		}
		return(expiryDate+date.getYear());
	}

}
