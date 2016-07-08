package com.lecoffretderachel.ordersmanager.editors.product;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lecoffretderachel.ordersmanager.model.Product;
import com.lecoffretderachel.ordersmanager.service.ProductService;

@Component
public class ProductView {
	@Autowired
	ProductService productService;
	JFrame frame;
	JTable table;
	MyTableModel model;
	
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
		
		MyTableModel model = new MyTableModel();
		table.setModel(model);
		table.setFont(new Font("", 1, 22));
		table.setRowHeight(30);
		
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(0, 0, 880, 200);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.add(pane);
        frame.pack();
        frame.setVisible(true);
    }
    
    public class MyTableModel extends AbstractTableModel {

        private String[] columnNames;
        private ArrayList data;

        public MyTableModel() {
        	data = new ArrayList(productService.listProducts());
        	columnNames = new String[] {"id", "name", "color", "fabric", "style"};
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
        	return Product.getClass(columnIndex);
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.size();
        }

        public Object getValueAt(int row, int col) {
            Product value = (Product) data.get(row);
            return value.get(col);
        }
        
        public boolean isCellEditable(int row, int col) {
        	return col != 0;
        }
        
        public void setValueAt(Object value, int row, int col) {
        	Product elem = (Product) data.get(row);
            elem.set(col, value);
            productService.updateProduct(elem);
            fireTableCellUpdated(row, col);
        }
    }
}
