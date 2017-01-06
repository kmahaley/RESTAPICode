	package com.webservice.rest.inventory;

import com.webservice.rest.inventory.exception.StoreDoesNotExists;
import com.webservice.rest.inventory.model.Product;
import com.webservice.rest.inventory.service.ProductService;

public class ProductServiceDemo {

	/*
	 * ProductService 
	 * 1) can add a product 
	 * 2) get a count of the products based on name and store 
	 * 3) can get list of all the products in database 
	 * 4) gets a product based on SKU id
	 */
	public static void main(String[] args) {
		
		try {
			ProductService ps = new ProductService();
			/*
			 * Register/define a new product with a SKU and product name, to add
			 * product in inventory we need to mention a store number to build
			 * inventory
			 */
			ps.addProduct(new Product(103, "iPhone 7 32GB plus", 200, 50));
			/* Get the inventory count for a product at a particular store */
			System.out.println(ps.getProductsForProductAndStore(900, "iPhone 7 32GB"));
			/* Update the inventory count for a product as a particular store */
			ps.updateProduct("iPhone 7 32GB", 900, 9);

			/*Additional functions to list out products at the store */
			System.out.println(ps.getAllProducts());
			System.out.println(ps.getProductBySKU(123));
		} catch (StoreDoesNotExists e) {
			System.out.println(e.getMessage());
		}
	}

}
