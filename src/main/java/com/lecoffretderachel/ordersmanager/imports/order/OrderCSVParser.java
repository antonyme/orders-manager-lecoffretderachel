package com.lecoffretderachel.ordersmanager.imports.order;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

public class OrderCSVParser {
	
	public static List<OrderCSV> parseFile(String filePath) throws FileNotFoundException {
		InputStreamReader fileReader1 = new InputStreamReader(
				new FileInputStream(filePath), StandardCharsets.UTF_8
		);
		InputStreamReader fileReader2 = new InputStreamReader(
				new FileInputStream(filePath), StandardCharsets.UTF_8
		);
		
		// fill order bean
		HeaderColumnNameMappingStrategy<OrderCSV> strategy = new HeaderColumnNameMappingStrategy<>();
	    strategy.setType(OrderCSV.class);
	    CsvToBean<OrderCSV> csvToBean = new CsvToBean<>();
	    List<OrderCSV> res = csvToBean.parse(strategy, new CSVReader(fileReader1));
	    
	    // add items to order bean
	    try (CSVReader reader = new CSVReader(fileReader2)) {
			Map<String, Integer> itemRange = getItemsRange(reader.readNext());
			String[] nextLine;
			int line = 0;
			while((nextLine = reader.readNext()) != null) {
				res.get(line).setOrder_items(
						Arrays.asList(nextLine)
						.subList(itemRange.get("first"), itemRange.get("last"))
				);
				line++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return res;
	}
	
	public static Map<String, Integer> getItemsRange(String[] headers) {
		Map<String, Integer> res = new HashMap<>();
		Integer first = Arrays.asList(headers).indexOf("order_item_1");
		Integer last;
		for(last = first; headers[last].contains("order_item"); last++);
		last--;
		res.put("first", first);
		res.put("last", last);
		return res;
	}
	
	public static List<ItemModel> parseItems(OrderCSV orderCSV) throws IllegalArgumentException {
		List<ItemModel> itemModelList = new ArrayList<>();
		orderCSV.getOrder_items().forEach((item) -> 
		{
			if(item != null && !item.equals("")) itemModelList.add(parseItem(item));
		});
		return itemModelList;
	}
	
	private static ItemModel parseItem(String itemCSV) throws IllegalArgumentException {
		ItemModel res = new ItemModel();
		String[] splitItem = itemCSV.split("\\|");
		
		//Abonnement|1|27.5|Size: L|Fréquence: 1 coffret par mois
		//Subscription|1|23.38|Size: Small
		//Abonnement1|1|24.5|je veux: 1 coffret par mois|Taille: p
		//Abonnement|1|27.5
		if(
				(
						splitItem[0].equalsIgnoreCase("Abonnement")
						|| splitItem[0].equalsIgnoreCase("Abonnement1")
						|| splitItem[0].equalsIgnoreCase("Subscription")
				)
				&& splitItem.length >= 3
			) {
			res.setIsSubscription(true);
			res.setName(splitItem[0]);
			res.setQuantity(parseQuantity(itemCSV, splitItem[1]));
			res.setPrice(parsePrice(itemCSV, splitItem[2]));
			if(splitItem.length > 3) {
				if(splitItem[0].equals("Abonnement1")) {
					res.setSize(parseSize(itemCSV, splitItem[4]));
				}
				else {
					res.setSize(parseSize(itemCSV, splitItem[3]));
				}
			}
		}
		//offrir en cadeau|1|33|mode de reception: Electronique|Durée de l'abonnement offert: 1 mois
		else if(splitItem[0].equalsIgnoreCase("offrir en cadeau") && splitItem.length >= 3) {
			res.setIsSubscription(false);
			res.setName(splitItem[0]);
			res.setQuantity(parseQuantity(itemCSV, splitItem[1]));
			res.setPrice(parsePrice(itemCSV, splitItem[2]));
		}
		//Recu en cadeau|1|57|Size: S|Durée de l'abonnement offert: 2 mois -> faire un produit
		else if(splitItem[0].equalsIgnoreCase("Recu en cadeau") && splitItem.length >= 3) {
			res.setIsSubscription(true);
			res.setName(splitItem[0]);
			res.setQuantity(parseQuantity(itemCSV, splitItem[1]));
			res.setPrice(parsePrice(itemCSV, splitItem[2]));
			if(splitItem.length > 3) {
				res.setSize(parseSize(itemCSV, splitItem[3]));
			}
		}
		//pois1|1|14.45|Size: M
		else if(splitItem.length == 4) {
			res.setIsSubscription(false);
			res.setName(splitItem[0]);
			res.setQuantity(parseQuantity(itemCSV, splitItem[1]));
			res.setPrice(parsePrice(itemCSV, splitItem[2]));
			res.setSize(parseSize(itemCSV, splitItem[3]));
		}
		else {
			throw new IllegalArgumentException("Item : " + itemCSV
					+ " does not comply with required item format");
		}
		return res;
	}
	
	private static Integer parseQuantity(String itemCSV, String toParse) throws IllegalArgumentException {
		try {
			return Integer.parseInt(toParse);
		}
		catch (NumberFormatException e) {
			throw new IllegalArgumentException("In item : " + itemCSV + ", quantity : " + toParse
					+ ", is not a valid integer");
		}
	}
	
	private static Double parsePrice(String itemCSV, String toParse) throws IllegalArgumentException {
		try {
			return Double.parseDouble(toParse);
		}
		catch (NumberFormatException e) {
			throw new IllegalArgumentException("In item : " + itemCSV + ", price : " + toParse
					+ ", is not a valid number");
		}
	}
	
	private static String parseSize(String itemCSV, String toParse) throws IllegalArgumentException {
		String[] splitSize = toParse.split(": ");
		if(splitSize.length == 2) {
			return splitSize[1];
		}
		else {
			throw new IllegalArgumentException("In item : " + itemCSV + ", size : " + toParse
					+ ", does not comply with required size format");
		}
	}
}
