package northwind.data;
import northwind.model.Order;

import java.math.BigDecimal;


public class OrderRepository extends AbstractJpaRepository<Order> {
	private static final long serialVersionUID = 1L;
	
	public OrderRepository() {
		super(Order.class);
	}
	
	public Order findOne(int orderId) {
		return getEntityManager().createQuery(
				"SELECT ord FROM Order ord JOIN FETCH ord.orderDetails WHERE ord.orderID = :idValue", Order.class)
				.setParameter("idValue", orderId)	
				.getSingleResult();
	}

	
	public BigDecimal findMonthlySales(int year, int month) {
		
		BigDecimal allMonthYearSales = BigDecimal.ZERO;
		
			allMonthYearSales = getEntityManager()
					.createQuery("SELECT SUM(od.unitPrice * od.quantity) FROM OrderDetail od, IN (od.order) o "
								+ "WHERE YEAR(o.shippedDate) = :yearValue AND MONTH(o.shippedDate) = :monthValue " , BigDecimal.class)
					.setParameter("yearValue", year)
					.setParameter("monthValue", month)
					.getSingleResult();
		
		if (allMonthYearSales == null) {
			allMonthYearSales = BigDecimal.ZERO;
		}
		return allMonthYearSales;
	}
	


}