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
import org.springframework.stereotype.Component;

import com.lecoffretderachel.ordersmanager.model.Tag;
import com.lecoffretderachel.ordersmanager.service.TagService;

@Component
public class TagEditor extends AbstractCellEditor
		implements TableCellEditor,
					ActionListener {
	TagService tagService;
	List<Tag> currentTags;
	JButton button;
	TagChooser tagChooser;
	JDialog dialog;
	protected static final String EDIT = "edit";

	@Autowired
	public TagEditor(TagService tagService) {
		this.tagService = tagService;
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
		tagChooser = new TagChooser();
		dialog = TagChooser.createDialog(button, "Pick tags", tagChooser, this);
	}

	public void actionPerformed(ActionEvent e) {
		if (EDIT.equals(e.getActionCommand())) {
			//The user has clicked the cell, so
			//bring up the dialog.
			button.setSelected(false);
			tagChooser.setDestinationElements(currentTags);
			tagChooser.setSourceElements(tagService.listTags());
			tagChooser.removeSourceElements(currentTags);
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
		currentTags = (List<Tag>) value;
		return button;
	}
}