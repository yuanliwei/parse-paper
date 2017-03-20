package com.ylw.parsepaper.logic.excel;

import com.ylw.parsepaper.logic.utils.PropUtils;
import com.ylw.parsepaper.logic.utils.ormliteutils.OrmLiteConnection;
import com.ylw.parsepaper.logic.utils.ormliteutils.OrmLiteUtils;

public class WriteSql {
	public static void main(String[] args) throws Exception {
		String databaseUrl = "jdbc:mysql://192.168.0.16:3306/cn_up366feishu_test?user=ttedudb&password=ttedudb";
		PropUtils.load();
		PropUtils.put("db_path", databaseUrl);
		// ConnectionSource connectionSource = new
		// JdbcConnectionSource(databaseUrl);

		WrdWordInfo info = new WrdWordInfo();
		info.setWordId(110001);
		info = OrmLiteUtils.queryForSameId(info);
		System.err.println(info);
		// // instantiate the dao
		// Dao<Account, String> accountDao =
		// DaoManager.createDao(connectionSource, Account.class);
		//
		// // if you need to create the 'accounts' table make this call
		// TableUtils.createTable(connectionSource, Account.class);
		OrmLiteConnection.getConnection().close();
	}
}
