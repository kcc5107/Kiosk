package com.myapp.kiosk.order;

import com.myapp.kiosk.menu.MenuItem;

import java.util.HashMap;

public class ShoppingCart {
    HashMap<MenuItem, Integer> shoppingCart = new HashMap<>();

    void addToCart(MenuItem item) {
        if (shoppingCart.containsKey(item)) {
            int count = shoppingCart.get(item) + 1;
            shoppingCart.put(item, count);
        } else {
            shoppingCart.put(item, 1);
        }
    }

    HashMap<MenuItem, Integer> getShoppingCart() {
        return shoppingCart;
    }

    void printItem() {
        for (MenuItem menuItem : shoppingCart.keySet()) {
            System.out.printf("%-13s | W %3.1f | %s | 수량 : %d\n",
                    menuItem.getName(), menuItem.getPrice(), menuItem.getDescription(), shoppingCart.get(menuItem));
        }
    }

    void clearCart() {
        shoppingCart.clear();
    }

}