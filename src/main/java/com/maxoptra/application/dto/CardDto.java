package com.maxoptra.application.dto;

import java.util.Date;

public class CardDto {
	
	private String bankName;
	
	private String cardNumber;
	
	private String expiryDate;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "CardDto [bankName=" + bankName + ", cardNumber=" + cardNumber + ", expiryDate=" + expiryDate + "]";
	}

}
