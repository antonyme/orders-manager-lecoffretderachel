package com.lecoffretderachel.ordersmanager.imports.order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OrderImportController {
	OrderImportView theView;
	OrderImportModel theModel;
	
	int state = 0;
	String filePath;
	
	
	public OrderImportController(OrderImportView theView, OrderImportModel theModel) {
		this.theView = theView;
		this.theModel = theModel;
		
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
	    	theModel.setOrderCSVList(OrderCSVParser.parseFile(fileReader));
	    } catch (IOException e) {
			throw new IllegalArgumentException("Cannot access file at : " + filePath);
		}
	}
	
	private void parseExtraInfos() throws IllegalArgumentException {
		for(OrderCSV orderCSV : theModel.getOrderCSVList()) {
			orderCSV.setItemModelList(OrderCSVParser.parseItems(orderCSV));
		}
	}
	
	private void splitSubAndDirectOrders() {
		for(OrderCSV orderCSV : theModel.getOrderCSVList()) {
			OrderModelBuilder subscription = new OrderModelBuilder(orderCSV, true);
			OrderModelBuilder direct = new OrderModelBuilder(orderCSV, false);
			if(!subscription.getProductInclude().isEmpty()) {
				theModel.getOrderBuilderList(true).add(subscription);
			}
			if(!direct.getProductInclude().isEmpty()) {
				theModel.getOrderBuilderList(false).add(direct);
			}
		}
	}
	
	private void matchProducts() {
		Set<String> products = new HashSet();
		theModel.getOrderBuilderList(false).forEach((orderBuilder) -> orderBuilder.getProductInclude().forEach((item) -> products.add(item.getName())));
		System.out.println(products);
		theModel.getOrderBuilderList(true).get(0);
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