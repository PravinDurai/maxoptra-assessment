package com.maxoptra.application.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardModel {

	private String bankName;

	private String cardNumber;

	private String encCardNumber;

	private String expiryMonth;

	private List<Integer> year = new ArrayList<Integer>();

	private String expiryYear;

	private String expiryDate;
	
	public CardModel() {
		super();
		this.bankName = "";
		this.cardNumber = "";
		this.expiryMonth = "";
		this.expiryYear = "";
		this.encCardNumber = "";
		this.expiryDate="";
		Date date = new Date();
		for (int i = 1900 + date.getYear(); i <= 1900 + date.getYear() + 20; i++) {
			year.add(i);
		}
	}

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

	public String getEncCardNumber() {
		return encCardNumber;
	}

	public void setEncCardNumber(String encCardNumber) {
		this.encCardNumber = encCardNumber;
	}

	public String getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public List<Integer> getYear() {
		return year;
	}

	public void setYear(List<Integer> year) {
		this.year = year;
	}

	public String getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "CardModel [bankName=" + bankName + ", cardNumber=" + cardNumber + ", encCardNumber=" + encCardNumber
				+ ", expiryMonth=" + expiryMonth + ", year=" + year + ", expiryYear=" + expiryYear + ", expiryDate="
				+ expiryDate + "]";
	}
	
}
