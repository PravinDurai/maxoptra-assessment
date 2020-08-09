package com.maxoptra.application.dto;

import com.opencsv.bean.CsvBindByName;

public class CSVFileDto {
	
	@CsvBindByName(column="Bank")
	private String bankName;
	
	@CsvBindByName(column="Card number")
	private String cardNumber;
	
	@CsvBindByName(column="Expiry date")
	private String expiryDate;
	
	public CSVFileDto(String bankName, String cardNumber, String expiryDate) {
		super();
		this.bankName = bankName;
		this.cardNumber = cardNumber;
		this.expiryDate = expiryDate;
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

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

}
