package com.lecoffretderachel.ordersmanager.editors.customer;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.lecoffretderachel.ordersmanager.editors.DualListEditor;

public class CustomerView {
	JFrame frame = new JFrame();
	JTable table = new JTable();
	JButton btnAddEntry = new JButton("Ajouter");
	JButton btnDeleteEntry = new JButton("Supprimer");
	DualListEditor tagEditor;
	DualListEditor productEditor;
	
	public CustomerView(DualListEditor tagEditor, DualListEditor productEditor) {
		this.tagEditor = tagEditor;
		this.productEditor = productEditor;
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

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	
	public void addTagColumnCellEditor() {
		table.getColumnModel().getColumn(5).setCellEditor(tagEditor);
		table.getColumnModel().getColumn(6).setCellEditor(tagEditor);
		table.getColumnModel().getColumn(7).setCellEditor(productEditor);
		table.getColumnModel().getColumn(8).setCellEditor(productEditor);
	}
}
