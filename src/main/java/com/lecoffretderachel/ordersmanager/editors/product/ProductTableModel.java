package com.lecoffretderachel.ordersmanager.editors.product;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lecoffretderachel.ordersmanager.model.Product;
import com.lecoffretderachel.ordersmanager.service.ProductService;

@Component
public class ProductTableModel extends AbstractTableModel {

	@Autowired
	ProductService productService;
    private String[] columnNames;
    private ArrayList data;

    public ProductTableModel() {
    	columnNames = new String[] {"id", "name", "color", "fabric", "style"};
    }
    
    public void fillData() {
    	data = new ArrayList(productService.listProducts());
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
