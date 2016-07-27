package com.lecoffretderachel.ordersmanager.editors.inventory;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.lecoffretderachel.ordersmanager.model.Inventory;
import com.lecoffretderachel.ordersmanager.model.Product;
import com.lecoffretderachel.ordersmanager.service.InventoryService;
import com.lecoffretderachel.ordersmanager.service.ProductService;

public class InventoryTableModel extends AbstractTableModel {
	final InventoryService inventoryService;
	final ProductService productService;
    String[] columnNames;
    List data = new ArrayList<>();

    public InventoryTableModel(InventoryService inventoryService, ProductService productService) {
    	this.inventoryService = inventoryService;
    	this.productService = productService;
    	columnNames = Inventory.getHeaders();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
    	return Inventory.getClass(columnIndex);
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
        Inventory value = (Inventory) data.get(row);
        return value.get(col);
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
    	return col != 0;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
    	Inventory elem = (Inventory) data.get(row);
        elem.set(col, value);
        inventoryService.update(elem);
        fireTableCellUpdated(row, col);
    }
    
    public void fillData() {
    	data.addAll(inventoryService.list());
    }
    
    public void addEmptyRow() {
    	int index = data.size();
    	Inventory newEntry = new Inventory();
    	newEntry.setProduct(productService.findFirst());
    	inventoryService.persist(newEntry);
    	data.add(newEntry);
    	fireTableRowsInserted(index, index);
    }
    
    public void deleteSelectedRow(int atIndex) {
    	Inventory elem = (Inventory) data.get(atIndex);
    	inventoryService.delete(elem);
    	data.remove(atIndex);
    	fireTableRowsDeleted(atIndex, atIndex);
    }
    
    public List<Product> listProduct() {
    	return productService.list();
    }
}
