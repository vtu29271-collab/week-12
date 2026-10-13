import java.util.*;

public class Main {

    static class Product {
        int id;
        String name;
        int stock;
        double price;

        public Product(int id, String name, int stock, double price) {
            this.id = id;
            this.name = name;
            this.stock = stock;
            this.price = price;
        }

        @Override
        public String toString() {
            return String.format("ID: %d | Name: %-12s | Stock: %-3d | Price: $%.2f", id, name, stock, price);
        }
    }

    private final Map<Integer, Product> inventory = new HashMap<>();

    public void addProduct(int id, String name, int stock, double price) {
        inventory.put(id, new Product(id, name, stock, price));
    }

    public void updateStockAfterSale(int productId, int quantitySold) {
        Product p = inventory.get(productId);
        if (p != null) {
            if (p.stock >= quantitySold) {
                p.stock -= quantitySold;
                System.out.println("Sale updated: " + p.name + " remaining stock: " + p.stock);
            } else {
                System.out.println("Insufficient stock for product ID: " + productId);
            }
        } else {
            System.out.println("Product ID " + productId + " not found.");
        }
    }

    public List<Product> getLowStockProducts(int threshold) {
        List<Product> lowStockList = new ArrayList<>();
        for (Product p : inventory.values()) {
            if (p.stock < threshold) {
                lowStockList.add(p);
            }
        }
        return lowStockList;
    }

    public void displaySortedByStock() {
        List<Product> productList = new ArrayList<>(inventory.values());
        productList.sort(Comparator.comparingInt(p -> p.stock));

        System.out.println("\n--- Products Sorted by Remaining Stock ---");
        for (Product p : productList) {
            System.out.println(p);
        }
    }

    public Product getProductById(int productId) {
        return inventory.get(productId);
    }

    public static void main(String[] args) {
        Main system = new Main();

        system.addProduct(101, "Milk", 15, 2.50);
        system.addProduct(102, "Bread", 8, 1.80);
        system.addProduct(103, "Eggs", 25, 3.20);
        system.addProduct(104, "Butter", 4, 4.00);

        System.out.println("=== UPDATING STOCK ===");
        system.updateStockAfterSale(101, 10);

        System.out.println("\n=== LOW-STOCK PRODUCTS (Below 10) ===");
        List<Product> lowStock = system.getLowStockProducts(10);
        for (Product p : lowStock) {
            System.out.println(p);
        }

        system.displaySortedByStock();

        System.out.println("\n=== PRODUCT RETRIEVAL BY ID ===");
        int queryId = 103;
        Product retrieved = system.getProductById(queryId);
        if (retrieved != null) {
            System.out.println("Found Product: " + retrieved);
        } else {
            System.out.println("Product not found.");
        }
    }
}