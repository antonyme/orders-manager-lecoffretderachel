package com.lecoffretderachel.ordersmanager.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.lecoffretderachel.ordersmanager.editors.customer.CustomerController;
import com.lecoffretderachel.ordersmanager.editors.inventory.InventoryController;
import com.lecoffretderachel.ordersmanager.editors.order.OrderController;
import com.lecoffretderachel.ordersmanager.editors.product.ProductController;
import com.lecoffretderachel.ordersmanager.editors.tag.TagController;
import com.lecoffretderachel.ordersmanager.imports.order.OrderImportController;
import com.lecoffretderachel.ordersmanager.logFile.Db2sql;

public class MainController {
	final MainView theView;
	final CustomerController customerController;
	final InventoryController inventoryController;
	final OrderController orderController;
	final ProductController productController;
	final TagController tagController;
	final OrderImportController importController;
	final Db2sql db2sql;
	
	public MainController(MainView theView,
			CustomerController customerController,
			InventoryController inventoryController,
			OrderController orderController,
			ProductController productController,
			TagController tagController,
			OrderImportController importController,
			Db2sql db2sql) {
		this.theView = theView;
		this.customerController = customerController;
		this.inventoryController = inventoryController;
		this.orderController = orderController;
		this.productController = productController;
		this.tagController = tagController;
		this.importController = importController;
		this.db2sql = db2sql;
		
		theView.addBtnOpenEditorListener(new BtnOpenEditorListener());
		theView.addBtnOrderImportListener(new BtnOrderImportListener());
		theView.addBtnSaveDBListener(new BtnSaveDBListener());
	}
	
	public void show() {
		theView.showGUI();
	}
	
	class BtnOpenEditorListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(theView.getEditorComboSelection()) {
			case "Customer":
				customerController.show();
				break;
			case "Product":
				productController.show();
				break;
			case "Inventory":
				inventoryController.show();
				break;
			case "Tag":
				tagController.show();
				break;
			case "Order":
				orderController.show();
				break;
			}
		}
	}
	
	class BtnOrderImportListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			importController.show();
		}
	}
	
	class BtnSaveDBListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			db2sql.dumpDatabase(
					ZonedDateTime.now(
					ZoneId.of("America/Montreal"))
					.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH'h'mm'.'ss's'"))
			);
		}
	}
}
