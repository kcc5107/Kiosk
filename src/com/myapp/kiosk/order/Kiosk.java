package com.myapp.kiosk.order;

import com.myapp.kiosk.menu.Menu;
import com.myapp.kiosk.menu.MenuItem;

import java.util.*;

public class Kiosk {
    private List<Menu> menuCategory = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public Kiosk(List<Menu> menuCategory) {
        this.menuCategory = menuCategory;
    }

    public void start() {
        while (true) {
            // 카테고리 목록 출력
            System.out.println("\n[ MAIN MENU ]");
            for (int i = 0; i < menuCategory.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, menuCategory.get(i).getCategory());
            }
            System.out.println("0. 종료");

            // 카테고리 선택
            int mainMenuSelect = 0;
            try {
                mainMenuSelect = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요.");
                sc.nextLine();
                continue;
            }
            if (mainMenuSelect == 0) {
                System.out.println("프로그램을 종료합니다.");
                return;
            }

            Menu menu = null;
            try {
                menu = menuCategory.get(mainMenuSelect - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("없는 카테고리 번호입니다.");
                continue;
            }
            // 카테고리 선택 후 메뉴 목록 출력
            System.out.println("\n[ " + menu.getCategory().toUpperCase() + " MENU ]");
            menu.printMenu();
//            for (int i = 0; i < menu.getItems().size(); i++) {
//                MenuItem menuItem = menu.getItems().get(i);
//                System.out.printf("%d %-13s | W %3.1f | %s\n", i + 1, menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
//            }
            System.out.println("0. 뒤로가기");

            // 메뉴 선택
            int menuSelect = 0;
            try {
                menuSelect = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요.");
                sc.nextLine();
                continue;
            }
            if (menuSelect == 0) {
                continue;
            }

            MenuItem menuItem = null;
            try {
                menuItem = menu.getItems().get(menuSelect - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("없는 메뉴의 번호입니다.");
                continue;
            }
            System.out.printf("선택한 메뉴 : %s | W %3.1f | %s%n ", menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
        }
    }
}