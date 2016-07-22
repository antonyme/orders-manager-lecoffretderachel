package com.lecoffretderachel.ordersmanager.imports.order;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

public class OrderCSVParser {
	
	public static List<OrderCSV> parseFile(InputStreamReader fileReader) {
	    HeaderColumnNameMappingStrategy<OrderCSV> strategy = new HeaderColumnNameMappingStrategy<>();
	    strategy.setType(OrderCSV.class);
	    CsvToBean<OrderCSV> csvToBean = new CsvToBean<>();
    	return csvToBean.parse(strategy, new CSVReader(fileReader));
	}
	
	public static List<ItemModel> parseItems(OrderCSV orderCSV) throws IllegalArgumentException {
		List<String> itemCSVList = new ArrayList<>();
		List<ItemModel> itemModelList = new ArrayList<>();
		itemCSVList.add(orderCSV.getOrder_item_1());
		itemCSVList.add(orderCSV.getOrder_item_2());
		itemCSVList.add(orderCSV.getOrder_item_3());
		itemCSVList.add(orderCSV.getOrder_item_4());
		itemCSVList.add(orderCSV.getOrder_item_5());
		itemCSVList.add(orderCSV.getOrder_item_6());
		itemCSVList.forEach((item) -> 
		{
			if(item != null) itemModelList.add(parseItem(item));
		});
		return itemModelList;
	}
	
	private static ItemModel parseItem(String itemCSV) throws IllegalArgumentException {
		ItemModel res = new ItemModel();
		String[] splitItem = itemCSV.split("\\|");
		if(splitItem.length == 4) {
			res.setIsSubscription(false);
		}
		else if(splitItem.length == 5 && splitItem[0].equals("Abonnement")) {
			res.setIsSubscription(true);
		}
		else {
			throw new IllegalArgumentException("Item : " + itemCSV
					+ " does not comply with required item format");
		}
		res.setName(splitItem[0]);
		try {
			res.setQuantity(Integer.parseInt(splitItem[1]));
		}
		catch (NumberFormatException e) {
			throw new IllegalArgumentException("In item : " + itemCSV + ", quantity : " + splitItem[1]
					+ ", is not a valid integer");
		}
		try {
			res.setPrice(Double.parseDouble(splitItem[2]));
		}
		catch (NumberFormatException e) {
			throw new IllegalArgumentException("In item : " + itemCSV + ", price : " + splitItem[2]
					+ ", is not a valid number");
		}
		String[] splitSize = splitItem[3].split(": ");
		if(splitSize.length == 2) {
			res.setSize(splitSize[1]);
		}
		else {
			throw new IllegalArgumentException("In item : " + itemCSV + ", size : " + splitItem[3]
					+ ", does not comply with required size format");
		}
		return res;
	}
}
