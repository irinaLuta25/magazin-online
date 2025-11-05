package model;

import model.enums.OrderStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order implements Serializable {

    private static int orderCounter=0;
    private int orderNumber;
    private Customer customer;
    private Map<AProduct, Integer> cart;
    private OrderStatus orderStatus;

    public Order(Customer customer, OrderStatus orderStatus) {
        this.orderNumber = ++orderCounter;
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
            if(product.getStock()<quantity) {
                throw new IllegalArgumentException("Stoc insuficient pentru produsul: " + product.getName() + ". Stoc curent: " + product.getStock() + " bucati.");
            }

            this.cart.put(product, this.cart.getOrDefault(product,0) + quantity);
//            System.out.println("\nAdded to cart: product " + product + " x" + quantity);
            product.setStock(product.getStock()-quantity);
//            System.out.println("current stock: "+ product.getStock());
        }
    }

    public void displayOrder() {
        System.out.println("\nOrder #" + this.orderNumber + " summary for customer " + this.customer.getName() + ":\n");
        System.out.println("~ Status " + this.orderStatus + " ~\n");
        for(Map.Entry<AProduct,Integer> entry : this.cart.entrySet()) {
            int quantity = entry.getValue();
            AProduct product = entry.getKey();

            System.out.println(product + " x" + quantity);
        }

        System.out.println("\nTotal: " + calculateTotal());
    }

    public void deleteFromCart(AProduct product) {
        if(this.cart.containsKey(product)) {
            System.out.println("\nInitial stock: " + product.getStock());

            int quantity = this.cart.get(product);
            this.cart.remove(product);
            product.setStock(product.getStock() + quantity);

            System.out.println("Removed " + quantity + " x " + product.getName() + " from cart.");
            System.out.println("Updated stock: " + product.getStock());
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    // todo: update-ul trebuie sa verifice intai daca cantitatea noua e valida (in stoc), apoi sa decida daca mareste sau micsoreaza stocul in functie de caz
//    public void updateQuantity(AProduct product, Integer newQuantity) {
//        if(this.cart.containsKey(product)) {
//            System.out.println("\nInitial stock: " + product.getStock());
//
//            this.cart.put(product, newQuantity);
//            product.setStock(newQuantity);
//
//            System.out.println("Updated stock for product " + product.getName() + ": " + product.getStock());
//        } else {
//            System.out.println("Product not found in cart.");
//        }
//    }

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
