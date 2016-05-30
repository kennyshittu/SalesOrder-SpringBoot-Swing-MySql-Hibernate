package com.dev.frontend.panels;


public interface PanelSwitcher 
{
	public void switchTo(String name);
	public HasBusinessPresenter getPanelOfClass(String name);

}
