package com.lecoffretderachel.ordersmanager.editors.product;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductView {
	JFrame frame;
	JTable table;
	@Autowired
	ProductTableModel model;
	
	public ProductView() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	
    private void createAndShowGUI() {
		frame = new JFrame();
		table = new JTable();
		
		model.fillData();
		table.setModel(model);
		table.setFont(new Font("", 0, 16));
		table.setRowHeight(30);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setBounds(0, 0, 880, 200);
		tablePane.setPreferredSize(tablePane.getSize());
		
		JButton addEntry = new JButton("Ajouter");
		JButton deleteEntry = new JButton("Supprimer");
		JPanel btnPane = new JPanel();
		btnPane.add(addEntry);
		btnPane.add(deleteEntry);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(tablePane, BorderLayout.CENTER);
		frame.add(btnPane, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
}
