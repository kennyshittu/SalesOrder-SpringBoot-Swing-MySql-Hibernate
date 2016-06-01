package com.salesorderapp.frontend.panels.list;

import java.util.ArrayList;
import java.util.List;

import com.salesorderapp.backend.entities.Customer;
import com.salesorderapp.frontend.services.Services;

public class CustomerDataModel extends ListDataModel
{
	private static final long serialVersionUID = 7526529951747613655L;

	public CustomerDataModel()
	{
		super(new String[] { "Code", "Name", "Phone", "Current Credit" }, 0);
	}

	@Override
	public int getObjectType()
	{
		return Services.TYPE_CUSTOMER;
	}

	@Override
	public List<String[]> convertRecordsListToTableModel(List<Object> list)
	{
		//TODO by the candidate
		/*
		 * This method use list returned by Services.listCurrentRecords and should convert it to array of rows
		 * each row is another array of columns of the row
		 */
//		String[][] sampleData = new String [][]{{"01","Customer 1","+201011121314","23.4"},{"02","Customer 2","+201112131415","1.4"}};
		List<String []> sampleData = new ArrayList<>();

		for (Object o : list) {
			Customer customer = (Customer)o;
			String[] rowData = new String []{
					String.valueOf(customer.getCode()),
					customer.getName(),
					customer.getPrimaryPhoneLine(),
					String.valueOf(customer.getCurrentCredit())
			};
			sampleData.add(rowData);
		}


		return sampleData;
	}
}
