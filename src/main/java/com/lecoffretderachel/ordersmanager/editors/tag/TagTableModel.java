package com.lecoffretderachel.ordersmanager.editors.tag;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lecoffretderachel.ordersmanager.model.Tag;
import com.lecoffretderachel.ordersmanager.service.TagService;

@Component
public class TagTableModel extends AbstractTableModel {
	final TagService tagService;
    String[] columnNames;
    ArrayList data;

    @Autowired
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
        tagService.updateTag(elem);
        fireTableCellUpdated(row, col);
    }
    
    public void fillData() {
    	data = new ArrayList(tagService.listTags());
    }
    
    public void addEmptyRow() {
    	int index = data.size();
    	Tag newEntry = new Tag();
    	newEntry.setName("newTag");
    	tagService.persistTag(newEntry);
    	data.add(newEntry);
    	fireTableRowsInserted(index, index);
    }
    
    public void deleteSelectedRow(int atIndex) {
    	Tag elem = (Tag) data.get(atIndex);
    	tagService.deleteTag(elem);
    	data.remove(atIndex);
    	fireTableRowsDeleted(atIndex, atIndex);
    }
}
