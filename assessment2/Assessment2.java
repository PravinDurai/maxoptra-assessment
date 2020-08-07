package com.maxoptra.assessment2;

import java.util.Scanner;

public class Assessment2 {

	public static int findDigitSum(int number) {
		if(number!=0) {
			int sum=number%10+findDigitSum(number/10);
			return(sum);
		}else {
			return(0);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int number=sc.nextInt();
		int sum=0;
		sum=findDigitSum(number);
		System.out.println("Sum :\t"+sum);
	}

}
