import file.FileManager;
import model.*;
import model.enums.*;
import repository.CustomerRepository;
import repository.OrderRepository;
import repository.ProductRepository;
import service.CustomerService;
import service.OrderService;
import service.ProductService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    // todo: separare pe roluri cu 2 meniuri, login + creare statistici cu masive + fa ceva exceptii custom + documentatie
    // total optional daca te plisctisesti candva si e gata tot: interfata grafica pt unele functionalitati
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CustomerRepository customerRepo = new CustomerRepository("data/customers.txt");
        ProductRepository productRepo = new ProductRepository("data/products.txt");

        List<Customer> customers = customerRepo.load();
        List<AProduct> products = productRepo.load();

        ProductService productService = new ProductService(productRepo);
        CustomerService customerService = new CustomerService(customerRepo);
        OrderRepository orderRepo = new OrderRepository("data/orders.dat");
        OrderService orderService = new OrderService(orderRepo);

        List<Order> restoredOrders = FileManager.readObject("data/orders.dat", List.class);
        if (restoredOrders != null) {
            for (Order o : restoredOrders) {
                orderService.add(o);
            }
        }

        boolean running = true;
        while (running) {
            showMainMenu();
            int option = readInt("Alege o optiune: ");

            switch (option) {
                case 1 -> manageCustomers(customerService);
                case 2 -> manageProducts(productService);
                case 3 -> manageOrders(orderService, customerService, productService);
                case 4 -> showStatistics(orderService, productService, customerService);
                case 5 -> {
                    saveAll(customerService, productService, orderService);
                    System.out.println("Datele au fost salvate. La revedere!");
                    running = false;
                }
                default -> System.out.println("Optiune invalida!");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\n--- MENIU PRINCIPAL ---");
        System.out.println("1. Gestionare clienti");
        System.out.println("2. Gestionare produse");
        System.out.println("3. Gestionare comenzi");
        System.out.println("4. Statistici (viitor)");
        System.out.println("5. Iesire");
    }

    private static void manageCustomers(CustomerService service) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Gestionare clienti ---");
            System.out.println("1. Listare clienti");
            System.out.println("2. Adauga client");
            System.out.println("0. Inapoi la meniul principal");
            int opt = readInt("Alege o optiune: ");

            switch (opt) {
                case 1 -> {
                    List<Customer> clients = service.getAll();
                    if (clients.isEmpty()) System.out.println("Nu exista clienti.");
                    else clients.forEach(System.out::println);
                }
                case 2 -> {
                    String name = readString("Nume client: ");
                    String address = readString("Adresa: ");
                    String email = readString("Email: ");
                    Customer c = new Customer(name, address, email);
                    service.add(c);
                    System.out.println("Client adaugat: " + c);
                }
                case 0 -> running = false; // back
                default -> System.out.println("Optiune invalida!");
            }
        }
    }

    private static void manageProducts(ProductService service) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Gestionare produse ---");
            System.out.println("1. Listare produse");
            System.out.println("2. Adauga produs");
            System.out.println("0. Inapoi la meniul principal");
            int opt = readInt("Alege o optiune: ");

            switch (opt) {
                case 1 -> {
                    List<AProduct> products = service.getAll();
                    if (products.isEmpty()) System.out.println("Nu exista produse.");
                    else products.forEach(System.out::println);
                }
                case 2 -> {
                    System.out.println("Tip produs: 1. Haine  2. Carte  3. Electronice");
                    int type = readInt("Alege tip: ");
                    AProduct p = null;
                    switch (type) {
                        case 1 -> {
                            String name = readString("Nume: ");
                            double price = readDouble("Pret: ");
                            int stock = readInt("Stoc: ");
                            String brand = readString("Brand: ");
                            String color = readString("Culoare: ");
                            p = new ClothingProduct(stock, name, price, brand, Size.M, color, List.of("bumbac"), Category.UNISEX);
                        }
                        case 2 -> {
                            String name = readString("Nume carte: ");
                            double price = readDouble("Pret: ");
                            int stock = readInt("Stoc: ");
                            String author = readString("Autor: ");
                            int pages = readInt("Nr pagini: ");
                            int year = readInt("An aparitie: ");
                            p = new BookProduct(name, price, "BrandX", stock, author, pages, year, Genre.FANTASY);
                        }
                        case 3 -> {
                            String name = readString("Nume: ");
                            double price = readDouble("Pret: ");
                            int stock = readInt("Stoc: ");
                            String brand = readString("Brand: ");
                            double weight = readDouble("Greutate: ");
                            boolean smart = readBoolean("Smart? (true/false): ");
                            int warranty = readInt("Luni garantie: ");
                            p = new ElectronicProduct(name, price, brand, stock, warranty, smart, weight);
                        }
                        default -> System.out.println("Tip produs invalid!");
                    }
                    if (p != null) {
                        service.add(p);
                        System.out.println("Produs adaugat: " + p);
                    }
                }
                case 0 -> running = false;
                default -> System.out.println("Optiune invalida!");
            }
        }
    }

    private static void manageOrders(OrderService orderService, CustomerService customerService, ProductService productService) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Gestionare comenzi ---");
            System.out.println("1. Plasare comanda");
            System.out.println("2. Listare comenzi");
            System.out.println("3. Sterge comanda");
            System.out.println("0. Inapoi la meniul principal");
            int opt = readInt("Alege o optiune: ");

            switch (opt) {
                case 1 -> {
                    List<Customer> clients = customerService.getAll();
                    if (clients.isEmpty()) {
                        System.out.println("Nu exista clienti.");
                        break;
                    }
                    System.out.println("Selecteaza client:");
                    for (int i = 0; i < clients.size(); i++) {
                        System.out.println((i + 1) + ". " + clients.get(i));
                    }
                    int clientIndex = readInt("Alege client: ") - 1;
                    if (clientIndex < 0 || clientIndex >= clients.size()) {
                        System.out.println("Client invalid!");
                        break;
                    }
                    Customer client = clients.get(clientIndex);
                    Order order = new Order(client, OrderStatus.PENDING);

                    List<AProduct> products = productService.getAll();
                    while (true) {
                        System.out.println("Selecteaza produs (0 pentru a opri adaugarea comenzii):");
                        for (int i = 0; i < products.size(); i++) {
                            System.out.println((i + 1) + ". " + products.get(i));
                        }
                        int prodIndex = readInt("Produs: ") - 1;

                        if (prodIndex == -1) {
                            System.out.println("Adaugarea comenzii a fost anulata.");
                            order = null;
                            break;
                        }
                        if (prodIndex < 0 || prodIndex >= products.size()) {
                            System.out.println("Produs invalid!");
                            continue;
                        }

                        AProduct prod = products.get(prodIndex);
                        int quantity = readInt("Cantitate (0 pentru a opri adaugarea comenzii): ");
                        if (quantity == 0) {
//                            System.out.println("Adaugarea comenzii a fost anulata.");
                            order = null;
                            break;
                        }

                        try {
                            order.addToCart(prod, quantity);
                            System.out.println("Adaugat: " + prod + " x" + quantity);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Eroare: " + e.getMessage());
                        }
                    }

                    if (order != null && !order.getCart().isEmpty()) {
                        orderService.add(order);
                        System.out.println("Comanda plasata: " + order);
                    } else {
                        System.out.println("Nu s-a plasat nicio comanda deoarece cosul este gol!");
                    }
                }
                case 2 -> {
                    List<Order> orders = orderService.getAll();
                    if (orders.isEmpty()) System.out.println("Nu exista comenzi.");
                    else orders.forEach(System.out::println);
                }
                case 3 -> {
                    List<Order> orders = orderService.getAll();
                    if (orders.isEmpty()) {
                        System.out.println("Nu exista comenzi.");
                        break;
                    }
                    for (int i = 0; i < orders.size(); i++) {
                        System.out.println((i + 1) + ". " + orders.get(i));
                    }
                    int index = readInt("Selecteaza comanda de sters: ") - 1;
                    if (index < 0 || index >= orders.size()) {
                        System.out.println("Comanda invalida!");
                        break;
                    }
                    orderService.remove(orders.get(index));
                    System.out.println("Comanda stearsa.");
                }
                case 0 -> running = false; // back
                default -> System.out.println("Optiune invalida!");
            }
        }
    }

    private static void showStatistics(OrderService orderService, ProductService productService, CustomerService customerService) {
        System.out.println("\n--- Statistici (in lucru) ---");
    }

    private static void saveAll(CustomerService customerService, ProductService productService, OrderService orderService) {
        // Salvare clienti
        List<String> customerLines = customerService.getAll().stream()
                .map(Customer::toFileString)
                .toList();
        FileManager.writeLines("data/customers.txt", customerLines);

        // Salvare produse
        List<String> productLines = productService.getAll().stream()
                .map(AProduct::toFileString)
                .toList();
        FileManager.writeLines("data/products.txt", productLines);

        // Salvare comenzi binar
        FileManager.writeObject("data/orders.dat", orderService.getAll());
    }

    private static int readInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Trebuie sa introduci un numar!");
                scanner.nextLine();
            }
        }
    }


    private static double readDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Trebuie sa introduci un numar!");
                scanner.nextLine();
            }
        }
    }


    private static String readString(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private static boolean readBoolean(String msg) {
        while (true) {
            System.out.print(msg);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) return Boolean.parseBoolean(input);
            System.out.println("Trebuie sa introduci true sau false!");
        }
    }
}
