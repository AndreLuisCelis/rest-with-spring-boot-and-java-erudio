package com.celisapp.service;

import org.springframework.stereotype.Service;

import com.celisapp.exceptions.ResourceNotfoundException;

@Service
public class MatchService {

	public Double sum(String numberOne, String numberTwo) throws Exception {
		if (!isNumber(numberOne) || !isNumber(numberTwo)) {
			throw new ResourceNotfoundException(" Please set a numeric value");
		}
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}

	public Double sub(String numberOne, String numberTwo) throws Exception {
		if (!isNumber(numberOne) || !isNumber(numberTwo)) {
			throw new ResourceNotfoundException(" Please set a numeric value");
		}
		return convertToDouble(numberOne) - convertToDouble(numberTwo);
	}

	public Double mult(String numberOne, String numberTwo) throws Exception {
		if (!isNumber(numberOne) || !isNumber(numberTwo)) {
			throw new ResourceNotfoundException(" Please set a numeric value");
		}
		return convertToDouble(numberOne) * convertToDouble(numberTwo);
	}

	public Double divider(String numberOne, String numberTwo) {
		if (!isNumber(numberOne) || !isNumber(numberTwo)) {
			throw new ResourceNotfoundException(" Please set a numeric value");
		}
		return convertToDouble(numberOne) / convertToDouble(numberTwo);
	}

	private Double convertToDouble(String strNumber) {
		if (strNumber == null)
			return 0D;
		String number = strNumber.replaceAll(",", ".");
		if (isNumber(number)) {
			return Double.parseDouble(strNumber);
		}
		return 0D;
	}

	private boolean isNumber(String strNumber) {
		if (strNumber == null)
			return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

}
