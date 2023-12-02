package com.drakend.scholarshipManage.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.drakend.scholarshipManage.exception.BadAgrumentException;

/**
 * <p>
 * This class a helper to convert string to date and reverse
 * </p>
 * 
 * @author NguyenDuyLong2810
 */
public class DateHelpers {

	private static Date instance;

	private DateHelpers() {
	}

	public static Date getInstance() {
		if (instance == null) {
			instance = new Date();
			return instance;
		}
		return instance;
	}

	public static String toString(Date date) {
		return date.toString();
	}

	public static Date toDate(String dateString) {
		try {
			return new SimpleDateFormat("yyy-MM-DD HH:MM:SS").parse(dateString);
		} catch (ParseException e) {
			throw new BadAgrumentException("Format date is incorrect");
		}
	}
}
