package com.dev.frontend.panels;

public class ComboBoxItem
{
	private String key;
	private String value;

	public ComboBoxItem()
	{
	}

	public ComboBoxItem(String key, String value)
	{
		this.key = key;
		this.value = value;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj==null)
			return false;
		if(!(obj instanceof ComboBoxItem))
			return false;
		return getKey().equals(((ComboBoxItem)obj).getKey());
	}

	@Override
	public String toString()
	{
		return value + "(" + key + ")";
	}

}
