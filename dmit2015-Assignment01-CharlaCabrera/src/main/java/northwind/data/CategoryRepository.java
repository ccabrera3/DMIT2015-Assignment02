package northwind.data;
import java.util.List;
import northwind.model.Category;
import northwind.report.CategorySales;

public class CategoryRepository extends AbstractJpaRepository<Category> {
	private static final long serialVersionUID = 1L;
	
	public CategoryRepository() {
		super(Category.class);
	}
	
	public List<CategorySales> findCategorySales(){
		return getEntityManager().createQuery(
		"SELECT new northwind.report.CategorySales(c.categoryName,SUM(od.quantity * od.unitPrice * (1 - od.discount)))"
		+ " FROM OrderDetail od,IN(od.product) p,IN(p.category) c WHERE YEAR(od.order.shippedDate) = :yearValue"
	    + " GROUP BY c.categoryName ORDER BY c.categoryName "		
		,CategorySales.class)
		.setParameter("yearValue",1997)
		.getResultList();
	}
	

}
