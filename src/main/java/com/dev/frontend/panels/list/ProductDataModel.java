package com.dev.frontend.panels.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.dev.backend.entities.Product;
import com.dev.frontend.services.Services;


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
					String.format("%s",product.getCode()),
					product.getDescription(),
					String.format("%s", product.getPrice()),
					String.format("%s",product.getQuantity())
			};
			sampleData.add(rowData);
		}


		return sampleData;
	}
}
