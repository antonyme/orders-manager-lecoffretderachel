package com.lecoffretderachel.ordersmanager.editors.product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lecoffretderachel.ordersmanager.Util;
import com.lecoffretderachel.ordersmanager.editors.TagChooser;
import com.lecoffretderachel.ordersmanager.model.Tag;

@Component
public class ProductController {
	final ProductView theView;
	final ProductTableModel theModel;
	
	@Autowired
	public ProductController(ProductView theView, ProductTableModel theModel) {
		this.theView = theView;
		this.theModel = theModel;

		theModel.fillData();
		theView.injectModelIntoTable(theModel);
		theView.addTagEditor(new TagEditor());
		theView.addBtnAddEntryListener(new BtnAddEntryListener());
		theView.addBtnDeleteEntryListener(new BtnDeleteEntryListener());
	}
	
	public void show() {
		theView.showGUI();
	}
	
	class BtnAddEntryListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theModel.addEmptyRow();
		}	
	}
	
	class BtnDeleteEntryListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			theModel.deleteSelectedRow(theView.getSelectedRow());
		}
	}
    
    public class TagEditor extends AbstractCellEditor
    		implements TableCellEditor,
    					ActionListener {
    	Set<Tag> currentTags;
    	JButton button;
    	TagChooser tagChooser;
    	JDialog dialog;
    	protected static final String EDIT = "edit";

    	public TagEditor() {
			button = new JButton();
			button.setActionCommand(EDIT);
			button.addActionListener(this);
			button.setBorderPainted(false);

			//Set up the dialog that the button brings up.
			tagChooser = new TagChooser();
			dialog = TagChooser.createDialog(button, "Pick tags", tagChooser, this);
		}

    	public void actionPerformed(ActionEvent e) {
    		if (EDIT.equals(e.getActionCommand())) {
				//The user has clicked the cell, so
				//bring up the dialog.
				button.setName(Util.setToString(currentTags));
				tagChooser.setSourceElements(theModel.getAllTags());
				tagChooser.clearDestinationListModel();
				dialog.pack();
				dialog.setVisible(true);
				
				fireEditingStopped(); //Make the renderer reappear.

			} else { //User pressed dialog's "OK" button.
				currentTags.clear();
				currentTags.addAll(tagChooser.getDestinationTags());
				dialog.setVisible(false);
			}
		}

		//Implement the one CellEditor method that AbstractCellEditor doesn't.
		public Object getCellEditorValue() {
			return currentTags;
		}
		
		//Implement the one method defined by TableCellEditor.
		public JButton getTableCellEditorComponent(JTable table,
		                            Object value,
		                            boolean isSelected,
		                            int row,
		                            int column) {
			currentTags = (Set<Tag>) value;
			return button;
		}
    }
}
