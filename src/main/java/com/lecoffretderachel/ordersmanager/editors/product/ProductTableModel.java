package com.lecoffretderachel.ordersmanager.editors.product;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.lecoffretderachel.ordersmanager.model.Product;
import com.lecoffretderachel.ordersmanager.service.ProductService;
import com.lecoffretderachel.ordersmanager.service.TagService;

public class ProductTableModel extends AbstractTableModel {

	final ProductService productService;
	final TagService tagService;
    String[] columnNames;
    ArrayList data;

    public ProductTableModel(ProductService productService, TagService tagService) {
    	this.productService = productService;
    	this.tagService = tagService;
    	columnNames = Product.getHeaders();
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
    
    @Override
    public boolean isCellEditable(int row, int col) {
    	return col != 0;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
    	Product elem = (Product) data.get(row);
        elem.set(col, value);
        productService.update(elem);
        fireTableCellUpdated(row, col);
    }
    
    public void fillData() {
    	data = new ArrayList(productService.list());
    }
    
    public void addEmptyRow() {
    	int index = data.size();
    	Product newEntry = new Product();
    	productService.persist(newEntry);
    	data.add(newEntry);
    	fireTableRowsInserted(index, index);
    }
    
    public void deleteSelectedRow(int atIndex) {
    	Product elem = (Product) data.get(atIndex);
    	productService.delete(elem);
    	data.remove(atIndex);
    	fireTableRowsDeleted(atIndex, atIndex);
    }
    
    public List getAllTags() {
    	return tagService.list();
    }
}
