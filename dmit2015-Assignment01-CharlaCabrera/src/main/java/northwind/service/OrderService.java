package northwind.service;

import northwind.exception.InvalidQuantity;
import northwind.exception.StockException;
import northwind.exception.NoOrderDetail;


import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.EJBContext;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.ejb3.annotation.SecurityDomain;

import northwind.model.Order;
import northwind.model.OrderDetail;
import northwind.model.OrderDetailPK;


@SecurityDomain("northwindDomain")
@DeclareRoles({"Administrator","Employee","Customer"})
@Stateless
public class OrderService {
	
	@Resource
	private EJBContext context;
	

	@Inject
	private EntityManager entityManager;

	@PermitAll
	public int createNewOrder(Order newOrder, List<OrderDetail> products) 
			throws InvalidQuantity, StockException, NoOrderDetail {
		
		int orderId = 0;
		
		if(products == null || products.size() == 0) {
			context.setRollbackOnly();
			throw new NoOrderDetail("There are no products in the Order");
		}
		
		entityManager.persist(newOrder);
		orderId = newOrder.getOrderID();
		
		for (OrderDetail singleItem : products) {
			
			if (singleItem.getQuantity() < 1) {
				context.setRollbackOnly();
				throw new InvalidQuantity("Product quantity must be greater than 1");
			}
			
			if (singleItem.getQuantity() > singleItem.getProduct().getUnitsInStock() ) {
				context.setRollbackOnly();
				throw new NoOrderDetail("Insufficient stock for quantity requested");
			}
			
			OrderDetailPK primaryKey = new OrderDetailPK();
			
			primaryKey.setOrderID(orderId);
			primaryKey.setProductID(singleItem.getProduct().getProductID());
			
			singleItem.setId(primaryKey);
			
			entityManager.persist(singleItem);
		}
		
		return orderId;
	}


}
