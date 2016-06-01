package com.salesorderapp.frontend.panels.edit;

import javax.swing.*;

import com.salesorderapp.frontend.panels.BusinessPresenter;

public abstract class EditContentPanel extends JPanel implements BusinessPresenter
{
	private static final long serialVersionUID = 4495835331993612718L;
	public abstract int getObjectType();
	public abstract String getCurrentCode();
}
