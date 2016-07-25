package com.lecoffretderachel.ordersmanager.imports.order;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class OrderImportView {
	JFrame frame = new JFrame();
	JPanel container = new JPanel();
	JButton btnNext;
	JPanel chooseFilePanel = new JPanel();
	JTextField pathField;
	JButton btnBrowse;
	
	public OrderImportView() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createGUI();
				addChooseFilePanel();
			}
		});
	}
	
	public JPanel getContainer() {
		return container;
	}
	
	public void setBtnNextState(Boolean enable) {
		btnNext.setEnabled(enable);
	}
	
	public String getPath() {
		return pathField.getText();
	}
	
	public void setPath(String path) {
		pathField.setText(path);
	}
	
	public void showGUI() {
		frame.setVisible(true);
	}
	
	public void addBtnNextListener(ActionListener listener) {
		btnNext.addActionListener(listener);
	}
	
	public void addBtnBrowseListener(ActionListener listener) {
		btnBrowse.addActionListener(listener);
	}
	
	private void createGUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		container.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(container);
		container.setLayout(new BorderLayout(0, 0));
		
		JPanel bottomPanel = new JPanel();
		container.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnNext = new JButton("Next");
		bottomPanel.add(btnNext);
		
		createChooseFilePanel();
	}
	
	public void createChooseFilePanel() {
		chooseFilePanel.setLayout(new BoxLayout(chooseFilePanel, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("File path : ");
		chooseFilePanel.add(lblNewLabel);
		
		pathField = new JTextField();
		chooseFilePanel.add(pathField);
		pathField.setColumns(10);
		
		btnBrowse = new JButton("Browse...");
		chooseFilePanel.add(btnBrowse);
	}
	
	public void addChooseFilePanel() {
		container.add(chooseFilePanel, BorderLayout.NORTH);
	}
	
	public void removeChooseFilePanel() {
		container.remove(chooseFilePanel);
		container.repaint();
	}
	
	public void printErrorDialog(String message) {
		JOptionPane.showMessageDialog(frame , message, "Error",
		        JOptionPane.ERROR_MESSAGE);
	}
}
