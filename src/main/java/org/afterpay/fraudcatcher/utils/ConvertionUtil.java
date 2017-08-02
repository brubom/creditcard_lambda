package org.afterpay.fraudcatcher.utils;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class ConvertionUtil {

	public static UUID getUUIDfromPlainString(String uuid) {
		
		StringBuffer sb = new StringBuffer(uuid);
		sb.insert(8, "-");
		 
		sb = new StringBuffer(sb.toString());
		sb.insert(13, "-");
		 
		sb = new StringBuffer(sb.toString());
		sb.insert(18, "-");
		 
		sb = new StringBuffer(sb.toString());
		sb.insert(23, "-");
		 
		return UUID.fromString( sb.toString());
		
		
	}
	
	public static Timestamp getTimestampFromString(String timestamp) throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	    Date parsedDate = dateFormat.parse(timestamp);
	    return new Timestamp(parsedDate.getTime());
		
	}
	
	public static String getStringFromTimestamp(Timestamp timestamp) throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		return dateFormat.format(timestamp);
	    
		
	}
	
}
