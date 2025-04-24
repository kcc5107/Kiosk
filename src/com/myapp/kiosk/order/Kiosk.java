package com.myapp.kiosk.order;

import com.myapp.kiosk.menu.MenuItem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    List<MenuItem> menuItems = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public Kiosk(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void start() throws InputMismatchException, IndexOutOfBoundsException {
        while (true) {
            System.out.println("[ SHAKESHACK MENU ]");
            for (int i = 0; i < menuItems.size(); i++) {
                MenuItem menuItem = menuItems.get(i);
                System.out.printf("%d %-13s | W %3.1f | %s\n", i + 1, menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
            }
            System.out.println("0. 종료");

            int menuSelect = 0;
            try {
                menuSelect = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요.");
                sc.nextLine();
                continue;
            }
            if (menuSelect == 0) {
                System.out.println("프로그램을 종료합니다.");
                return;
            }

            MenuItem menuItem = null;
            try {
                menuItem = menuItems.get(menuSelect - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("지정된 범위의 숫자를 입력해주세요");
                continue;
            }
            System.out.printf("선택한 메뉴 : %s | W %3.1f | %s%n ", menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
        }
    }
}
