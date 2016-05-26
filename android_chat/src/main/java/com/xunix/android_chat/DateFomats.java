package com.xunix.android_chat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFomats {


	/** Date to string  */
	public static String getStringTime(Date date){
		SimpleDateFormat sd = new SimpleDateFormat("MM-dd"+"\t"+"HH:"+"mm");

		return sd.format(date);
	}


}
