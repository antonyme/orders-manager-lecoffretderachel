package com.lecoffretderachel.ordersmanager.editors.customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerController {
	final CustomerView theView;
	final CustomerTableModel theModel;
	
	public CustomerController(CustomerView theView, CustomerTableModel theModel) {
		this.theView = theView;
		this.theModel = theModel;

		theView.injectModelIntoTable(theModel);
		theView.addBtnAddEntryListener(new BtnAddEntryListener());
		theView.addBtnDeleteEntryListener(new BtnDeleteEntryListener());
		theView.addTagColumnCellEditor();
	}
	
	public void show() {
		theModel.fillData();
		theView.showGUI();
	}
	
	class BtnAddEntryListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theModel.addEmptyRow();
		}	
	}
	
	class BtnDeleteEntryListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theModel.deleteSelectedRow(theView.getSelectedRow());
		}
	}
}
