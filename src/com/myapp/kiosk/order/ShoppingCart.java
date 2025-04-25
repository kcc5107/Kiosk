package com.myapp.kiosk.order;

import com.myapp.kiosk.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<MenuItem> shoppingCart = new ArrayList<>();

    void addToCart(MenuItem item) {
        shoppingCart.add(item);
    }

    List<MenuItem> getShoppingCart() {
        return shoppingCart;
    }

    void printItem() {
        for (int i = 0; i < shoppingCart.size(); i++) {
            MenuItem menuItem = shoppingCart.get(i);
            System.out.printf("%-13s | W %3.1f | %s\n", menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
        }
    }

    void clearCart() {
        shoppingCart.clear();
    }
}