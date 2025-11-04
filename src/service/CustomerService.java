package service;

import model.Customer;


public class CustomerService extends AbstractService<Customer> {

    public Customer getByEmail(String email) {
        for (Customer c : getAll()) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void add(Customer customer) {
        if (getByEmail(customer.getEmail()) != null) {
            System.out.println("Client already exists!");
        } else {
            super.add(customer);
        }
    }
}
