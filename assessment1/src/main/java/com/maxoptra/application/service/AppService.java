package com.maxoptra.application.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.maxoptra.application.dto.CardDto;

public interface AppService {
	
	public String convertCardNumber(String cardNumber);
	public String getOutputExpiryDate(Date date);
	public long getExpiryTimeInMillSec(String expiryDate);
	
	public List<CardDto> validateCardinFile(Map<Long,CardDto> cardMap);

}
