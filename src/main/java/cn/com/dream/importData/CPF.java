package cn.com.dream.importData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import cn.com.dream.util.ExcelUtil.ExcelLogs;
import cn.com.dream.util.ExcelUtil.ExcelUtil;
import cn.com.dream.util.JdbcUtil.DBUtil;

public class CPF {
	public static void main(String[] args) throws FileNotFoundException, SQLException {
		File f = new File("src\\main\\java\\cn\\com\\dream\\importData\\cpf导入.xls");
		InputStream inputStream = new FileInputStream(f);

		ExcelLogs logs = new ExcelLogs();
		Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm", logs, 0);

		for (Map m : importExcel) {
			System.out.println("***********************************");
			System.out.println(m);
			
			/*
			 * if("45".equals(m.get("id")) || "46".equals(m.get("id"))) { continue; }
			 */
			

			if(!"45".equals(m.get("id")) && !"46".equals(m.get("id"))) { 
				 continue; 
			}
			 
			
			if(m.get("production_Date") != null &&m.get("production_Date").toString().length() ==0) {
				 m.put("production_Date",null);
			 }
			 if(m.get("production_Date") != null && m.get("production_Date").toString().length()>0) {
				 if(m.get("production_Date").toString().contains("/")) {
					 m.put("production_Date",m.get("production_Date"));
				 }else {
					 m.put("production_Date",doubleToDate(Double.valueOf(m.get("production_Date").toString().trim()+"")));
				 }
			 }else {
				 System.out.println(m.get("production_Date"));
				 m.put("production_Date",null); 
			 }
			 
			 if(m.get("buy_Date") != null &&m.get("buy_Date").toString().length() >0) {
				 
				 m.put("buy_Date",doubleToDate(Double.valueOf(m.get("buy_Date")+"")));
			 }else { 
				 m.put("buy_Date",null); 
			 }
			 if(m.get("receive_Date") != null &&m.get("receive_Date").toString().length() >0) {
				 m.put("receive_Date",doubleToDate(Double.valueOf(m.get("receive_Date")+"")));
			 }else { 
				 m.put("receive_Date",null); 
			 }
			 if(m.get("case_Date") != null &&m.get("case_Date").toString().length() >0) {
				 m.put("case_Date",doubleToDate(Double.valueOf(m.get("case_Date")+"")));
			 }else { 
				 m.put("case_Date",null); 
			 }
			 
			 
			 if(m.get("w_Reach") != null &&m.get("w_Reach").toString().length() ==0) {
				 m.put("w_Reach",null);
			 }
			 if(m.get("w_Delay") != null &&m.get("w_Delay").toString().length() ==0) {
				 m.put("w_Delay",null);
			 }
			 if(m.get("w_Repair") != null &&m.get("w_Repair").toString().length() ==0) {
				 m.put("w_Repair",null);
			 }
			/*
			 * if(m.get("w_Law") != null &&m.get("w_Law").toString().length() ==0) {
			 * m.put("w_Law",null); }
			 */
			 
			 
			System.out.println(m);
			
			String where = "id = ?";
			String[] whereArgs = new String[] { m.get("id") + "" };
			List<Map<String, Object>> list = DBUtil.query("mod_case_detail", where, whereArgs);

			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					System.out.println("存在数据：" + list.get(i).toString());
				}
				continue;
			}
		
			DBUtil.insert("mod_case_detail", m);

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
