package com.salesorderapp.frontend.panels.edit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.salesorderapp.backend.entities.SalesOrderEntity;
import com.salesorderapp.frontend.panels.ComboBoxItem;
import com.salesorderapp.frontend.services.Services;
import com.salesorderapp.frontend.services.Utils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class EditSalesOrder extends EditContentPanel
{
	private static final long serialVersionUID = -8971249970444644844L;
	private JTextField txtOrderNum = new JTextField();
	private JTextField txtTotalPrice = new JTextField();
	private JComboBox<ComboBoxItem> txtCustomer = new JComboBox<ComboBoxItem>();
	private JTextField txtQuantity = new JTextField();
	private JButton btnAddLine = new JButton("Add");
	private JComboBox<ComboBoxItem> txtProduct = new JComboBox<ComboBoxItem>();

	private List<Map<String, Object>> addedProducts = Lists.newArrayList();

	private DefaultTableModel defaultTableModel = new DefaultTableModel(new String[] { "Product", "Qty", "Price", "Total" }, 0)
	{

		private static final long serialVersionUID = 7058518092777538239L;

		@Override
		public boolean isCellEditable(int row, int column)
		{
			return false;
		}
	};
	private JTable lines = new JTable(defaultTableModel);

	public EditSalesOrder()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(new JLabel("Order Number"), gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		gbc.weightx = 0.5;
		add(txtOrderNum, gbc);

		txtOrderNum.setColumns(10);
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 3;
		gbc.gridy = 0;
		add(new JLabel("Customer"), gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		txtCustomer.setSelectedItem("Select a Customer");
		add(txtCustomer, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(new JLabel("Total Price"), gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		gbc.weightx = 0.5;
		add(txtTotalPrice, gbc);
		txtTotalPrice.setColumns(10);
		txtTotalPrice.setEditable(false);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		add(new JLabel("Details"), gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 6;
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(10, 1));
		gbc.fill = GridBagConstraints.BOTH;
		add(separator, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(new JLabel("Product"), gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		txtProduct.setSelectedItem("Select a Product");
		add(txtProduct, gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 3;
		gbc.gridy = 3;
		add(new JLabel("Quantity"), gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 4;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		add(txtQuantity, gbc);
		txtQuantity.setColumns(10);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 5, 2, 5);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		add(btnAddLine, gbc);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 7;
		gbc.gridheight = 8;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.ipady = 40;
		gbc.fill = GridBagConstraints.BOTH;
		JScrollPane scrollPane = new JScrollPane(lines, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		lines.setFillsViewportHeight(true);
		add(scrollPane, gbc);
		btnAddLine.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				addRow();
			}
		});

	}

	public void addRow()
	{
		ComboBoxItem comboBoxItem = (ComboBoxItem) txtProduct.getSelectedItem();
		if (comboBoxItem == null)
		{
			JOptionPane.showMessageDialog(this, "You must select a product");
			return;

		}
		String productCode = comboBoxItem.getKey();
		double price = Services.getProductPrice(productCode);
		Integer qty = 0;
		try
		{
			qty = Integer.parseInt(txtQuantity.getText());
			Map<String, Object> product = Maps.newHashMap();
			product.put("id", productCode);
			product.put("quantity", Double.parseDouble(txtQuantity.getText()));
			product.put("price", price);

			addedProducts.add(product);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(this, "Invalid number format in Qty field");
			return;
		}
		double totalPrice = qty * price;
		defaultTableModel.addRow(new String[] { productCode, "" + qty, "" + price, "" + totalPrice });
		double currentValue = Utils.parseDouble(txtTotalPrice.getText());
		currentValue += totalPrice;
		txtTotalPrice.setText("" + currentValue);
	}
	
	public boolean bindToGUI(Object o) {
		// TODO by the candidate
		/*
		 * This method use the object returned by Services.readRecordByCode and should map it to screen widgets 
		 */
		if(o != null) {
			SalesOrderEntity salesOrderEntity = (SalesOrderEntity) o;
			defaultTableModel.setRowCount(0);
			addedProducts = new ArrayList<>();
			for (Map<String, Object> product : salesOrderEntity.getProducts()) {
				double totalPrice = (Double) product.get("quantity") * (Double) product.get("price");
				defaultTableModel.addRow(new String[]{
						String.valueOf(product.get("id")),
						"" + String.valueOf(product.get("quantity")),
						"" + String.valueOf(product.get("price")),
						"" + totalPrice});
			}

			txtOrderNum.setText(String.valueOf(salesOrderEntity.getOrderNumber()));
			txtCustomer.setSelectedItem(new ComboBoxItem(
							String.valueOf(salesOrderEntity.getCustomer().get("id")),
							String.valueOf(salesOrderEntity.getCustomer().get("name")))
			);
			txtTotalPrice.setText(salesOrderEntity.getTotalPrice().toString());
		} else {
			return false;
		}
		return true;
	}

	public Object guiToObject() {
		// TODO by the candidate
		/*
		 * This method collect values from screen widgets and convert them to object of your type
		 * This object will be used as a parameter of method Services.save
		 */
		if (defaultTableModel.getRowCount() < 1){
			JOptionPane.showMessageDialog(this, "You must add at least one order.");
			return null;
		}
		ComboBoxItem productComboBoxItem = (ComboBoxItem) txtProduct.getSelectedItem();
		ComboBoxItem customerComboBoxItem = (ComboBoxItem) txtCustomer.getSelectedItem();
		if (productComboBoxItem == null || customerComboBoxItem == null)
		{
			JOptionPane.showMessageDialog(this, "You must select a product and a customer");
			return null;
		}

		Map<String, Object> customer  = Maps.newHashMap();
		customer.put("id", customerComboBoxItem.getKey());
		customer.put("name", customerComboBoxItem.getValue());

		// create products map list
		return new SalesOrderEntity(
				Long.parseLong(txtOrderNum.getText()),
				customer,
				addedProducts,
				Double.parseDouble(txtTotalPrice.getText()));
	}

	public int getObjectType()
	{
		return Services.TYPE_SALESORDER;
	}

	public String getCurrentCode()
	{
		return txtOrderNum.getText();
	}

	public void clear()
	{
		txtOrderNum.setText("");
		txtCustomer.removeAllItems();
		txtProduct.removeAllItems();
		txtQuantity.setText("");
		txtTotalPrice.setText("");
		defaultTableModel.setRowCount(0);
		addedProducts = new ArrayList<>();
	}

	public void onInit()
	{
		List<ComboBoxItem> customers = Services.listCurrentRecordRefernces(Services.TYPE_CUSTOMER);
		for (ComboBoxItem item : customers)
			txtCustomer.addItem(item);

		List<ComboBoxItem> products = Services.listCurrentRecordRefernces(Services.TYPE_PRODUCT);
		for (ComboBoxItem item : products)
			txtProduct.addItem(item);
	}
}
