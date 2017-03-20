package com.ylw.parsepaper.logic.excel;

import java.io.File;
import java.util.List;

import com.ylw.excelorm.ExcelParser;
import com.ylw.parsepaper.logic.utils.PropUtils;
import com.ylw.parsepaper.logic.utils.ormliteutils.OrmLiteConnection;
import com.ylw.parsepaper.logic.utils.ormliteutils.OrmLiteUtils;

public class ExcelToSql {
	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\y\\Desktop\\天学网词汇\\2017.02.15-考纲词汇导入（任务分配）-程灵玺.xlsx");
		String databaseUrl = "jdbc:mysql://192.168.0.16:3306/cn_up366feishu_test?user=ttedudb&password=ttedudb";
		PropUtils.load();
		PropUtils.put("db_path", databaseUrl);

		List<WrdWordInfoV2> wrds = ExcelParser.parse(file.getAbsolutePath(), WrdWordInfoV2.class);
		wrds.forEach(action -> {
			System.out.println(action.toString());
		});
		OrmLiteUtils.saveOrUpdateAll(wrds);
		OrmLiteConnection.getConnection().close();

	}
}
