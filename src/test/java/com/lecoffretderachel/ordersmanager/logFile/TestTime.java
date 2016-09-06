package com.lecoffretderachel.ordersmanager.logFile;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestTime {

	public static void main(String[] args) {
		System.out.println(ZonedDateTime.now(ZoneId.of("America/Montreal")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH'h'mm'.'ss's'")));
	}

}
