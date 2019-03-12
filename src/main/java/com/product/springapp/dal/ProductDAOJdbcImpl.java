package com.product.springapp.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.product.springapp.domain.Product;

@Component
public class ProductDAOJdbcImpl implements ProductDAO {

	@Autowired
	DataSource ds;

	@Autowired
	JdbcTemplate template;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.product.springapp.dal.ProductDAO#save(com.product.springapp.
	 * domain.Product)
	 */
	@Override
	public void save(Product p) {
		try (Connection con = ds.getConnection()) {
			PreparedStatement pStmt = con.prepareStatement("insert into product(name, price, qoh) values (?, ?, ?)");
			pStmt.setString(1, p.getName());
			pStmt.setFloat(2, p.getPrice());
			pStmt.setInt(3, p.getQoh());
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.product.springapp.dal.ProductDAO#findById(long)
	 */
	@Override
	public Product findById(long id) {
		Product p = null;
		try (Connection con = ds.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from product where id = " + id);
			if (rs.next()) {
				p = new Product();
				p.setId(rs.getLong(1));
				p.setName(rs.getString(2));
				p.setPrice(rs.getFloat(3));
				p.setQoh(rs.getInt(4));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return p;
	}

	@Override
	public List<Product> findAll() {

		return template.query("select * from product", new ProductMapper());
	}
}
