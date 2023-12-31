package com.laba.solvd.HW_ShoppingMallApp.shop;

import com.laba.solvd.HW_ShoppingMallApp.exceptions.InsufficientStockException;
import com.laba.solvd.HW_ShoppingMallApp.interfaces.InventoryManagement;

import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.time.LocalDate;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Inventory implements InventoryManagement {


    // Example of private properties
    private LocalDate lastStockUpdate;
    private Map<Product, Integer> productStock;


    // Custom constructor that initializes the product stock and sets the last stock update date
    public Inventory() {
        this.lastStockUpdate = LocalDate.now();
        this.productStock = new HashMap<>();
    }


    // Method to extract class information using reflection
    public void reflectClassInfo() {
        try {
            Class<?> cls = this.getClass();
            System.out.println("Class: " + cls.getName());
            // Extract fields
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                System.out.println("Field: " + field.getName() + ", Type: " + field.getType());
            }
            // Extract constructors
            Constructor<?>[] constructors = cls.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println("Constructor: " + constructor);
            }
            // Extract methods
            Method[] methods = cls.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println("Method: " + method.getName() + ", Return type: " + method.getReturnType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /// Implementing InventoryManagement methods
    @Override
    public void addStock(Product product, int quantity) {
        int currentQuantity = productStock.getOrDefault(product, 0);
        productStock.put(product, currentQuantity + quantity);
        lastStockUpdate = LocalDate.now();
        writeInventoryToFile(); // Write to file after updating inventory
    }

    @Override
    public int checkStock(Product product) {

        return productStock.getOrDefault(product, 0);
    }


    // A method to add a product to the inventory
    public void addProduct(Product product, int quantity) {
        int currentQuantity = productStock.getOrDefault(product, 0);
        productStock.put(product, currentQuantity + quantity);
        lastStockUpdate = LocalDate.now();
        writeInventoryToFile(); // Write to file after adding a product
    }


    public void writeInventoryToFile() {
        File file = new File("inventory.txt");
        StringBuilder inventoryDetails = new StringBuilder();
        for (Map.Entry<Product, Integer> entry : productStock.entrySet()) {
            inventoryDetails.append(entry.getKey().toString())
                    .append(" - Stock: ")
                    .append(entry.getValue())
                    .append(System.lineSeparator());
        }

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(inventoryDetails.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // A method to get the quantity of a product in the inventory
    public int getProductQuantity(Product product) {
        return productStock.getOrDefault(product, 0);
    }

    // A method to check if the inventory is empty
    public boolean isEmpty() {
        return productStock.isEmpty();
    }

    // A method to remove a product from the inventory (with overload for different quantities)
    public void removeProduct(Product product) throws InsufficientStockException {
        removeProduct(product, 1); // Remove a single unit by default
    }


    public void removeProduct(Product product, int quantity) throws InsufficientStockException {
        int currentStock = productStock.getOrDefault(product, 0);
        if (quantity > currentStock) {
            throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
        }
        productStock.put(product, currentStock - quantity);
    }

    // Getters and Setters
    public Map<Product, Integer> getProductStock() {
        return new HashMap<>(productStock); // Returning a copy for encapsulation
    }

    public LocalDate getLastStockUpdate() {
        return lastStockUpdate;
    }

    public void setLastStockUpdate(LocalDate lastStockUpdate) {
        this.lastStockUpdate = lastStockUpdate;
    }

    // Static method example
    public static void printInventoryStatus(Inventory inventory) {
        if (inventory.isEmpty()) {
            System.out.println("The inventory is currently empty.");
        } else {
            System.out.println("The inventory contains products.");
        }
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "productStock=" + productStock +
                ", lastStockUpdate=" + lastStockUpdate +
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(productStock, lastStockUpdate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Inventory inventory = (Inventory) obj;
        return Objects.equals(productStock, inventory.productStock) &&
                Objects.equals(lastStockUpdate, inventory.lastStockUpdate);
    }
}
