package com.lecoffretderachel.ordersmanager.editors.inventory;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class InventoryView {
	JFrame frame = new JFrame();
	JTable table = new JTable();
	JButton btnAddEntry = new JButton("Ajouter");
	JButton btnDeleteEntry = new JButton("Supprimer");
	
	public InventoryView() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
	}
	
    private void createGUI() {
		table.setFont(new Font("", 0, 16));
		table.setRowHeight(30);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setBounds(0, 0, 880, 200);
		tablePane.setPreferredSize(tablePane.getSize());
		
		JPanel btnPane = new JPanel();
		btnPane.add(btnAddEntry);
		btnPane.add(btnDeleteEntry);

		frame.setLayout(new BorderLayout());
		frame.add(tablePane, BorderLayout.CENTER);
		frame.add(btnPane, BorderLayout.SOUTH);
        frame.pack();
    }
    
    public void showGUI() {
        frame.setVisible(true);
    }
    
    public int getSelectedRow() {
    	return table.getSelectionModel().getMinSelectionIndex();
    }
	
	public void injectModelIntoTable(AbstractTableModel model) {
		table.setModel(model);
	}
	
	public void addBtnAddEntryListener(ActionListener listener) {
		btnAddEntry.addActionListener(listener);
	}
	
	public void addBtnDeleteEntryListener(ActionListener listener) {
		btnDeleteEntry.addActionListener(listener);
	}
	
	public void addProductCellEditor(JComboBox box) {
		table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(box));
	}
}
