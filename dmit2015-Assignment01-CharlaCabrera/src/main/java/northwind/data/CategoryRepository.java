package northwind.data;
import northwind.model.Category;

public class CategoryRepository extends AbstractJpaRepository<Category> {
	private static final long serialVersionUID = 1L;
	
	public CategoryRepository() {
		super(Category.class);
	}
	

}
