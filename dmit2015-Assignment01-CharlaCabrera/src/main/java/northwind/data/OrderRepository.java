package northwind.data;
import northwind.model.Order;

public class OrderRepository extends AbstractJpaRepository<Order> {
	private static final long serialVersionUID = 1L;
	
	public OrderRepository() {
		super(Order.class);
	}
	

}
