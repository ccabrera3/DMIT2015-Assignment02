package northwind.data;
import java.util.List;


import northwind.model.Product;


public class ProductRepository extends AbstractJpaRepository<Product> {
	private static final long serialVersionUID = 1L;

	
	public ProductRepository() {
		super(Product.class);
	}
	
	public List<Product> findAllByCategoryId(int categoryId) {
		return getEntityManager().createQuery(
			"SELECT p FROM Product p WHERE p.category.categoryID = :idValue", Product.class)
			.setParameter("idValue", categoryId)
			.getResultList();
	}
	
	public List<Product> findTopTenProducts(){
		return getEntityManager().createQuery(
				"SELECT p from Product p ORDER BY p.unitPrice DESC",
				Product.class)
				.setMaxResults(10)
				.getResultList();
				
	}



}
