package northwind.data;
import northwind.model.Order;

import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.NoResultException;


public class OrderRepository extends AbstractJpaRepository<Order> {
	private static final long serialVersionUID = 1L;
	@Inject
	private Logger log;
	
	public OrderRepository() {
		super(Order.class);
	}
	
	public Order findOne(int orderId) {
		Order singleResult;
		try {
			singleResult = getEntityManager().createQuery(
					"SELECT ord FROM Order ord JOIN FETCH ord.orderDetails WHERE ord.orderID = :idValue", Order.class)
					.setParameter("idValue", orderId)	
					.getSingleResult();
		}
		catch(NoResultException nre) {
			singleResult = null;
			log.info(nre.getMessage());
		}
		return singleResult;
		
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