package com.salesorderapp.frontend.panels.edit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Objects;
import javax.swing.*;

import com.salesorderapp.frontend.panels.BusinessPresenter;
import com.salesorderapp.frontend.panels.HasBusinessPresenter;
import com.salesorderapp.frontend.panels.MenuPanel;
import com.salesorderapp.frontend.panels.PanelSwitcher;
import com.salesorderapp.frontend.services.Services;

public class EditContainer extends JPanel implements ActionListener,HasBusinessPresenter {

	private static final long serialVersionUID = -7388350255798160262L;
	private JToolBar toolbar;
	private String SAVE_ACTION = "save";
	private String DELETE_ACTION = "delete";
	private String CLOSE_ACTION = "close";
	private int objectType;
	private EditContentPanel editPanel;
	private PanelSwitcher cardSwitcher;

	public EditContainer(EditContentPanel editPanel,PanelSwitcher cardSwitcher) 
	{
		setLayout(new BorderLayout());
		this.cardSwitcher = cardSwitcher;
		toolbar = new JToolBar();
		add(toolbar, BorderLayout.PAGE_START);
		addToolBarButton(SAVE_ACTION, "Save");
		addToolBarButton(DELETE_ACTION, "Delete");
		addToolBarButton(CLOSE_ACTION, "Close");
		this.editPanel = editPanel;
		this.objectType = editPanel.getObjectType();
		add(editPanel, BorderLayout.CENTER);
	}

	void addToolBarButton(String name, String text) {
		String imgLocation = name + ".png";
		URL imageURL = EditContainer.class.getResource(imgLocation);

		JButton button = new JButton();
		button.setActionCommand(name);
		button.setToolTipText(text);
		button.addActionListener(this);

		if (imageURL != null) {
			ImageIcon defaultIcon = new ImageIcon(imageURL, text);
			button.setIcon(defaultIcon);
		} else {
			button.setText(text);
		}
		toolbar.add(button);
	}

	public void actionPerformed(ActionEvent e) 
	{
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals(SAVE_ACTION))
		{
			Object currentObject = editPanel.guiToObject();
			try
			{
				if(currentObject != null) {
					currentObject = Services.save(currentObject, objectType);
					boolean retValue = editPanel.bindToGUI(currentObject);
					if (!retValue) {
						if (Objects.equals(objectType, 3)) {
							JOptionPane.showMessageDialog(this, "Invalid Order Number, Inventory Level or Customer Credit Limit");
						} else {
							JOptionPane.showMessageDialog(this, "Invalid Data Provided.");
						}
					} else {
						JOptionPane.showMessageDialog(this, "Record Saved");
					}
				}
			}
			catch(Exception ee)
			{
				ee.printStackTrace();
				JOptionPane.showMessageDialog(this, "Record Not Saved");
			}
		}
		else if(actionCommand.equals(DELETE_ACTION))
		{
			boolean retValue = Services.deleteRecordByCode(editPanel.getCurrentCode(), objectType);
			if(retValue)
			{
				goToHome();
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Couldn't Delete Record");
			}
		}
		else if(actionCommand.equals(CLOSE_ACTION))
		{
			goToHome();
		}
	}

	private void goToHome() 
	{
		cardSwitcher.switchTo(MenuPanel.class.getName());
	}

	public BusinessPresenter getBusinessPresenter()
	{
		return editPanel;
	}
}
