package com.dev.frontend.panels;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.dev.frontend.panels.edit.EditCustomer;
import com.dev.frontend.panels.edit.EditProduct;
import com.dev.frontend.panels.edit.EditSalesOrder;
import com.dev.frontend.panels.list.CustomerDataModel;
import com.dev.frontend.panels.list.ProductDataModel;
import com.dev.frontend.panels.list.SalesOrderDataModel;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = -2976633621616925350L;
	private PanelSwitcher cardSwitcher;

	public MenuPanel(PanelSwitcher cardSwitcher1) {
		this.cardSwitcher = cardSwitcher1;
		JButton btnAddCustomer = new JButton("Add Customer");
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardSwitcher.switchTo(EditCustomer.class.getName());
			}
		});
		add(btnAddCustomer);
		btnAddCustomer.setBounds(new Rectangle(50, 50, 30, 20));

		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				cardSwitcher.switchTo(EditProduct.class.getName());
			}
		});

		JButton btnAddSalesOrder = new JButton("Add Sales order");
		btnAddSalesOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				cardSwitcher.switchTo(EditSalesOrder.class.getName());
			}
		});

		add(btnAddProduct);
		add(btnAddSalesOrder);

		JButton btnListCustomers = new JButton("List Customers");
		add(btnListCustomers);
		btnListCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardSwitcher.switchTo(CustomerDataModel.class.getName());
			}
		});

		JButton btnListProducts = new JButton("List Products");
		
		add(btnListProducts);

		JButton btnListOrders = new JButton("List Sales Order");
		add(btnListOrders);
		
		btnListProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardSwitcher.switchTo(ProductDataModel.class.getName());
			}
		});
		btnListOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardSwitcher.switchTo(SalesOrderDataModel.class.getName());
			}
		});

	}
}
