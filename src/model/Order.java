package model;

import model.enums.OrderStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private int orderNumber;
    private Customer customer;
    private Map<AProduct, Integer> cart;
    private OrderStatus orderStatus;

    public Order(int orderNumber, Customer customer, OrderStatus orderStatus) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.cart = new HashMap<>();
        this.orderStatus = orderStatus;
    }

    public double calculateTotal() {
        double total = 0;
        for(Map.Entry<AProduct, Integer> entry : this.cart.entrySet()) {
            total+= entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void addToCart(AProduct product, int quantity) {
        if(product!=null && quantity>0) {
            this.cart.put(product, this.cart.getOrDefault(product,0) + quantity);
        }
    }

    public void displayOrder() {
        System.out.println("Your order summary:\n");
        for(Map.Entry<AProduct,Integer> entry : this.cart.entrySet()) {
            int quantity = entry.getValue();
            AProduct product = entry.getKey();

            System.out.println(product + " x" + quantity);
        }
    }

    public void deleteFromCart(AProduct product) {
        if(this.cart.containsKey(product)) {
            this.cart.remove(product);
        }
    }

    public void updateQuantity(AProduct product, Integer newQuantity) {
        if(this.cart.containsKey(product)) {
            this.cart.put(product, newQuantity);
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    public int getOrderNumber() {
        return orderNumber;
    }


    public Customer getCustomer() {
        return customer;
    }

    public Map<AProduct, Integer> getCart() {
        return cart;
    }


    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Order{");
        sb.append("orderNumber=").append(orderNumber);
        sb.append(", customer=").append(customer);
        sb.append(", cart=").append(cart);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append('}');
        return sb.toString();
    }
}
