
package com.criown.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	//date-->string  年月日
	public static String formatDate1(Date date){
		//System.out.println("formatDate1::"+date);
		SimpleDateFormat sdf;
		if(date!=null){
			sdf= new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		}
		else  return "null";

	}

	//date-->string  年月日时
	public static String formatDate2(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		if(date!=null){
			return sdf.format(date);
		}
		else  return "null";
	}


	public static Date formatString(String str,String format) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}

	public static Date calculate(Date date,int hour)
	{
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		//加day天数
		calendar.add(Calendar.HOUR_OF_DAY,hour);
		date = calendar.getTime();
		return  date;
	}

	public static String getTime()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
}
