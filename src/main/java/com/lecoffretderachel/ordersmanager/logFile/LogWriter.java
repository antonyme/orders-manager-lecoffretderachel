package com.lecoffretderachel.ordersmanager.logFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.opencsv.CSVWriter;

public class LogWriter {
	private static final String LOCATION = "C:"
			+ File.separator + "Users"
			+ File.separator + "GUILLAUME"
			+ File.separator + "Google Drive"
			+ File.separator + "Rachel"
			+ File.separator + "logs";
	
	public static void write(String fileName, List<String[]> toLog) {
		String filePath = LOCATION + File.separator + fileName + ".csv";
		try (
				CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))
		) {
			writer.writeAll(toLog);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
