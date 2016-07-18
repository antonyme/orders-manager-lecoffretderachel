package com.lecoffretderachel.ordersmanager.editors.customer;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.lecoffretderachel.ordersmanager.model.Customer;
import com.lecoffretderachel.ordersmanager.service.CustomerService;
import com.lecoffretderachel.ordersmanager.service.TagService;

public class CustomerTableModel extends AbstractTableModel {

	final CustomerService customerService;
	final TagService tagService;
    String[] columnNames;
    ArrayList data;

    public CustomerTableModel(CustomerService customerService, TagService tagService) {
    	this.customerService = customerService;
    	this.tagService = tagService;
    	columnNames = Customer.getHeaders();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
    	return Customer.getClass(columnIndex);
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
        Customer value = (Customer) data.get(row);
        return value.get(col);
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
    	return col != 0;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
    	Customer elem = (Customer) data.get(row);
        elem.set(col, value);
        customerService.update(elem);
        fireTableCellUpdated(row, col);
    }
    
    public void fillData() {
    	data = new ArrayList(customerService.list());
    }
    
    public void addEmptyRow() {
    	int index = data.size();
    	Customer newEntry = new Customer();
    	customerService.persist(newEntry);
    	data.add(newEntry);
    	fireTableRowsInserted(index, index);
    }
    
    public void deleteSelectedRow(int atIndex) {
    	Customer elem = (Customer) data.get(atIndex);
    	customerService.delete(elem);
    	data.remove(atIndex);
    	fireTableRowsDeleted(atIndex, atIndex);
    }
    
    public List getAllTags() {
    	return tagService.list();
    }
}
