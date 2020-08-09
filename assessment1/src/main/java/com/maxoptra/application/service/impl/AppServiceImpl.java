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

	@Override
	public long getExpiryTimeInMillSec(String expiryDate) {
		
		int op=0;
		Date date=new Date();
//		System.out.println("Output :\t"+expiryDate.substring(0,3).toLowerCase());
		
		switch (expiryDate.substring(0,3).toLowerCase()) {
		case "jan":
			op=0;
			break;
		case "feb":
			op=1;
			break;
		case "mar":
			op=2;
			break;
		case "apr":
			op=3;
			break;
		case "may":
			op=4;
			break;
		case "jun":
			op=5;
			break;
		case "jul":
			op=6;
			break;
		case "aug":
			op=7;
			break;
		case "sep":
			op=8;
			break;
		case "oct":
			op=9;
			break;
		case "nov":
			op=10;
			break;
		case "dec":
			op=11;
			break;
		}
		date.setMonth(op);
		System.out.println("Year :\t"+Integer.parseInt(expiryDate.substring(expiryDate.indexOf("-"), expiryDate.length())));
		date.setYear(Integer.parseInt(expiryDate.substring(expiryDate.indexOf("-")+1, expiryDate.length())));
		System.out.println("Time in Milli Sec :\t"+date.getTime());
		return(date.getTime());
	}

}
