package com.cls.utility;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class MounthOfYear {
	
	private static final LocalDateAttributeConverter lc= new LocalDateAttributeConverter();
	
	public static String getMountYear(Date date){
		LocalDate usedDate= lc.convertToEntityAttribute(date); 
		String luna  = usedDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
		return luna;
	}
	
	public static String getMountYearPrecedent(Date date){
		LocalDate usedDate =lc.convertToEntityAttribute(date).minusMonths(1);
		String luna  = usedDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
		return luna;
	}
	

}
