package com.myapp.kiosk;

import com.myapp.kiosk.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<MenuItem> menuItems = new ArrayList<>(List.of(
                new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
                new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거")
        ));
        Scanner sc = new Scanner(System.in);

        while (true) {
//            System.out.println("""
//                    [ SHAKESHACK MENU ]\s
//                    1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거\s
//                    2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거\s
//                    3. Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거\s
//                    4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거\s
//                    0. 종료""");
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
