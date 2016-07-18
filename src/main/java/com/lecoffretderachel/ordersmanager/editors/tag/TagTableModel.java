package com.lecoffretderachel.ordersmanager.editors.tag;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.lecoffretderachel.ordersmanager.model.Tag;
import com.lecoffretderachel.ordersmanager.service.TagService;

public class TagTableModel extends AbstractTableModel {
	final TagService tagService;
    String[] columnNames;
    ArrayList data;

    public TagTableModel(TagService tagService) {
    	this.tagService = tagService;
    	columnNames = new String[] {"id", "name"};
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
    	return Tag.getClass(columnIndex);
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
        Tag value = (Tag) data.get(row);
        return value.get(col);
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
    	return col != 0;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
    	Tag elem = (Tag) data.get(row);
        elem.set(col, value);
        tagService.update(elem);
        fireTableCellUpdated(row, col);
    }
    
    public void fillData() {
    	data = new ArrayList(tagService.list());
    }
    
    public void addEmptyRow() {
    	int index = data.size();
    	Tag newEntry = new Tag();
    	newEntry.setName("newTag");
    	tagService.persist(newEntry);
    	data.add(newEntry);
    	fireTableRowsInserted(index, index);
    }
    
    public void deleteSelectedRow(int atIndex) {
    	Tag elem = (Tag) data.get(atIndex);
    	tagService.delete(elem);
    	data.remove(atIndex);
    	fireTableRowsDeleted(atIndex, atIndex);
    }
}
