package com.webservice.rest.inventory.service;

import java.util.List;

import com.webservice.rest.inventory.database.ProductDAO;
import com.webservice.rest.inventory.exception.StoreDoesNotExists;
import com.webservice.rest.inventory.model.Product;
/**
 * 
 * This is the product service which will handle functions like add a product, give list of the products
 * update product.
 * 
 */
public class ProductService {
	private ProductDAO dao=new ProductDAO();
	
	public ProductService(){	}
	
	public List<Product> getAllProducts() {
		return dao.getListOfProducts();
	}
	
	public int getProductsForProductAndStore(int store, String name ){
		return dao.getCountForProductAndStore(store, name);
	}
	
	public List<Product> getProductBySKU(int sku){
		return dao.getProductBySKUFromDB(sku);
	}
	
	public void addProduct(Product product){
		try{
		dao.addProduct(product);
		}catch(StoreDoesNotExists e){
			throw new StoreDoesNotExists(e.getMessage());
		}
	}
	
	public void updateProduct(String productName, int storeNumber, int setCount) {
		try {
			dao.updateProduct(productName, storeNumber, setCount);
		} catch (StoreDoesNotExists e) {
			throw new StoreDoesNotExists(e.getMessage());
		}
	}
}
