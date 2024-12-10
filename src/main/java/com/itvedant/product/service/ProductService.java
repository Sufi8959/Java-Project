package com.itvedant.product.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itvedant.product.dao.AddProductDAO;
import com.itvedant.product.dao.UpdateProductDAO;
import com.itvedant.product.entity.Product;
import com.itvedant.product.exception.ProductNotFoundException;
import com.itvedant.product.exception.UserNotFoundException;
import com.itvedant.product.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	public List<Product> createProduct(List<AddProductDAO> addProductDao) {

		List<Product> products = new ArrayList<>();
		for (AddProductDAO addProductsDao : addProductDao) {
			Product product = new Product();
			product.setName(addProductsDao.getName());
			product.setPrice(addProductsDao.getPrice());
			product.setQuantity(addProductsDao.getQuantity());

			products.add(product);
		}

		productRepository.saveAll(products);
		return products;
	}

	public List<Product> fetchAllProducts() {
		List<Product> products = new ArrayList<>();

		products = productRepository.findAll();

		return products;
	}

	public Product searchProduct(Integer id) {
		Product product = new Product();

		product = this.productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
		return product;
	}

	public Product updateProduct(Integer id, UpdateProductDAO updateProductDao) {

		Product product = new Product();

		product = this.searchProduct(id);

		if (updateProductDao.getName() != null) {
			product.setName(updateProductDao.getName());
		}
		if (updateProductDao.getPrice() != null) {
			product.setPrice(updateProductDao.getPrice());
		}

		if (updateProductDao.getQuantity() != null) {
			product.setQuantity(updateProductDao.getQuantity());
		}

		this.productRepository.save(product);

		return product;

	}

	public String deleteProduct(Integer id) {
		Product product = new Product();

		product = this.searchProduct(id);

		this.productRepository.delete(product);

		return "Deleted Sucessfully";
	}
}
