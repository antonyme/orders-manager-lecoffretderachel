package com.lecoffretderachel.ordersmanager.imports.order;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

public class OrderImportController {
	OrderImportView theView;
	
	int state = 0;
	String filePath;
	
	List<OrderCSV> orderCSVList;
	List<OrderModelBuilder> subOrderBuilderList;
	List<OrderModelBuilder> directOrderBuilderList;
	
	
	public OrderImportController(OrderImportView theView) {
		this.theView = theView;
		
		theView.addBtnBrowseListener(new BtnBrowseListener());
		theView.addBtnNextListener(new BtnNextListener());
	}
	
	public void show() {
		theView.showGUI();
	}
	
	private String askFileLocation() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File", "csv");
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(new File("C:"
				+ File.separator + "Users"
				+ File.separator + "GUILLAUME"
				+ File.separator + "Google Drive"
				+ File.separator + "Rachel"
				+ File.separator + "CSV import"
				+ File.separator + "orders exports"));
		int returnValue = chooser.showOpenDialog(theView.getContainer());
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			state = 1;
			return chooser.getSelectedFile().getAbsolutePath();
		}
		else {
			return null;
		}
	}
	
	private void openAndReadFile() throws IllegalArgumentException {
	    try(InputStreamReader fileReader = new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8)) {
	    	orderCSVList = OrderCSVParser.parseFile(fileReader);
	    } catch (IOException e) {
			throw new IllegalArgumentException("Cannot access file at : " + filePath);
		}
	}
	
	private void parseExtraInfos() throws IllegalArgumentException {
		for(OrderCSV orderCSV : orderCSVList) {
			orderCSV.setItemModelList(OrderCSVParser.parseItems(orderCSV));
		}
	}
	
	private void splitSubAndDirectOrders() {
		subOrderBuilderList = new ArrayList<>();
		directOrderBuilderList = new ArrayList<>();
		for(OrderCSV orderCSV : orderCSVList) {
			OrderModelBuilder subscription = new OrderModelBuilder(orderCSV, true);
			OrderModelBuilder direct = new OrderModelBuilder(orderCSV, false);
			if(!subscription.getProductInclude().isEmpty()) {
				subOrderBuilderList.add(subscription);
			}
			if(!direct.getProductInclude().isEmpty()) {
				directOrderBuilderList.add(direct);
			}
		}
	}
	
	private void matchProducts() {
		
	}
	
	private void matchClients() {
		
	}
	
	class BtnBrowseListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			filePath = askFileLocation();
			if(filePath != null) {
				theView.setPath(filePath);
				theView.setBtnNextState(true);
			}
		}
	}
	
	class BtnNextListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(state) {
			case 1:
				try {
					openAndReadFile();
					parseExtraInfos();
					splitSubAndDirectOrders();
					matchProducts();
					matchClients();
				}
				catch(IllegalArgumentException ex) {
					theView.printErrorDialog(ex.getMessage());
					break;
				}
				state = 2;
				theView.setBtnNextState(false);
				theView.removeChooseFilePanel();
				break;
			case 2:
				break;
			default:
				break;
			}
		}
	}
}