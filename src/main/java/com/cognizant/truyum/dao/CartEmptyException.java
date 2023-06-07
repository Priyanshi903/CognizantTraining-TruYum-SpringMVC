package com.cognizant.truyum.dao;
@SuppressWarnings("serial")
public class CartEmptyException extends Exception{
	CartEmptyException(String mssg) {
		super(mssg);
//		System.out.println("Cart empty");
	}

}
