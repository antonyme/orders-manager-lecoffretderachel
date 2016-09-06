package com.lecoffretderachel.ordersmanager.imports.order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;

import com.lecoffretderachel.ordersmanager.model.OrderProduct;

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
/*		chooser.setCurrentDirectory(new File("C:"
				+ File.separator + "Users"
				+ File.separator + ""
				+ File.separator + "orders exports"));*/
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
	    try {
	    	theModel.setOrderCSVList(OrderCSVParser.parseFile(filePath));
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
		try {
			theModel.matchDirectOrdersProducts();
		}
		catch(IllegalArgumentException e) {
			theView.printErrorDialog(e.getMessage());
		}
	}
	
	private void matchClients() {
		try {
			theModel.matchOrdersClient();
		}
		catch(IllegalArgumentException e) {
			theView.printErrorDialog(e.getMessage());
		}
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
					theModel.persistDirectOrders();
					theModel.writeDirectLogs();
				}
				catch(IllegalArgumentException ex) {
					ex.printStackTrace();
					theView.printErrorDialog(ex.getMessage());
					break;
				}
				state = 2;
				theModel.suggestSubOrderProducts();
				theView.setBtnNextState(false);
				theView.removeChooseFilePanel();
				theView.addSubOrdersPanel(theModel.getSubOrderList(), theModel.getSubOrderMaxItemCount());
				theView.addSubOrdersProductEditor(
						new DefaultCellEditor(
								new JComboBox<>(theModel.getPossibleOrderProduct().toArray())));
				theView.addSubOrdersProductRenderer(new SubOrdersProductRenderer());
				theView.setBtnNextState(true);
				break;
			case 2:
				theModel.persistSubOrders();
				theModel.writeSubLogs();
				theView.hideGUI();
				break;
			default:
				break;
			}
		}
	}
	
	class SubOrdersProductRenderer extends DefaultTableCellRenderer {
		public SubOrdersProductRenderer() {
			super();
		}
		
		@Override
		public void setValue(Object value) {
			if(value == null) {
				setText("");
			}
			else {
				OrderProduct elem = (OrderProduct) value;
				setText(elem.getProduct().getName() + " (" + elem.getProductSize() + ")");	
			}
		}
	}
}