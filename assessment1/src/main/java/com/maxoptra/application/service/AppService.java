package com.maxoptra.application.service;

import java.util.Date;

public interface AppService {
	
	public String convertCardNumber(String cardNumber);
	public String getOutputExpiryDate(Date date);
	public long getExpiryTimeInMillSec(String expiryDate);

}
