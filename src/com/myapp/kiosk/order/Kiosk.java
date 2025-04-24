package com.myapp.kiosk.order;

import com.myapp.kiosk.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    List<MenuItem> menuItems = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public Kiosk(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void start() {
        while (true) {
            System.out.println("[ SHAKESHACK MENU ]");
            for (int i=0; i<menuItems.size(); i++) {
                MenuItem menuItem = menuItems.get(i);
                System.out.printf("%d %-13s | W %3.1f | %s\n", i+1, menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
            }
            System.out.println("0. 종료");

            int menuSelect = sc.nextInt();
            if (menuSelect == 0 ) {
                System.out.println("프로그램을 종료합니다.");
                return;
            } else if (menuSelect > 4) {
                System.out.println("잘못된 입력입니다");
                continue;
            }
            MenuItem menuItem = menuItems.get(menuSelect-1);
            System.out.printf("선택한 메뉴 : %s | W %3.1f | %s%n ", menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
        }
    }
}
