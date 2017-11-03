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
	
/*		public List<MonthlySalesReport> findMonthlySales(){
			
			return getEntityManager().createQuery(
					"SELECT new northwind.report.MonthlySales(od.month, SUM(od.quantity * od.unitPrice * (1 - od.discount)))"
					+ " FROM OrderDetail od WHERE YEAR(od.order.shippedDate) BETWEEN :startDate AND :endDate "
				    + " GROUP BY c.categoryName ORDER BY c.categoryName "		
					,MonthlySalesReport.class)
					.setParameter("startDate",1996)
					.setParameter("endDate",1998)
					.getResultList();
			
		}*/

		//"SELECT new northwind.report.CategorySales(c.categoryName,SUM(od.quantity * od.unitPrice * (1 - od.discount)))"
		//+ " FROM OrderDetail od,IN(od.product) p,IN(p.category) c WHERE YEAR(od.order.shippedDate) = :yearValue"
	   // + " GROUP BY c.categoryName ORDER BY c.categoryName "	
		
		
		
		public BigDecimal findMonthlySales(int year, int month) {
			
			BigDecimal allMonthYearSales = BigDecimal.ZERO;
			try{
				allMonthYearSales = getEntityManager()
						.createQuery("SELECT SUM(od.unitPrice * od.quantity ) FROM OrderDetail od, IN (od.order) o "
									+ "WHERE YEAR(o.shippedDate) = :yearValue AND MONTH(o.shippedDate) = :monthValue " , BigDecimal.class)
						.setParameter("yearValue", year)
						.setParameter("monthValue", month)
						.getSingleResult();
			}catch (Exception e) {
				
			}
			if (allMonthYearSales == null) {
				allMonthYearSales = BigDecimal.ZERO;
			}
			return allMonthYearSales;
		}	
	


}