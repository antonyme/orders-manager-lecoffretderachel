package com.lecoffretderachel.ordersmanager.editors.inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryController {
	final InventoryView theView;
	final InventoryTableModel theModel;
	
	@Autowired
	public InventoryController(InventoryView theView, InventoryTableModel theModel) {
		this.theView = theView;
		this.theModel = theModel;

		theModel.fillData();
		theView.injectModelIntoTable(theModel);
		theView.addBtnAddEntryListener(new BtnAddEntryListener());
		theView.addBtnDeleteEntryListener(new BtnDeleteEntryListener());
		theView.addProductCellEditor(new JComboBox<>(theModel.listProduct().toArray()));
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
