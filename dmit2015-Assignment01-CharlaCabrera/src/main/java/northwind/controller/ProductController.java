package northwind.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

import northwind.data.ProductRepository;
import northwind.model.Product;
@Model
public class ProductController {
	//CODE HERE FOR PRODUCT DETAIL
	private int currentSelectedProductId;		// getter/setter
	private Product currentSelectedProduct;	// getter
	
	public void findProduct() {
		if( !FacesContext.getCurrentInstance().isPostback() ) {
			if( currentSelectedProductId > 0 ) {
				currentSelectedProduct = productRepository.find(currentSelectedProductId);
				if( currentSelectedProduct == null ) {
					Messages.addGlobalInfo("There is no product with productID {0}", 
							currentSelectedProductId);
				} else {
					Messages.addGlobalInfo("Successfully retrieved product info.");
				}
			} else {
				Messages.addGlobalError("Bad request. A valid ProductID is required.");
			}
		}		
	}
	// END PRODUCT DETAIL CODE MODEL
	@Inject
	private ProductRepository productRepository;
	
	private List<Product> products;
	
	//CALLING OUT THE REPORT
	
	public List <Product> retrieveTopTen() {
		return productRepository.findTopTenProducts();
	}
	//END REPORT
	
	@PostConstruct
	void init() {
		products = productRepository.findAll();
	}
	
	public List<Product> getProducts(){
		return products;
	}
	
	private List<Product> productsbyCategory;	// getter
	private int currentSelectedCategoryId;	// getter/setter
//	private Genre currentSelectedGenre;	// getter
	
	public void findProductsByCategory() {
		if( !FacesContext.getCurrentInstance().isPostback() ) {
			
			if( currentSelectedCategoryId > 0) {
				productsbyCategory = productRepository.findAllByCategoryId(currentSelectedCategoryId);
				if( productsbyCategory.size() == 0 ) {
					Messages.addGlobalInfo("There are no products for Category ID {0}", 
							currentSelectedCategoryId);
				}
			} else {
				Messages.addGlobalError("Bad request. A valid Category ID is required.");
			}
		}
	}

	public int getCurrentSelectedCategoryId() {
		return currentSelectedCategoryId;
	}

	public void setCurrentSelectedCategoryId(int currentSelectedCategoryId) {
		this.currentSelectedCategoryId = currentSelectedCategoryId;
	}

	public List<Product> getProductsbyCategory() {
		return productsbyCategory;
	}
	
	//FOR THE PRODUCT DETAILS
	public int getCurrentSelectedProductId() {
		return currentSelectedProductId;
	}

	public void setCurrentSelectedProductId(int currentSelectedProductId) {
		this.currentSelectedProductId = currentSelectedProductId;
	}

	public Product getCurrentSelectedProduct() {
		return currentSelectedProduct;
	}
	

	

}
