package northwind.service;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import northwind.data.ProductRepository;
import northwind.model.Product;
@Stateless
public class ProductService {

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private ProductRepository productRepository;


	
	public Product findOne (int productId) {
		Product currentProduct = new Product();
		try {
			currentProduct = productRepository.find(productId);
		} catch(NoResultException e) {
			currentProduct = null;
		}
		return currentProduct;
		
	}

}
