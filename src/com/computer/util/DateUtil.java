 
 
package com.computer.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
 
public class DateUtil {
	private static final Log log = Log.getLog(DateUtil.class);
			 
	
	public static Date strCSTToDate(String dateInString,String inFormat){
		SimpleDateFormat sdf=new SimpleDateFormat(inFormat,Locale.US);
		
		Date date=null;
		try {
			date = (Date) sdf.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error("strCSTToFate Error! "+e.getMessage());
		}
		
		
		return date;
	}
	
	public static String strCSTTostrDate(String dateInString,String inFormat,String outFormat){
		SimpleDateFormat sdf=new SimpleDateFormat(inFormat,Locale.US);
		
		Date date=null;
		try {
			date = (Date) sdf.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error("strCSTToFate Error! "+e.getMessage());
		}
		if(date==null)
			return null;
		
		return new SimpleDateFormat(outFormat).format(date);
	}
	
	
	/**
	  * 将短时间格式字符串转换为时间 yyyy-MM-dd HH:mm 
	  * "yyyy-MM-dd HH:mm"
	  * @param strDate
	  * @return
	  */
	 public static Date strToDate(String dateInString,String format) {
		 
		    SimpleDateFormat sdf=new SimpleDateFormat(format);
			
			Date date=null;
			try {
				date = (Date) sdf.parse(dateInString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				log.error("strToDate Error! "+e.getMessage());
			}
			
			
			return date;
	
		 
	 }
	 
	 public static boolean isDate(String dttm, String format) {
		    if (dttm == null || dttm.isEmpty() || format == null || format.isEmpty()) {
		        return false;
		    }

		    if (format.replaceAll("'.+?'", "").indexOf("y") < 0) {
		        format += "/yyyy";
		        DateFormat formatter = new SimpleDateFormat("/yyyy");
		        dttm += formatter.format(new Date());
		    }

		    DateFormat formatter = new SimpleDateFormat(format);
		    formatter.setLenient(false);
		    ParsePosition pos = new ParsePosition(0);
		    Date date = formatter.parse(dttm, pos);

		    if (date == null || pos.getErrorIndex() > 0) {
		        return false;
		    }
		    if (pos.getIndex() != dttm.length()) {
		        return false;
		    }

		    if (formatter.getCalendar().get(Calendar.YEAR) > 9999) {
		        return false;
		    }

		    return true;
		}
	 
	
	 /**
		 * 获取某一个月的第一天
		 * @param month
		 * @param Timestamp
		 * @return
		 */
	    public static Timestamp getFirstDayOfMonth(Calendar calendar) {
	        
	        calendar.set(Calendar.DATE, 1);        // 设置当前月第一天
	        calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);
	        
	       
	        return new Timestamp(calendar.getTimeInMillis());
	    }
	    
	    /**
		 * 获取某一的开始时间
		 * @param month
		 * @param Timestamp
		 * @return
		 */
	    public static Timestamp getFirstTimeOfDay(Calendar calendar) {
	        
	       
	        calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);
	        
	       
	        return new Timestamp(calendar.getTimeInMillis());
	    }
	    
	    /**
		 * 获取某一天的最后时间
		 * @param month
		 * @param Timestamp
		 * @return
		 */
	    public static Timestamp getLastTimeOfDay(Calendar calendar) {
	   
	        
	        calendar.set(Calendar.HOUR_OF_DAY, 23);
	        calendar.set(Calendar.MINUTE, 59);
	        calendar.set(Calendar.SECOND, 59);	        
	       
	        
	        return new Timestamp(calendar.getTimeInMillis());
	    }
	    
	    /**
		 * 获取某一个月的最后一天
		 * @param month
		 * @param Timestamp
		 * @return
		 */
	    public static Timestamp getLastDayOfMonth(Calendar calendar) {
	   
	        calendar.set(Calendar.DATE, 1);        // 设置当前月的1号
	        
	        calendar.set(Calendar.HOUR_OF_DAY, 23);
	        calendar.set(Calendar.MINUTE, 59);
	        calendar.set(Calendar.SECOND, 59);
	        
	        calendar.add(Calendar.MONTH, 1);    // 加一个月，变为下月的1号
	        calendar.add(Calendar.DATE, -1);    // 减一天，变为当前月的最后一天
	        
	        return new Timestamp(calendar.getTimeInMillis());
	    }
	    
		/**
		 * 计算两个日期之间相差的天数
		 * @param costTime
		 * @param receivableTime
		 * @return
		 */
		public static int getDays(Date end,Date start){
			Calendar aCalendar = Calendar.getInstance();
	        Calendar bCalendar = Calendar.getInstance();
	        aCalendar.setTime(end);
	        bCalendar.setTime(start);
	        int days = 0;
	        while(aCalendar.before(bCalendar)){
	            days++;
	            aCalendar.add(Calendar.DAY_OF_YEAR, 1);
	        }
	        
	        if(days==0){
	        	aCalendar.setTime(start);
	            bCalendar.setTime(end);
	            while(aCalendar.before(bCalendar)){
	                days++;
	                aCalendar.add(Calendar.DAY_OF_YEAR, 1);
	            }
	        }
	        return days;
		}
		
		/**
		 * 计算两个月份之间相差的月份数
		 * @param costTime
		 * @param receivableTime
		 * @return
		 */
		public static int getMonths(Date end,Date start){
			Calendar aCalendar = Calendar.getInstance();
	        Calendar bCalendar = Calendar.getInstance();
	        aCalendar.setTime(end);
	        bCalendar.setTime(start);
	        int months = 0;
	        while(aCalendar.before(bCalendar)){
	        	months++;
	            aCalendar.add(Calendar.MONTH, 1);
	        }
	        
	        if(months==0){
	        	aCalendar.setTime(start);
	            bCalendar.setTime(end);
	            while(aCalendar.before(bCalendar)){
	            	months++;
	                aCalendar.add(Calendar.MONTH, 1);
	            }
	        }
	        return months;
		}
		
		/**
		 * 返回年数
		 * @param date
		 * @param year
		 * @return
		 */
		public static String getYear(Date date){
			
	        return String.valueOf(date.getYear()+1900);
		}
		
		/**
		 * 获取某一个月的最后一天
		 * @param month
		 * @param Timestamp
		 * @return
		 */
	    public static Timestamp getLastMonthTime(Calendar calendar) {
	   
	       
	        
	        calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);
	        
	        calendar.add(Calendar.MONTH, -1);    // 减一个月，变为上个月的开始
	       
	        
	        return new Timestamp(calendar.getTimeInMillis());
	    }
		
		/**
		 * 返回月份数
		 * @param date
		 * @param month
		 * @return
		 */
		public static String getMonth(Date date){
			
			int queryMonthReal=date.getMonth()+1;//Date.getMonth 返回的是0-11，故要加1
			if(queryMonthReal<10){
				return "0"+queryMonthReal;//1->01 2->02 ... 9->09
			}else{
				return String.valueOf(queryMonthReal);//10 11 12
			}
			
		}
	 
}



