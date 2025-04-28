package com.myapp.kiosk.order;

import com.myapp.kiosk.menu.Menu;
import com.myapp.kiosk.menu.MenuItem;

import java.util.*;

public class Kiosk {
    private List<Menu> menuCategory = new ArrayList<>();
    private ShoppingCart shoppingCart;
    Scanner sc = new Scanner(System.in);

    public Kiosk(List<Menu> menuCategory, ShoppingCart shoppingCart) {
        this.menuCategory = menuCategory;
        this.shoppingCart = shoppingCart;
    }

    public void start() {
        while (true) {
            // 카테고리 목록, 오더 메뉴 출력
            printCategory();

            // 카테고리 선택
            int mainMenuSelect = scannerInt();
            switch (mainMenuSelect) {
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                case 4:
                    // 장바구니가 비어있을 때 잘못 입력
                    try {
                        checkCartEmpty();
                    } catch (InputMismatchException e) {
                        System.out.println("유효하지 않은 번호입니다.");
                        continue;
                    }
                    List<MenuItem> items = shoppingCart.getShoppingCart();
                    double total = 0;
                    for (MenuItem item : items) {
                        total += item.getPrice();
                    }

                    System.out.println("아래와 같이 주문 하시겠습니까?\n[ Orders ]");
                    shoppingCart.printItem();
                    System.out.printf("[ Total ]%nW %3.1f%n", total);
                    System.out.println("\n1. 주문      2. 메뉴판");
                    int order = scannerInt();
                    if (order == 1) {
                        System.out.printf("\n주문이 완료되었습니다. 금액은 W %3.1f 입니다%n", total);
                        shoppingCart.clearCart();
                    }
                    // 추가 할 부분
                    break;
                case 5:
                    // 장바구니가 비어있을 때 잘못 입력
                    try {
                        checkCartEmpty();
                    } catch (InputMismatchException e) {
                        System.out.println("유효하지 않은 번호입니다.");
                        continue;
                    }
                    shoppingCart.clearCart();
                    break;
                default:
                    Menu menu = null;
                    try {
                        menu = menuCategory.get(mainMenuSelect - 1);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("유효하지 않은 번호입니다.");
                        continue;
                    }
                    // 카테고리 선택 후 메뉴 목록 출력
                    System.out.println("\n[ " + menu.getCategory().toUpperCase() + " MENU ]");
                    menu.printMenu();
                    System.out.println("0. 뒤로가기");

                    // 메뉴 선택
                    int menuSelect = scannerInt();
                    if (menuSelect == 0) {
                        continue;
                    }

                    MenuItem menuItem = null;
                    try {
                        menuItem = menu.getItems().get(menuSelect - 1);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("유효하지 않은 번호입니다.");
                        continue;
                    }
                    System.out.printf("선택한 메뉴 : %s | W %3.1f | %s%n ", menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
                    System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                    System.out.println("1. 확인      2. 취소");
                    boolean flag = true;
                    while (flag) {
                        int add = scannerInt();
                        if (add == 1) {
                            shoppingCart.addToCart(menuItem);
                            System.out.println(menuItem.getName() + " 이 장바구니에 추가되었습니다.");
                            flag = false;
                        } else if (add == 2) {
                            flag = false;
                        } else {
                            System.out.println("유효하지 않은 번호입니다.");
                        }
                    }
                    break;
            }
        }
    }

    private void checkCartEmpty() {
        if (shoppingCart.getShoppingCart().isEmpty()) {
            throw new InputMismatchException();
        }
    }

    private void printCategory() {
        System.out.println("\n[ MAIN MENU ]");
        for (int i = 0; i < menuCategory.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, menuCategory.get(i).getCategory());
        }
        System.out.println("0. 종료");

        if (!shoppingCart.getShoppingCart().isEmpty()) {
            System.out.println("\n[ ORDER MENU ]");
            System.out.println("4. Orders     | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. Cancel     | 진행중인 주문을 취소합니다.");
        }
    }

    private int scannerInt() {
        int num = -1;
        try {
            num = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해주세요.");
        }
        // -1을 리턴하게되면 그 뒤, 결국 index 예외 처리를 함
        return num;
    }
}