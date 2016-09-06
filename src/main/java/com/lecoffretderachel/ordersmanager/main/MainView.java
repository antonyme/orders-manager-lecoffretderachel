package com.lecoffretderachel.ordersmanager.main;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainView {
	JFrame frame = new JFrame();
	JComboBox editorCombo = new JComboBox();
	JButton btnOpenEditor = new JButton("Open");
	JButton btnOrderImport = new JButton("Import new orders");
	JButton btnSaveDB = new JButton("Save current database state");
	
	public MainView() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
	}

	private void createGUI() {
		frame.setBounds(100, 100, 300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblEditor = new JLabel("Editors");
		panel.add(lblEditor);

		editorCombo.addItem(new String("Customer"));
		editorCombo.addItem(new String("Product"));
		editorCombo.addItem(new String("Inventory"));
		editorCombo.addItem(new String("Tag"));
		editorCombo.addItem(new String("Order"));
		panel.add(editorCombo);
		
		panel.add(btnOpenEditor);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_1.add(btnOrderImport);
		panel_1.add(btnSaveDB);
	}
	
	public void showGUI() {
		frame.setVisible(true);
	}
	
	public void addBtnOpenEditorListener(ActionListener listener) {
		btnOpenEditor.addActionListener(listener);
	}
	
	public void addBtnOrderImportListener(ActionListener listener) {
		btnOrderImport.addActionListener(listener);
	}
	
	public void addBtnSaveDBListener(ActionListener listener) {
		btnSaveDB.addActionListener(listener);
	}
	
	public String getEditorComboSelection() {
		return editorCombo.getSelectedItem().toString();
	}
}
