package com.lecoffretderachel.ordersmanager.editors.inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class InventoryController {
	final InventoryView theView;
	final InventoryTableModel theModel;
	
	public InventoryController(InventoryView theView, InventoryTableModel theModel) {
		this.theView = theView;
		this.theModel = theModel;

		theView.injectModelIntoTable(theModel);
		theView.addBtnAddEntryListener(new BtnAddEntryListener());
		theView.addBtnDeleteEntryListener(new BtnDeleteEntryListener());
		theView.addProductCellEditor(new JComboBox<>(theModel.listProduct().toArray()));
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
