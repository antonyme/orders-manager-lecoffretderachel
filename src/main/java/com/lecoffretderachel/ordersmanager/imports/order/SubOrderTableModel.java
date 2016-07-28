package com.lecoffretderachel.ordersmanager.imports.order;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.lecoffretderachel.ordersmanager.model.Order;
import com.lecoffretderachel.ordersmanager.model.OrderProduct;
public class SubOrderTableModel extends AbstractTableModel {
	List data = new ArrayList<>();
	int maxItemCount;

	public SubOrderTableModel(List data, int maxItemCount) {
		this.data = data;
		this.maxItemCount = maxItemCount;
	}

    @Override
    public Class<?> getColumnClass(int columnIndex) {
    	switch(columnIndex) {
    	case 0:
    		return String.class;
    	case 1:
    		return String.class;
    	default:
    		return OrderProduct.class;
    	}
    }

    @Override
    public String getColumnName(int col) {
    	switch(col) {
    	case 0:
    		return "shippingFirstName";
    	case 1:
    		return "shippingLastName";
    	default:
    		return "product " + (col - 1);
    	}
    }

    public int getColumnCount() {
        return 2 + maxItemCount;
    }

    public int getRowCount() {
        return data.size();
    }

    public Object getValueAt(int row, int col) {
        Order value = (Order) data.get(row);
        switch(col) {
        case 0:
        	return value.getShippingFirstName();
        case 1:
        	return value.getShippingLastName();
        default:
        	return value.getOrderProductInclude().get(col - 2);
        }
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
    	if(col < 2) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
    	Order elem = (Order) data.get(row);
        if(col > 1) {
        	OrderProduct toSet = (OrderProduct) value;
        	toSet.setOrder(elem);
        	elem.getOrderProductInclude().set(col - 2, toSet);
        }
        fireTableCellUpdated(row, col);
    }
}
