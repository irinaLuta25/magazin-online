package service;

import model.Customer;
import model.Order;
import model.enums.OrderStatus;
import repository.OrderRepository;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class OrderService extends AbstractService<Order> {
    public OrderService(OrderRepository repository) {
        super(repository);
    }
    // idee: interfata Payable - pay for the order at checkout, move calculateOrder here as well
    // - aici poate fi extins prin selectare metoda plata => atribut nou in order si introducere card pt plata daca e necesar
    // - o data cu plata se schimba si stadiul in payed. saaau sa nu aiba de ales si ramburs. ca daca poate plati si ramburs
    // tre sa te gandesti cum simulezi asta sau daca pui doar un mesaj tip vei plati suma de xxxx la livrare.

    public List<Order> getOrdersForCustomer(Customer customer) {
        List<Order> result = new ArrayList<>();
        for (Order o : getAll()) {
            if (o.getCustomer().equals(customer)) {
                result.add(o);
            }
        }
        result.sort((o1, o2) -> Integer.compare(o1.getOrderNumber(), o2.getOrderNumber()));
        return result;
    }

    public void updateOrderStatus(int orderNumber, OrderStatus newStatus) {
        for(Order order : items) {
            if(order.getOrderNumber()==orderNumber) {
                order.setOrderStatus(newStatus);
                System.out.println("Order status has been updated to " + newStatus);
                return;
            }
        }
        System.out.println("Order number " + orderNumber + " has not been found.");
    }

    public double calculateTotalOfAllOrders() {
        double sum = 0;
        for (Order order : this.items) {
            sum += order.calculateTotal();
        }
        return sum;
    }

    @Override
    public void displayAll() {
        if (items.isEmpty()) {
            System.out.println("No orders to display.\n");
            return;
        }

        System.out.println("\n##################### All Orders #####################");
        for (Order order : items) {
            order.displayOrder();
            System.out.println("------------------");
        }
    }

    @Override
    public void update(Order order) {
        throw new UnsupportedOperationException("Orders cannot be updated completely.");
    }

    @Override
    public void remove(Order item) {
        System.out.println("Canceling order " + item.getOrderNumber() + "...");
        item.getCart().forEach(((product, quantity) -> {
            product.setStock(product.getStock());
            System.out.println("initial stock for product " + product.getName() + ": " + product.getStock());
        }));
        super.remove(item);

        item.getCart().forEach(((product, quantity) -> {
            product.setStock(product.getStock()+quantity);
            System.out.println("updated stock for product " + product.getName() + ": " + product.getStock());
        }));

    }
}
