package service;

import model.Customer;
import repository.CustomerRepository;
import repository.Repository;


public class CustomerService extends AbstractService<Customer> {


    public CustomerService(CustomerRepository repository) {
        super(repository);
    }

    public Customer getByEmail(String email) {
        for (Customer c : getAll()) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void displayAll() {
        System.out.println("\n##################### All Customers #####################");
        super.displayAll();
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
