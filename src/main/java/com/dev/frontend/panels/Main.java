package com.dev.frontend.panels;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.dev.Application;
import com.dev.frontend.panels.edit.EditContainer;
import com.dev.frontend.panels.edit.EditCustomer;
import com.dev.frontend.panels.edit.EditProduct;
import com.dev.frontend.panels.edit.EditSalesOrder;
import com.dev.frontend.panels.list.CustomerDataModel;
import com.dev.frontend.panels.list.ListContentPanel;
import com.dev.frontend.panels.list.ProductDataModel;
import com.dev.frontend.panels.list.SalesOrderDataModel;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@org.springframework.stereotype.Component
public class Main implements PanelSwitcher {
	private JFrame frame;
	private JPanel panel;
	private HashMap<String, HasBusinessPresenter> containersMap = new HashMap<String, HasBusinessPresenter>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
							.headless(false)
							.run(args);

					Main window = context.getBean(Main.class);

//					Main window = new Main();
					MenuPanel menuPanel = new MenuPanel(window);
					window.panel.add(menuPanel, MenuPanel.class.getName());
					EditContainer productContainer = new EditContainer(new EditProduct(),
							window);
					EditContainer customerContainer = new EditContainer(new EditCustomer(),
							window);
					EditContainer salesOrderContainer = new EditContainer(new EditSalesOrder(),
							window);
					
					window.addPanel(productContainer, EditProduct.class.getName());
					window.addPanel(customerContainer, EditCustomer.class.getName());
					window.addPanel(salesOrderContainer, EditSalesOrder.class.getName());
					window.addPanel(new ListContentPanel(window,new CustomerDataModel()),CustomerDataModel.class.getName());
					window.addPanel(new ListContentPanel(window,new ProductDataModel()),ProductDataModel.class.getName());
					window.addPanel(new ListContentPanel(window,new SalesOrderDataModel()),SalesOrderDataModel.class.getName());
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	void addPanel(HasBusinessPresenter container,String name)
	{
		containersMap.put(name, container);
		panel.add((Component) container,name);
	}
	public Main() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel(new CardLayout());
		frame.add(panel);
	}

	public void switchTo(String name) {
		CardLayout layout = (CardLayout) panel.getLayout();
		HasBusinessPresenter container = getPanelOfClass(name);
		if(container!=null)
		{
			container.getBusinessPresenter().clear();
			container.getBusinessPresenter().onInit();
		}
		layout.show(panel, name);
	}

	public HasBusinessPresenter getPanelOfClass(String name) {
		return containersMap.get(name);
	}
}
