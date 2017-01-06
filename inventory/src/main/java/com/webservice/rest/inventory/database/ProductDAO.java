package com.webservice.rest.inventory.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.webservice.rest.inventory.exception.StoreDoesNotExists;
import com.webservice.rest.inventory.model.Product;

public class ProductDAO {
	private Connection connection;
	private Statement statement;

	public ProductDAO() {
	}

	public List<Product> getListOfProducts() {
		List<Product> list=new ArrayList<Product>();
		try {
			String query = "SELECT * FROM products";
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				int sku = rs.getInt("SKU");
				String name = rs.getString("name");
				int store = rs.getInt("store");
				int count = rs.getInt("count");
				list.add(new Product(sku,name,store,count));
			}

			closeConnection(connection, statement);
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (NullPointerException e) {
			throw new NullPointerException("Nullpointer exception "+e.getMessage());
		}
		return list;
	}
	


	public int getCountForProductAndStore(int store, String name ){
		String query="select count from products where store="+store+" and name like '"+name+"%'";
		PreparedStatement ps = null;
        ResultSet rs = null;
        int count=0;
        try {
        	connection = JDBCMySQLConnection.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	count += rs.getInt("count");
            }
            closeConnection(connection, statement);
        } catch (SQLException e) {
			System.err.println(e.getMessage());
		}catch (NullPointerException e) {
			throw new NullPointerException("Nullpointer exception "+e.getMessage());
		}
        return count;
	}
	
	
	public List<Product> getProductBySKUFromDB(int sku){
		List<Product> list=new ArrayList<Product>();
		Product temp=null;
		try {
			String query = "SELECT * FROM products where SKU="+sku;
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				
				String name = rs.getString("name");
				int store = rs.getInt("store");
				int count = rs.getInt("count");
				temp=new Product(sku,name,store,count);
				list.add(new Product(sku,name,store,count));
			}

			closeConnection(connection, statement);
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}catch (NullPointerException e) {
			throw new NullPointerException("Nullpointer exception "+e.getMessage());
		}
		return list;
	}
	
	public void addProduct(Product product) {
		try {
			int sku=product.getSKU();
			String name=product.getName();
			int store=product.getStore();
			int count=product.getCount();
			if(isProductPresent(store,name,true)){
				String query = "INSERT INTO products VALUES ("+sku+",'"+name+"',"+store+","+count+")";
				connection = JDBCMySQLConnection.getConnection();
				statement = connection.createStatement();
				statement.executeUpdate(query);
				closeConnection(connection, statement);
			}else{
				System.out.println("Store does not exist at "+store);
			}
				
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (NullPointerException e) {
			throw new NullPointerException("Nullpointer exception "+e.getMessage());
		}
	}
	
	
	private boolean isProductPresent(int store, String name, boolean isAdd ) {
		String query = "";
		if (isAdd)
			query = "select count from products where store=" + store;
		else
			query = "select count from products where store=" + store+" and name='"+name+"'";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = JDBCMySQLConnection.getConnection();
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs.next()) {
				closeConnection(connection, statement);
				return true;
			}
			closeConnection(connection, statement);

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (NullPointerException e) {
			throw new NullPointerException("Nullpointer exception " + e.getMessage());
		} catch (StoreDoesNotExists e) {
			System.out.println("Store does not exist at "+store);
			return false;
		}
		return false;
	}

	public void updateProduct(String productName, int storeNumber, int setCount) {
		try {
			if(isProductPresent(storeNumber, productName, false)){
			String query="UPDATE products SET count="+setCount+" WHERE name like'"+productName+"%' and Store="+storeNumber;
			connection = JDBCMySQLConnection.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(query);
			closeConnection(connection, statement);
			}else{
				System.out.println("Store does not exist at "+storeNumber);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}catch (NullPointerException e) {
			throw new NullPointerException("Nullpointer exception "+e.getMessage());
		}
	}
	
	private void closeConnection(Connection connection, Statement statement) throws SQLException {
		if (statement != null)
			statement.close();

		if (connection != null)
			connection.close();
	}
}
