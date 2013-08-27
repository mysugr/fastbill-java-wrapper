package org.testobject.fastbill;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Util {

	public static long secondsToTimestamp(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z").parse(date + " +0100").getTime();
		} catch (ParseException e) {
			throw new RuntimeException(date + " has the wrong date format");
		}
	}
	
	public static long dayToTimestamp(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date + " +0100").getTime();
		} catch (ParseException e) {
			throw new RuntimeException(date + " has the wrong date format");
		}
	}
}