package cn.com.dream.importData.tms;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import cn.com.dream.util.ExcelUtil.ExcelLogs;
import cn.com.dream.util.ExcelUtil.ExcelUtil;
import cn.com.dream.util.JdbcUtil.DBUtil;

public class TmsTest {

	public static void main(String[] args) throws Exception {

		File f = new File("src/main/java/cn/com/dream/importData/tms/InputTaxRate20190404.xls");
		InputStream inputStream = new FileInputStream(f);

		ExcelLogs logs = new ExcelLogs();
		Collection<Map> importExcel = ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs, 0);
		int sk = 1;
		for (Map m : importExcel) {
			System.out.println(sk);
			sk++;
			System.out.println(m);
			String where = "purchaseNo = ?";
			String[] whereArgs = new String[] { m.get("purchaseNo") + "" };
			List<Map<String, Object>> list = DBUtil.query("InputTaxRate", where, whereArgs);

			/*
			 * if (list != null && list.size() > 0) { for (int i = 0; i < list.size(); i++)
			 * { System.out.println("存在数据：" + list.get(i).toString()); }
			 * 
			 * if (list.size() == 2) { System.out.println("存在数据：多条数据" + m.get("id") + "" );
			 * Map<String, Object> whereMap = new HashMap<>(); whereMap.put("id",
			 * m.get("id") + ""); DBUtil.delete("u_dept_temp_bak", whereMap); }
			 * 
			 * 
			 * continue; }
			 */

			DBUtil.insert("InputTaxRate", m);
		}

	}

}
