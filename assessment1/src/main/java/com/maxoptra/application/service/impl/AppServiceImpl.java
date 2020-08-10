package com.maxoptra.application.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.maxoptra.application.dto.CardDto;
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
		String expiryDate = "";
		switch (date.getMonth()) {
		case 0:
			expiryDate += "Jan-";
			break;
		case 1:
			expiryDate += "Feb-";
			break;
		case 2:
			expiryDate += "Mar-";
			break;
		case 3:
			expiryDate += "Apr-";
			break;
		case 4:
			expiryDate += "May-";
			break;
		case 5:
			expiryDate += "Jun-";
			break;
		case 6:
			expiryDate += "Jul-";
			break;
		case 7:
			expiryDate += "Aug-";
			break;
		case 8:
			expiryDate += "Sep-";
			break;
		case 9:
			expiryDate += "Oct-";
			break;
		case 10:
			expiryDate += "Nov-";
			break;
		case 11:
			expiryDate += "Dec-";
			break;
		}
		return (expiryDate + date.getYear());
	}

	@Override
	public long getExpiryTimeInMillSec(String expiryDate) {

		int op = 0;
		Date date = new Date();
//		System.out.println("Output :\t"+expiryDate.substring(0,3).toLowerCase());

		switch (expiryDate.substring(0, 3).toLowerCase()) {
		case "jan":
			op = 0;
			break;
		case "feb":
			op = 1;
			break;
		case "mar":
			op = 2;
			break;
		case "apr":
			op = 3;
			break;
		case "may":
			op = 4;
			break;
		case "jun":
			op = 5;
			break;
		case "jul":
			op = 6;
			break;
		case "aug":
			op = 7;
			break;
		case "sep":
			op = 8;
			break;
		case "oct":
			op = 9;
			break;
		case "nov":
			op = 10;
			break;
		case "dec":
			op = 11;
			break;
		}
		date.setMonth(op);
		date.setYear(Integer.parseInt(expiryDate.substring(expiryDate.indexOf("-") + 1, expiryDate.length())));
		return (date.getTime());
	}

	@Override
	public List<CardDto> validateCardinFile(Map<Long, CardDto> cardMap) {
		List<CardDto> cardList = new ArrayList<CardDto>();
		String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" + "(?<mastercard>5[1-5][0-9]{14})|"
				+ "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" + "(?<amex>3[47][0-9]{13})|"
				+ "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" + "(?<jcb>(?:2131|1800|35\\d{3})\\d{11}))$";
		//^(?:2131|1800|35\d{3})\d{11}$
		//(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$
		Pattern pattern = Pattern.compile(regex);
		String cardNumber = "";
//		System.out.println("within service");
		for(Long temp:cardMap.keySet()) {
//			System.out.println(cardMap.get(temp));
		}
		for (Long temp : cardMap.keySet()) {
			CardDto cardDto = cardMap.get(temp);
			cardNumber = cardDto.getCardNumber().replaceAll("-", "");
			Matcher matcher = pattern.matcher(cardNumber);
			if (!matcher.matches()) {
//				System.out.println("Invalid Card :\t"+cardNumber.toString());
				cardList.add(cardDto);
				cardMap.remove(temp);
			}
		}
		return (cardList);
	}

}
