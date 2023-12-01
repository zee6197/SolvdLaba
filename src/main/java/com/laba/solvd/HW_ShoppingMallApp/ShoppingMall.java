package com.laba.solvd.HW_ShoppingMallApp;

import com.laba.solvd.HW_ShoppingMallApp.people.Customer;
import com.laba.solvd.HW_ShoppingMallApp.people.Employee;
import com.laba.solvd.HW_ShoppingMallApp.shop.Inventory;
import com.laba.solvd.HW_ShoppingMallApp.shop.Shop;
import com.laba.solvd.HW_ShoppingMallApp.shop.Product;
import sun.util.resources.LocaleData;

import java.time.LocalDate;
import java.util.Objects;
import java.util.*;

public class ShoppingMall {

    public ArrayList<Object> inventory;
    private String name;
    private LocaleData establishedDate;
    private static int numberOfMalls;


    // hierarchy here:
    public Set<Shop> shops;
    public Set<Employee> employees;
    public Set<Customer> customers;


    public ShoppingMall(String name, LocaleData date) {
        this.name = name;
        this.establishedDate = date;
        // hierarchy here:
        this.shops = new HashSet<>();
        this.employees = new HashSet<>();
        this.customers = new HashSet<>();
    }

    // Static initialization block
    static {
        // Initial count of malls
        numberOfMalls = 0;
    }

    private static void incrementMallCount() {
        numberOfMalls++;
    }

    public static int getNumberOfMalls() {
        return numberOfMalls;
    }

    public void addShop(Shop shop) {
        shops.add(shop);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocaleData getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(LocaleData establishedDate) {
        this.establishedDate = establishedDate;
    }

    // A method with some logic
    public void printWelcomeMessage() {
        System.out.println("Welcome to " + name + "!");
    }


    // Method to add stock to a shop's inventory
    public void addStockToShop(String shopName, Product product, int quantity) {
        for (Shop shop : shops) {
            if (shop.getShopName().equals(shopName)) {
                shop.getInventory().addStock(product, quantity);
                break;
            }
        }
    }

    // Method to apply a discount to all products in the mall
    public void applyDiscountToAllProducts(double discountRate) {
        for (Shop shop : shops) {
            Inventory inventory = shop.getInventory();
            for (Product product : inventory.getProductStock().keySet()) {
                product.offerDiscount(discountRate);
            }
        }
    }


    @Override
    public String toString() {
        return "ShoppingMall{" +
                "name='" + name + '\'' +
                ", establishedDate=" + establishedDate +
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, establishedDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ShoppingMall that = (ShoppingMall) obj;
        return Objects.equals(name, that.name) &&
                Objects.equals(establishedDate, that.establishedDate);
    }
}