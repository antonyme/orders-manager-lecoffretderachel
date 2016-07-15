package com.lecoffretderachel.ordersmanager.editors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lecoffretderachel.ordersmanager.service.ListService;

public class DualListEditor extends AbstractCellEditor
		implements TableCellEditor,
					ActionListener {
	ListService service;
	List currentList;
	JButton button;
	DualListDialog dualListDialog;
	JDialog dialog;
	protected static final String EDIT = "edit";

	public DualListEditor(ListService service) {
		this.service = service;
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
	}
	
	private void createGUI() {
		button = new JButton();
		button.setActionCommand(EDIT);
		button.addActionListener(this);
		button.setBorderPainted(false);

		//Set up the dialog that the button brings up.
		dualListDialog = new DualListDialog();
		dialog = DualListDialog.createDialog(button, "Pick tags", dualListDialog, this);
	}

	public void actionPerformed(ActionEvent e) {
		if (EDIT.equals(e.getActionCommand())) {
			//The user has clicked the cell, so
			//bring up the dialog.
			button.setSelected(false);
			dualListDialog.setDestinationElements(currentList);
			dualListDialog.setSourceElements(service.list());
			dualListDialog.removeSourceElements(currentList);
			dialog.pack();
			dialog.setVisible(true);
			
			fireEditingStopped(); //Make the renderer reappear.

		} else { //User pressed dialog's "OK" button.
			currentList.clear();
			currentList.addAll(dualListDialog.getDestinationTags());
			dialog.setVisible(false);
		}
	}

	//Implement the one CellEditor method that AbstractCellEditor doesn't.
	public Object getCellEditorValue() {
		return currentList;
	}
	
	//Implement the one method defined by TableCellEditor.
	public JButton getTableCellEditorComponent(JTable table,
	                            Object value,
	                            boolean isSelected,
	                            int row,
	                            int column) {
		currentList = (List) value;
		return button;
	}
}