package com.lecoffretderachel.ordersmanager.imports.order;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class OrderImportView {
	JFrame frame = new JFrame();
	JPanel container = new JPanel();
	JButton btnNext;
	JPanel chooseFilePanel = new JPanel();
	JTextField pathField;
	JButton btnBrowse;
	JTable table = new JTable();
	JScrollPane subOrdersPanel = new JScrollPane(table);
	
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
		createSubOrdersPanel();
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
	
	public void createSubOrdersPanel() {
		table.setFont(new Font("", 0, 16));
		table.setRowHeight(30);
		subOrdersPanel.setBounds(0, 0, 880, 200);
		subOrdersPanel.setPreferredSize(subOrdersPanel.getSize());
	}
	
	public void addSubOrdersPanel(List subOrders, int maxItemCount) {
		table.setModel(new SubOrderTableModel(subOrders, maxItemCount));
		container.add(subOrdersPanel, BorderLayout.NORTH);
	}
	
	public void addSubOrdersProductEditor(TableCellEditor editor) {
		for(int i = 2; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellEditor(editor);
		}
	}
	
	public void addSubOrdersProductRenderer(TableCellRenderer renderer) {
		for(int i = 2; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}
	}
	
	public void removeSubOrdersPanel() {
		container.remove(subOrdersPanel);
		container.repaint();
	}
	
	public void printErrorDialog(String message) {
		JOptionPane.showMessageDialog(frame , message, "Error",
		        JOptionPane.ERROR_MESSAGE);
	}
}
