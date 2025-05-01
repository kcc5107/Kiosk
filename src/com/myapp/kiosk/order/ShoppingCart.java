package com.myapp.kiosk.order;

import com.myapp.kiosk.menu.MenuItem;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

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

    void checkCartEmpty() {
        if (shoppingCart.isEmpty()) {
            throw new InputMismatchException();
        }
    }

    void removeItem(String search) {
        List<MenuItem> collect = shoppingCart.keySet().stream().filter(item -> item.getName().toLowerCase().contains(search)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            for (MenuItem item : collect) {
                System.out.println(item.getName() + "가 삭제되었습니다.");
                shoppingCart.remove(item);
            }
        } else {
            System.out.println("해당하는 품목이 없습니다.");
        }
    }

    double totalPrice() {
        double total = 0;
        for (MenuItem item : shoppingCart.keySet()) {
            total += item.getPrice() * shoppingCart.get(item);
        }
        return total;
    }
}