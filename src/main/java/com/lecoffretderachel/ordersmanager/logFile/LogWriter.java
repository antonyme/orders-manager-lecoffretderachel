package com.lecoffretderachel.ordersmanager.logFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.opencsv.CSVWriter;

public class LogWriter {
	
	public static void write(String fileName, List<String[]> toLog) {
		File dir = new File("logs");
		dir.mkdirs();
		String filePath = new File(dir, fileName + ".csv").getPath();
		try (
				CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))
		) {
			writer.writeAll(toLog);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
