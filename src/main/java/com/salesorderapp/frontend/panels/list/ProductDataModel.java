package com.salesorderapp.frontend.panels.list;

import java.util.ArrayList;
import java.util.List;

import com.salesorderapp.backend.models.Product;
import com.salesorderapp.frontend.services.Services;


public class ProductDataModel extends ListDataModel
{
	private static final long serialVersionUID = 7526529951747614655L;

	public ProductDataModel() 
	{
		super(new String[]{"Code","Description","Price","Quantity"}, 0);
	}

	@Override
	public int getObjectType() {
		return Services.TYPE_PRODUCT;
	}

	@Override
	public List<String[]> convertRecordsListToTableModel(List<Object> list)
	{
		//TODO by the candidate
		/*
		 * This method use list returned by Services.listCurrentRecords and should convert it to array of rows
		 * each row is another array of columns of the row
		 */
//		String[][] sampleData = new String [][]{
//				{"01","Product 1","12.5","25"},
//				{"02","Product 2","10","8"}
//		};
		List<String []> sampleData = new ArrayList<>();

		for (Object o : list) {
			Product product = (Product)o;
			String[] rowData = new String []{
					String.valueOf(product.getCode()),
					product.getDescription(),
					String.valueOf(product.getPrice()),
					String.valueOf(product.getQuantity())
			};
			sampleData.add(rowData);
		}


		return sampleData;
	}
}
