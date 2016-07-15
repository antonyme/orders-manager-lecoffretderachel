package com.lecoffretderachel.ordersmanager.editors.product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class ProductController {
	final ProductView theView;
	final ProductTableModel theModel;
	
	public ProductController(ProductView theView, ProductTableModel theModel) {
		this.theView = theView;
		this.theModel = theModel;

		theModel.fillData();
		theView.injectModelIntoTable(theModel);
		theView.addBtnAddEntryListener(new BtnAddEntryListener());
		theView.addBtnDeleteEntryListener(new BtnDeleteEntryListener());
		theView.addTagColumnCellEditor();
	}
	
	public void show() {
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
