package com.lecoffretderachel.ordersmanager.logFile;

import java.util.ArrayList;
import java.util.List;

public class TestLogWriter {
	public static void main(String[] args) {
		List<String[]> list = new ArrayList<>();
		list.add(new String[] {"test", "test", "ok"});
		LogWriter.write("test", list);
	}

}
