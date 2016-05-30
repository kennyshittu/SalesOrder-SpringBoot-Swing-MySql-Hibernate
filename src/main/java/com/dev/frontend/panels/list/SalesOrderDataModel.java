package com.dev.frontend.panels.list;

import java.util.ArrayList;
import java.util.List;

import com.dev.backend.entities.SalesOrder;
import com.dev.frontend.services.Services;


public class SalesOrderDataModel extends ListDataModel
{
	private static final long serialVersionUID = 7526529951747614655L;

	public SalesOrderDataModel() 
	{
		super(new String[]{"Order Number","Customer","Total Price"}, 0);
	}

	@Override
	public int getObjectType() {
		return Services.TYPE_SALESORDER;
	}

	@Override
	public List<String[]> convertRecordsListToTableModel(List<Object> list)
	{
		//TODO by the candidate
		/*
		 * This method use list returned by Services.listCurrentRecords and should convert it to array of rows
		 * each row is another array of columns of the row
		 */
		String[][] sampleData = new String [][]{{"22423","(01)Customer 1","122.5"},{"22424","(02)Customer 2","3242.5"}};
		List<String []> sampleData = new ArrayList<>();

		for (Object o : list) {
			SalesOrder salesOrder = (SalesOrder)o;
			String[] rowData = new String []{
					String.format("%s",salesOrder.getCode()),
					String.format("(%s)%s", salesOrder.getCustomer_code()),
					String.format("%s",product.getQuantity())
			};
			sampleData.add(rowData);
		}


		return sampleData;
	}
}
