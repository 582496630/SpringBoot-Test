package com.zy.many.test.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Long times = 1498889089226L-2*24*60*60*1000L;
		Date date = new Date(times);
		System.out.println(date);
		System.out.println(date.getTime());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
		String date2 = simpleDateFormat.format(date);
		System.out.println("data2:"+date2);
		String year = date2.substring(0, 4);
		System.out.println("年："+year);
		String month = date2.substring(5, 7);
		System.out.println("月："+month);
		String day = date2.substring(8, 10);
		System.out.println("日："+day);
		String hours = date2.substring(11, 13);
		System.out.println("时："+hours);
		String minutes = date2.substring(14, 16);
		System.out.println("分："+minutes);
		String seconds = date2.substring(17, 19);
		System.out.println("秒："+seconds);
		System.out.println("*****************************************************");
		
		//Calendar calendar = Calendar.getInstance();
		Calendar calendar = new GregorianCalendar();
		//calendar.setTime(date);//设置时间
		calendar.set(2017, 6, 31 );
		//calendar.set(year, month, date, hourOfDay, minute, second);
		Date date3 =calendar.getTime();
		System.out.println(date3);
		String date4 = simpleDateFormat.format(date3);
		System.out.println(date4);
		
		Integer yearC = calendar.get(Calendar.YEAR);
		System.out.println("Calendar.YEAR(年):"+yearC);
		Integer monthC = calendar.get(Calendar.MONTH)+1;
		System.out.println("Calendar.MONTH(月):"+monthC);
		Integer dateC = calendar.get(Calendar.DATE);
		System.out.println("Calendar.DATE(日):"+dateC);
		Integer dateC2 = calendar.get(Calendar.DAY_OF_YEAR);
		System.out.println("Calendar.DAY_OF_YEAR(一年中的第几天):"+dateC2);
		Integer hoursC = calendar.get(Calendar.HOUR);
		System.out.println("Calendar.HOUR(时):"+hoursC);
		Integer hoursDayC = calendar.get(Calendar.HOUR_OF_DAY);
		System.out.println("Calendar.HOUR_OF_DAY(24进制的时:):"+hoursDayC);
		Integer minuteC = calendar.get(Calendar.MINUTE);
		System.out.println("Calendar.MINUTE(分):"+minuteC);
		Integer secondC = calendar.get(Calendar.SECOND);
		System.out.println("Calendar.SECOND(秒):"+secondC);
		Integer dayWeekC = calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println("Calendar.DAY_OF_WEEK(数字表示今天周几，1代表周日):"+ dayWeekC);
		Integer weekMonth = calendar.get(Calendar.WEEK_OF_MONTH);
		System.out.println("Calendar.WEEK_OF_MONTH(本月的第几周，数字1--7算是一周，所以星期天算是下周的):"+weekMonth);
		
		String week = null ;
		//两个依据DAY_OF_WEEK 判断星期几的条件语句
/*		if (dayWeekC == 1) {
			week="星期天";
		} else if(dayWeekC == 2){
			week="星期一";
		} else if(dayWeekC == 3){
			week="星期二";
		} else if(dayWeekC == 4){
			week="星期三";
		} else if(dayWeekC == 5){
			week="星期四";
		} else if(dayWeekC == 6){
			week="星期五";
		} else if(dayWeekC == 7){
			week="星期六";
		}*/
		
		switch (dayWeekC) {
		case 1:
			week="星期天";
			break;
		case 2:
			week="星期一";
			break;
		case 3:
			week="星期二";
			break;
		case 4:
			week="星期三";
			break;
		case 5:
			week="星期四";
			break;
		case 6:
			week="星期五";
			break;
		case 7:
			week="星期六";
			break;
			
		default:
			break;
		}
		
		System.out.println("今天是-->"+week);
		
		System.out.println("*****************************************************");
		Integer dy = date.getYear();//年
		System.out.println(dy);
		Integer dm = date.getMonth();//月
		System.out.println(dm);
		Integer dD = date.getDate();//日
		System.out.println(dD);
		Integer d = date.getDay();//周几
		System.out.println(d);
		Integer dh = date.getHours();//时
		System.out.println(dh);
		Integer m = date.getMinutes();//分
		System.out.println(m);
		Integer s = date.getSeconds();//秒
		System.out.println(s);
		
		
		Calendar c = new GregorianCalendar();
		System.out.println(c.getTime());
		String cString  = simpleDateFormat.format(c.getTime());
				System.out.println(cString);
	}

}
