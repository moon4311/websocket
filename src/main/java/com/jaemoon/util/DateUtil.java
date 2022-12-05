package com.jaemoon.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getTime(Date dt) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern("YYYY-MM-dd HH:mm:ss");
		return sdf.format(dt);
	}
	
}
