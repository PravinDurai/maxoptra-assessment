package com.maxoptra.application.model;

import java.util.Date;

public class CardModel {
	
	private String bankName;
	
	private String cardNumber;
	
	private String expirationDate;

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

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "CardModel [bankName=" + bankName + ", cardNumber=" + cardNumber + ", expirationDate=" + expirationDate
				+ "]";
	}	
}
