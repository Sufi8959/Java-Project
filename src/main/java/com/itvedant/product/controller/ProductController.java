package com.itvedant.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.itvedant.product.dao.AddProductDAO;
import com.itvedant.product.dao.UpdateProductDAO;
import com.itvedant.product.entity.Product;
import com.itvedant.product.service.ProductService;

@Controller
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("")
	public ResponseEntity<List<Product>> create(@RequestBody List<AddProductDAO> addProductDao) {
		return ResponseEntity.ok(this.productService.createProduct(addProductDao));
	}

	@GetMapping("")
	public ResponseEntity<?> read() {
		return ResponseEntity.ok(this.productService.fetchAllProducts());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> search(@PathVariable Integer id) {
		return ResponseEntity.ok(this.productService.searchProduct(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UpdateProductDAO updateProductDao) {
		return ResponseEntity.ok(this.productService.updateProduct(id, updateProductDao));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		return ResponseEntity.ok(this.productService.deleteProduct(id));
	}
}
