package cn.com.dream.importData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.Get;

import cn.com.dream.util.ExcelUtil.ExcelLogs;
import cn.com.dream.util.ExcelUtil.ExcelUtil;
import cn.com.dream.util.JdbcUtil.DBUtil;

public class GtmcUserTemp {
	
	public static void main(String[] args) throws FileNotFoundException, SQLException {
		File f = new File("src\\main\\java\\cn\\com\\dream\\importData\\05gtmc用户.xls");
		InputStream inputStream = new FileInputStream(f);

		ExcelLogs logs = new ExcelLogs();
		Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm", logs, 0);

		for (Map m : importExcel) {
			//System.out.println(m);
			//System.out.println(m.get("LOGIN_NAME") + "");
			/*
			 * if(m.get("CREATED_TIME") != null && m.get("CREATED_TIME").toString().length()
			 * >0) {
			 * m.put("CREATED_TIME",doubleToDate(Double.valueOf(m.get("CREATED_TIME")+"")));
			 * }else {
			 * 
			 * m.put("CREATED_TIME",null); }
			 */
			/*
			 * if(m.get("UPDATED_TIME") != null && m.get("UPDATED_TIME").toString().length()
			 * >0) {
			 * m.put("UPDATED_TIME",doubleToDate(Double.valueOf(m.get("UPDATED_TIME")+"")));
			 * }else { m.put("UPDATED_TIME",null); }
			 */
			
			
			//System.out.println(m);
			
			String where = "LOGIN_NAME = ?";
			String[] whereArgs = new String[] { m.get("LOGIN_NAME") + "" };
			List<Map<String, Object>> list = DBUtil.query("u_user_temp", where, whereArgs);

			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					System.out.println("存在数据：" + list.get(i).toString());
				}
				continue;
			}
			System.out.println("haha:" + m);
			break;
			//DBUtil.insert("u_user_temp", m);

		}
		
		
	}
	
	
	
	/** 
     * 时间戳转换成日期格式字符串 
     * @param seconds 精确到秒的字符串 
     * @param formatStr 
     * @return 
     */  
    public static String doubleToDate(Double d) {  
		if(d == null || d==0 || d == 0.00) {
			return "";
		}
		Calendar base = Calendar.getInstance();
		//Delphi的日期类型从1899-12-30开始
		base.set(1899, 11, 30, 0, 0, 0);
		base.add(Calendar.DATE, d.intValue());
		base.add(Calendar.MILLISECOND,(int)((d % 1) * 24 * 60 * 60 * 1000));
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return outFormat.format(base.getTime());  
    }  
	
}
