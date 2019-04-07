package cn.com.dream.importData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import cn.com.dream.util.ExcelUtil.ExcelLogs;
import cn.com.dream.util.ExcelUtil.ExcelUtil;

public class UserTemp {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File f=new File("src/test/resources/test.xls");
	    InputStream inputStream= new FileInputStream(f);
	    
	    ExcelLogs logs =new ExcelLogs();
	    Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs , 0);
	    
	    for(Map m : importExcel){
	      System.out.println(m);
	    }
		
	}
	
}
