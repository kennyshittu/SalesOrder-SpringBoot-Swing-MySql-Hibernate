package com.dev.frontend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dev.backend.entities.Customer;
import com.dev.backend.entities.CustomerList;
import com.dev.backend.entities.Product;
import com.dev.backend.entities.ProductList;
import com.dev.backend.entities.SalesOrderList;
import com.dev.backend.models.SalesOrderEntity;
import com.dev.frontend.panels.ComboBoxItem;
import org.springframework.web.client.RestTemplate;

public class Services 
{
	public static final int TYPE_PRODUCT = 1;
	public static final int TYPE_CUSTOMER = 2;
	public static final int TYPE_SALESORDER = 3;
	public static final String BASE_URI = "http://localhost:8080";
	
	public static Object save(Object object,int objectType)
	{
		//TODO by the candidate 
		/*
		 * This method is called eventually after you click save on any edit screen
		 * object parameter is the return object from calling method guiToObject on edit screen
		 * and the type is identifier of the object type and may be TYPE_PRODUCT ,
		 * TYPE_CUSTOMER or TYPE_SALESORDER 
		 */
		final RestTemplate restTemplate = new RestTemplate();
		System.out.println("Current object : " + object);
		switch (objectType){
			case  TYPE_PRODUCT:
				return restTemplate.postForObject(
						String.format("%s/product/create", BASE_URI),
						object,
						Product.class
				);
			case TYPE_CUSTOMER:
				System.out.println("Customer : " + String.format("%s/customer/create", BASE_URI));
				return restTemplate.postForObject(
						String.format("%s/customer/create", BASE_URI),
						object,
						Customer.class
				);
			case TYPE_SALESORDER:
				System.out.println("Salesorder : " + String.format("%s/salesorder/create", BASE_URI));
				return restTemplate.postForObject(
						String.format("%s/salesorder/create", BASE_URI),
						object,
						SalesOrderEntity.class
				);
			default:
				return null;
		}
	}
	public static Object readRecordByCode(String code,int objectType)
	{
		//TODO by the candidate
		/*
		 * This method is called when you select record in list view of any entity
		 * and also called after you save a record to re-bind the record again
		 * the code parameter is the first column of the row you have selected
		 * and the type is identifier of the object type and may be TYPE_PRODUCT ,
		 * TYPE_CUSTOMER or TYPE_SALESORDER */
		final RestTemplate restTemplate = new RestTemplate();

		switch (objectType){
			case  TYPE_PRODUCT:
				return restTemplate.getForObject(
						String.format("%s/product/?code=%s", BASE_URI, code),
						Product.class
				);
			case TYPE_CUSTOMER:
				return restTemplate.getForObject(
						String.format("%s/customer/?code=%s", BASE_URI, code),
						Customer.class
				);
			case TYPE_SALESORDER:
				return restTemplate.getForObject(
						String.format("%s/salesorder/?salescode=%s", BASE_URI, code),
						SalesOrderEntity.class
				);
			default:
				return null;
		}
	}
	public static boolean deleteRecordByCode(String code,int objectType)
	{
		//TODO by the candidate
		/*
		 * This method is called when you click delete button on an edit view
		 * the code parameter is the code of (Customer - PRoduct ) or order number of Sales Order
		 * and the type is identifier of the object type and may be TYPE_PRODUCT ,
		 * TYPE_CUSTOMER or TYPE_SALESORDER
		 */
		final RestTemplate restTemplate = new RestTemplate();

		switch (objectType){
			case  TYPE_PRODUCT:
				return restTemplate.getForObject(
						String.format("%s/product/delete?code=%s", BASE_URI, code),
						Boolean.class
				);
			case TYPE_CUSTOMER:
				return restTemplate.getForObject(
						String.format("%s/customer/delete?code=%s", BASE_URI, code),
						Boolean.class
				);
			case TYPE_SALESORDER:
				return restTemplate.getForObject(
						String.format("%s/salesorder/delete?ordernumber=%s", BASE_URI, code),
						Boolean.class
				);
			default:
				return false;
		}
	}
	
	public static List<Object> listCurrentRecords(int objectType)
	{
		//TODO by the candidate
		/*
		 * This method is called when you open any list screen and should return all records of the specified type
		 */
		final RestTemplate restTemplate = new RestTemplate();

		switch (objectType){
			case  TYPE_PRODUCT:
				return restTemplate
						.getForObject(
								String.format("%s/product/all", BASE_URI),
								ProductList.class)
						.getProducts()
						.stream()
						.collect(Collectors.toList());
			case TYPE_CUSTOMER:
				return restTemplate
						.getForObject(
								String.format("%s/customer/all", BASE_URI),
								CustomerList.class)
						.getCustomers()
						.stream()
						.collect(Collectors.toList());
			case TYPE_SALESORDER:
				return restTemplate
						.getForObject(
								String.format("%s/salesorder/all", BASE_URI),
								SalesOrderList.class)
						.getSalesOrders()
						.stream()
						.collect(Collectors.toList());
			default:
				return new ArrayList<Object>();
		}
	}
	public static List<ComboBoxItem> listCurrentRecordRefernces(int objectType) 
	{	
		//TODO by the candidate
		/*
		 * This method is called when a Combo Box need to be initialized and should
		 * return list of ComboBoxItem which contains code and description/name for all records of specified type
		 */
		final RestTemplate restTemplate = new RestTemplate();

		switch (objectType){
			case  TYPE_PRODUCT:
				return restTemplate
						.getForObject(
								String.format("%s/product/all", BASE_URI),
								ProductList.class)
						.getProducts()
						.stream()
						.map(product -> new ComboBoxItem(
								String.valueOf(product.getCode()),
								product.getDescription()))
						.collect(Collectors.toList());
			case TYPE_CUSTOMER:
				return restTemplate
						.getForObject(
								String.format("%s/customer/all", BASE_URI),
								CustomerList.class)
						.getCustomers()
						.stream()
						.map(customer -> new ComboBoxItem(
								String.valueOf(customer.getCode()),
								customer.getName()))
						.collect(Collectors.toList());
			default:
				return new ArrayList<ComboBoxItem>();
		}

	}
	public static double getProductPrice(String productCode) {
		//TODO by the candidate
		/*
		 * This method is used to get unit price of product with the code passed as a parameter
		 */
		final RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(
				String.format("%s/product/?code=%s", BASE_URI, productCode),
				Product.class
		);
		return product.getPrice();
	}
}
