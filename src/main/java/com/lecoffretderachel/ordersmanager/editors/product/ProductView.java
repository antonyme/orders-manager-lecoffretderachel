package com.lecoffretderachel.ordersmanager.editors.product;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.lecoffretderachel.ordersmanager.editors.DualListEditor;

public class ProductView {
	JFrame frame;
	JTable table;
	JButton btnAddEntry = new JButton("Ajouter");
	JButton btnDeleteEntry = new JButton("Supprimer");
	DualListEditor tagEditor;
	
	public ProductView(DualListEditor tagEditor) {
		this.tagEditor = tagEditor;
		try {
			javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
			    public void run() {
			        createGUI();
			    }
			});
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    private void createGUI() {
		frame = new JFrame();
		table = new JTable();
		
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
		table.getColumnModel().getColumn(2).setCellEditor(tagEditor);
	}
}
