package com.lecoffretderachel.ordersmanager.editors.order;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderController {
	final OrderView theView;
	final OrderTableModel theModel;
	
	public OrderController(OrderView theView, OrderTableModel theModel) {
		this.theView = theView;
		this.theModel = theModel;

		theModel.fillData();
		theView.injectModelIntoTable(theModel);
		theView.addBtnAddEntryListener(new BtnAddEntryListener());
		theView.addBtnDeleteEntryListener(new BtnDeleteEntryListener());
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
