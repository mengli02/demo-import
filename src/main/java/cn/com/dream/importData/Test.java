package cn.com.dream.importData;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Test {

	public static void main(String[] args) {
		
		System.out.println(0 == 0.0);
		
		Double d = 42235.8122337963;
		String t;
		
		Calendar base = Calendar.getInstance();
		//Delphi的日期类型从1899-12-30开始
		base.set(1899, 11, 30, 0, 0, 0);
		base.add(Calendar.DATE, d.intValue());
		base.add(Calendar.MILLISECOND,(int)((d % 1) * 24 * 60 * 60 * 1000));
		
		System.out.println(base.getTime());
		
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		t = outFormat.format(base.getTime());
		System.out.println(t);
		//t=outFormat.format(base.getTime());  
		
	}
	
}


