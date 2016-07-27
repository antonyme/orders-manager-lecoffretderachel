package com.lecoffretderachel.ordersmanager.editors.order;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.lecoffretderachel.ordersmanager.model.Order;
import com.lecoffretderachel.ordersmanager.service.OrderService;

public class OrderTableModel extends AbstractTableModel {
	final OrderService orderService;
    String[] columnNames;
    List data = new ArrayList<>();

    public OrderTableModel(OrderService orderService) {
    	this.orderService = orderService;
    	columnNames = Order.getHeaders();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
    	return Order.getClass(columnIndex);
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
        Order value = (Order) data.get(row);
        return value.get(col);
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
    	return false;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
    	Order elem = (Order) data.get(row);
        elem.set(col, value);
        orderService.update(elem);
        fireTableCellUpdated(row, col);
    }
    
    public void fillData() {
    	data.addAll(orderService.list());
    }
    
    public void addEmptyRow() {
    	int index = data.size();
    	Order newEntry = new Order();
    	orderService.persist(newEntry);
    	data.add(newEntry);
    	fireTableRowsInserted(index, index);
    }
    
    public void deleteSelectedRow(int atIndex) {
    	Order elem = (Order) data.get(atIndex);
    	orderService.delete(elem);
    	data.remove(atIndex);
    	fireTableRowsDeleted(atIndex, atIndex);
    }
}
