package com.myapp.kiosk.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String category;
    // 리스트 필드 3개? or 객체 3개?
    private List<MenuItem> items = new ArrayList<>();

    public Menu(String category, List<MenuItem> items) {
        this.category = category;
        this.items = items;
    }

    public String getCategory() {
        return category;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void printMenu() {
        for (int i = 0; i < items.size(); i++) {
            MenuItem menuItem = items.get(i);
            System.out.printf("%d %-13s | W %3.1f | %s\n", i + 1, menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
        }
    }
}
