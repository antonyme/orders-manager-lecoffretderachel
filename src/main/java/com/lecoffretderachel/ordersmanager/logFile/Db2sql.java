package com.lecoffretderachel.ordersmanager.logFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.lecoffretderachel.ordersmanager.service.UtilService;
import com.lecoffretderachel.ordersmanager.util.Util;

public class Db2sql {
	private UtilService utilService;
	
	public Db2sql(UtilService utilService) {
		this.utilService = utilService;
	}

	public void dumpDatabase(String fileName) {
		try {
			File dir = new File("database backup");
			dir.mkdirs();
			String filePath = new File(dir, fileName + ".sql").getPath();
			FileWriter fw = new FileWriter(filePath);
			BufferedWriter buff = new BufferedWriter(fw);

			StringBuilder sb = new StringBuilder();
			sb.append("SET FOREIGN_KEY_CHECKS=0;");
			sb.append("\n");

			List<String> tables = utilService.getTables();
			for(String tbl : tables) {
				sb.append("\n");
				sb.append("-- ----------------------------\n")
				.append("-- Table structure for `")
				.append(tbl)
				.append("`\n-- ----------------------------\n");
				sb.append("DROP TABLE IF EXISTS `")
				.append(tbl)
				.append("`;\n");
				String crt = utilService.showCreateTable(tbl) + ";";
				sb.append(crt).append("\n");

				List<Object[]> data = utilService.getData(tbl);
				int lineCount = data.size();
				if(lineCount != 0) {
					sb.append("\n");
					sb.append("-- ----------------------------\n").append("-- Records for `").append(tbl)
							.append("`\n-- ----------------------------\n");
					sb.append("INSERT INTO ").append(tbl).append(" VALUES\n");
				}
				for(int i = 0; i <  lineCount; i++) {
					Object[] line = data.get(i);
					int columnCount = line.length;
					if(columnCount != 0) {
						sb.append("\t(");
					}
					for(int j = 0; j < columnCount; j++) {
						if (j != 0) {
							sb.append(",");
						}
						String s = "";
						try {
							if(line[j].getClass() == String.class) {
								s += "'";
								s += Util.addEscapeChar((String) line[j]);
								s += "'";
							}
							else if (line[j].getClass() == Integer.class) {
								s += line[j];
							}
							else if (line[j].getClass() == Boolean.class) {
								s += (Boolean) line[j] ? 1 : 0;
							}
							else {
								s += "'";
								s += line[j].toString();
								s += "'";
							}
						} catch (Exception e) {
							s = "NULL";
						}
						sb.append(s);
					}
					if (i != lineCount - 1) {
						sb.append("),\n");
					}
					else {
						sb.append(");\n");
					}
					buff.append(sb.toString());
					sb = new StringBuilder();
				}
			}
			buff.flush();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}