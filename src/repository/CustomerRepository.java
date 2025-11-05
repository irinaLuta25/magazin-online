package repository;

import model.Customer;

public class CustomerRepository extends AbstractTextRepository<Customer> {
    public CustomerRepository(String filename) {
        super(filename);
    }

    @Override
    protected String convertToLine(Customer customer) {
        return customer.getName()+";"+ customer.getAddress()+";"+ customer.getEmail();
    }

    @Override
    protected Customer parseLine(String line) {
        String[] tokens=line.split(";");
        return new Customer(tokens[0], tokens[1], tokens[2]);
    }
}
