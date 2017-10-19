package northwind.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.convert.BigDecimalConverter;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

import northwind.data.OrderRepository;
import northwind.model.Order;
import northwind.model.OrderDetail;
@Model
public class OrderController {
	
	private int currentSelectedOrderId;		// getter/setter
	private Order currentSelectedOrder;		// getter
	
	public void findOrder() {
		if( !FacesContext.getCurrentInstance().isPostback() ) {
			if( currentSelectedOrderId > 0 ) {
				currentSelectedOrder = orderRepository.findOne(currentSelectedOrderId);
				if( currentSelectedOrder == null ) {
					Messages.addGlobalInfo("There is no invoice with invoiceID {0}", 
							currentSelectedOrderId);					
				}
			} else {
				Messages.addGlobalError("Bad request. Invalid invoiceID {0}", currentSelectedOrderId);
			}
		}
	}
	
	@Inject
	private OrderRepository orderRepository;
	
	private List<Order> orders;
	
	@PostConstruct
	void init() {
		orders = orderRepository.findAll();
	}
	
	public List<Order> getOrders(){
		return orders;
	}
	
	public int getCurrentSelectedOrderId() {
		return currentSelectedOrderId;
	}

	public void setCurrentSelectedOrderId(int currentSelectedOrderId) {
		this.currentSelectedOrderId = currentSelectedOrderId;
	}

	public Order getCurrentSelectedOrder() {
		return currentSelectedOrder;
	}
	
	public BigDecimal findSubTotal() {
		double subtotal = 0;
		for(OrderDetail od :currentSelectedOrder.getOrderDetails()) {
			subtotal += od.getUnitPrice().doubleValue() * od.getQuantity();
		}
		return BigDecimal.valueOf(subtotal);
	}
	
	public double findTotal() {
		double subtotal = 0;
		for(OrderDetail od :currentSelectedOrder.getOrderDetails()) {
			subtotal += od.getUnitPrice().doubleValue() * od.getQuantity();
		}
		double total = subtotal + currentSelectedOrder.getFreight().doubleValue();
		return total;
	}

}
