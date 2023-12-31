package com.laba.solvd.HW_ShoppingMallApp.shop;

import com.laba.solvd.HW_ShoppingMallApp.checkout.Checkout;
import com.laba.solvd.HW_ShoppingMallApp.interfaces.ShoppingOperations;
import com.laba.solvd.HW_ShoppingMallApp.payments.Payment;
import com.laba.solvd.HW_ShoppingMallApp.payments.Receipt;
import com.laba.solvd.HW_ShoppingMallApp.people.Customer;
import com.laba.solvd.HW_ShoppingMallApp.people.Employee;

import java.util.*;

public class Shop implements ShoppingOperations {
    private String shopName;
    public Payment payment;
    public Receipt receipt;



    // hierarchy here:
    private final Inventory inventory;
    private Set<Employee> employees;
    private Set<Customer> customers;
    public final Map<String, Product> productCatalog = new HashMap<>();
    public final Checkout cart = new Checkout();

    public Shop(String shopName) {
        this.shopName = shopName;
        // Connecting classes in a hierarchy
        this.inventory = new Inventory();
        this.employees = new HashSet<>(employees);
        this.customers = new HashSet<>(customers);
    }


    @Override
    public void close() {
        // Logic for closing the shop
        System.out.println(shopName + " is now closed.");
    }

    // Getters and Setters
    public String getShopName() {
        return shopName;
    }

    public Payment getPayment() {
        return payment;
    }
    public Receipt getReceipt() {
        return receipt;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    // A method with logic
    public void restockInventory(Product product, int quantity) {
        inventory.addProduct(product, quantity);
    }


    // Overloaded methods
    public void open() {

        System.out.println(shopName + " is now open.");
    }

    @Override
    public String toString() {
        return "MyShop{" +
                "shopName='" + shopName + '\'' +
                ", inventory=" + inventory +
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(shopName, inventory);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Shop myShop = (Shop) obj;
        return Objects.equals(shopName, myShop.shopName) &&
                Objects.equals(inventory, myShop.inventory);
    }
}