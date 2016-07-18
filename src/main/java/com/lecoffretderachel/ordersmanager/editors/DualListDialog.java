package com.lecoffretderachel.ordersmanager.editors;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class DualListDialog extends JPanel {
	private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);
	private static final String ADD_BUTTON_LABEL = "Add >>";
	private static final String REMOVE_BUTTON_LABEL = "<< Remove";
	private static final String DEFAULT_SOURCE_CHOICE_LABEL = "Available";
	private static final String DEFAULT_DEST_CHOICE_LABEL = "Added";

	private JLabel sourceLabel;
	private JList sourceList;
	private SortedListModel sourceListModel;
	private JList destList;
	private SortedListModel destListModel;
	private JLabel destLabel;
	private JButton addButton;
	private JButton removeButton;

	public DualListDialog() {
		initScreen();
	}

	public void clearSourceListModel() {
		sourceListModel.clear();
	}

	public void clearDestinationListModel() {
		destListModel.clear();
	}

	public void addSourceElements(List newValue) {
		fillListModel(sourceListModel, newValue);
	}

	public void removeSourceElements(List oldValue) {
		for (int i = oldValue.size() - 1; i >= 0; --i) {
			sourceListModel.removeElement(oldValue.get(i));
		}
	}

	public void setSourceElements(List newValue) {
		clearSourceListModel();
		addSourceElements(newValue);
	}

	public void addDestinationElements(List newValue) {
		fillListModel(destListModel, newValue);
	}

	public void removeDestinationElements(List oldValue) {
		for (int i = oldValue.size() - 1; i >= 0; --i) {
			destListModel.removeElement(oldValue.get(i));
		}
	}

	public void setDestinationElements(List newValue) {
		clearDestinationListModel();
		addDestinationElements(newValue);
	}

	private void fillListModel(SortedListModel model, List newValues) {
		int size = newValues.size();
		for (int i = 0; i < size; i++) {
			model.add(newValues.get(i));
		}
	}

	public void addSourceElements(Object newValue[]) {
		fillListModel(sourceListModel, newValue);
	}

	public void setSourceElements(Object newValue[]) {
		clearSourceListModel();
		addSourceElements(newValue);
	}

	public void addDestinationElements(Object newValue[]) {
		fillListModel(destListModel, newValue);
	}

	private void fillListModel(SortedListModel model, Object newValues[]) {
		model.addAll(newValues);
	}

	public Iterator sourceIterator() {
		return sourceListModel.iterator();
	}

	public Iterator destinationIterator() {
		return destListModel.iterator();
	}
	
	public List getDestinationList() {
		List res = new ArrayList<>();
		for(int i = 0; i < destListModel.getSize(); i++) {
			res.add(destListModel.getElementAt(i));
		}
		return res;
	}
	
	private void clearSourceSelected() {
		List selected = sourceList.getSelectedValuesList();
		removeSourceElements(selected);
		sourceList.getSelectionModel().clearSelection();
	}

	private void clearDestinationSelected() {
		List selected = destList.getSelectedValuesList();
		removeDestinationElements(selected);
		destList.getSelectionModel().clearSelection();
	}

	private void initScreen() {
		setBorder(BorderFactory.createEtchedBorder());
		setLayout(new GridBagLayout());
		sourceLabel = new JLabel(DEFAULT_SOURCE_CHOICE_LABEL);
		sourceListModel = new SortedListModel();
		sourceList = new JList(sourceListModel);
		add(sourceLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				EMPTY_INSETS, 0, 0));
		add(new JScrollPane(sourceList), new GridBagConstraints(0, 1, 1, 5, .5, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, EMPTY_INSETS, 0, 0));

		addButton = new JButton(ADD_BUTTON_LABEL);
		add(addButton, new GridBagConstraints(1, 2, 1, 2, 0, .25, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				EMPTY_INSETS, 0, 0));
		addButton.addActionListener(new AddListener());
		removeButton = new JButton(REMOVE_BUTTON_LABEL);
		add(removeButton, new GridBagConstraints(1, 4, 1, 2, 0, .25, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(0, 5, 0, 5), 0, 0));
		removeButton.addActionListener(new RemoveListener());

		destLabel = new JLabel(DEFAULT_DEST_CHOICE_LABEL);
		destListModel = new SortedListModel();
		destList = new JList(destListModel);
		add(destLabel, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				EMPTY_INSETS, 0, 0));
		add(new JScrollPane(destList), new GridBagConstraints(2, 1, 1, 5, .5, 1.0, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, EMPTY_INSETS, 0, 0));
	}

	public static JDialog createDialog(JButton button, String title, DualListDialog dual, ActionListener listener) {
		JButton ok = new JButton("Ok");
		ok.addActionListener(listener);
		JDialog dialog = new JDialog((JFrame) SwingUtilities.getRoot(button), title, true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.getContentPane().add(dual, BorderLayout.CENTER);
		dialog.getContentPane().add(ok, BorderLayout.SOUTH);
		dialog.setSize(400, 400);
		return dialog;
	}

	private class AddListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			List selected = sourceList.getSelectedValuesList();
			addDestinationElements(selected);
			clearSourceSelected();
		}
	}

	private class RemoveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			List selected = destList.getSelectedValuesList();
			addSourceElements(selected);
			clearDestinationSelected();
		}
	}
}

class SortedListModel extends AbstractListModel {

	SortedSet model;

	public SortedListModel() {
		model = new TreeSet();
	}

	public int getSize() {
		return model.size();
	}

	public Object getElementAt(int index) {
		return model.toArray()[index];
	}

	public void add(Object element) {
		if (model.add(element)) {
			fireContentsChanged(this, 0, getSize());
		}
	}

	public void addAll(Object elements[]) {
		Collection c = Arrays.asList(elements);
		model.addAll(c);
		fireContentsChanged(this, 0, getSize());
	}

	public void clear() {
		model.clear();
		fireContentsChanged(this, 0, getSize());
	}

	public boolean contains(Object element) {
		return model.contains(element);
	}

	public Object firstElement() {
		return model.first();
	}

	public Iterator iterator() {
		return model.iterator();
	}

	public Object lastElement() {
		return model.last();
	}

	public boolean removeElement(Object element) {
		boolean removed = model.remove(element);
		if (removed) {
			fireContentsChanged(this, 0, getSize());
		}
		return removed;
	}
}
