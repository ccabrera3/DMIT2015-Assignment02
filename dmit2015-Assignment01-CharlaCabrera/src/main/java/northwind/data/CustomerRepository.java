package northwind.data;

import northwind.model.Customer;


public class CustomerRepository extends AbstractJpaRepository<Customer> {
	private static final long serialVersionUID = 1L;

	public CustomerRepository() {
		super(Customer.class);
	}

}
