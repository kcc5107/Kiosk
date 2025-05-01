package com.myapp.kiosk.order;

import com.myapp.kiosk.menu.Menu;
import com.myapp.kiosk.menu.MenuItem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private List<Menu> menuCategory = new ArrayList<>();
    private final ShoppingCart shoppingCart;
    Scanner sc = new Scanner(System.in);

    public Kiosk(List<Menu> menuCategory, ShoppingCart shoppingCart) {
        this.menuCategory = menuCategory;
        this.shoppingCart = shoppingCart;
    }

    public void start() {
        while (true) {
            // 카테고리 목록 출력
            printCategory();

            // 카테고리 선택
            int mainMenuSelect = scannerInt(0, 5);
            switch (mainMenuSelect) {
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                case 4:
                    // 장바구니가 비어있을 때 잘못 입력한 경우의 예외처리
                    try {
                        shoppingCart.checkCartEmpty();
                    } catch (InputMismatchException e) {
                        System.out.println("유효하지 않은 번호입니다.");
                        continue;
                    }

                    // 장바구니 물품의 총 비용 계산
                    double total = shoppingCart.totalPrice();
                    System.out.println("아래와 같이 주문 하시겠습니까?\n[ Orders ]");
                    // 장바구니 목록 출력
                    shoppingCart.printItem();
                    System.out.printf("[ Total ]%nW %3.1f%n", total);
                    // 주문 메뉴
                    ordersMenu(total);
                    break;
                case 5:
                    // 장바구니가 비어있을 때 잘못 입력
                    try {
                        shoppingCart.checkCartEmpty();
                    } catch (InputMismatchException e) {
                        System.out.println("유효하지 않은 번호입니다.");
                        continue;
                    }
                    shoppingCart.clearCart();
                    break;
                default:
                    Menu menu = menuCategory.get(mainMenuSelect - 1);
                    // 카테고리 선택 후, 메뉴 목록 출력
                    System.out.println("\n[ " + menu.getCategory().toUpperCase() + " MENU ]");
                    menu.printMenu();
                    System.out.println("0. 뒤로가기");

                    // 메뉴 선택
                    MenuItem menuItem = selectMenuItem(menu);
                    if (menuItem == null) {
                        continue;
                    }

                    // 장바구니 추가 확인
                    confirmAdd(menuItem);
                    break;
            }
        }
    }

    private void printCategory() {
        System.out.println("\n[ MAIN MENU ]");
        for (int i = 0; i < menuCategory.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, menuCategory.get(i).getCategory());
        }
        System.out.println("0. 종료");

        // 장바구니가 비어있지 않을 때만 추가 출력
        if (!shoppingCart.getShoppingCart().isEmpty()) {
            System.out.println("\n[ ORDER MENU ]");
            System.out.println("4. Orders     | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. Cancel     | 진행중인 주문을 취소합니다.");
        }
    }

    private int scannerInt(int range1, int range2) {
        // 매개변수로 숫자 범위 제한
        int num = -1;
        while (true) {
            try {
                num = sc.nextInt();
                // 버퍼 비우기
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("숫자를 입력해주세요.");
                sc.nextLine();
                continue;
            }
            if (num >= range1 && num <= range2) {
                break;
            }
            System.out.println("유효하지 않은 번호입니다.");
        }
        // 초기화값 -1을 리턴하게된다해도 결국 index 예외 처리가 일어남.
        return num;
    }

    // Enum 활용한 메서드
    private double userTypeSelect(double total) {
        System.out.println("할인 정보를 입력해주세요.");
        // 사용자 유형별 할인율 출력
        UserTypeDiscount.printUserTypeDiscount();
        int userTypeNum = scannerInt(1, 4);
        // 사용자 유형에 맞는 할인 적용
        UserTypeDiscount userTypeDiscount = UserTypeDiscount.selectUserType(userTypeNum);
        return userTypeDiscount.applyDiscount(total);
    }

    private void ordersMenu(double total) {
        boolean orderFlag = true;
        while (orderFlag) {
            System.out.println("\n1. 주문      2. 메뉴판      3. 품목 삭제");
            int order = scannerInt(1, 3);
            if (order == 1) {
                // 사용자 유형별 할인
                double discountTotal = userTypeSelect(total);
                System.out.printf("\n주문이 완료되었습니다. 금액은 W %3.1f 입니다%n", discountTotal);
                shoppingCart.clearCart();
                orderFlag = false;
            } else if (order == 2) {
                orderFlag = false;
            } else if (order == 3) {
                System.out.println("입력받은 문자를 포함한 이름의 주문 품목을 삭제합니다. (3글자 이상)");
                String search = sc.nextLine();
                if (search.length() < 3) {
                    System.out.println("3글자 이상 입력해주세요");
                    continue;
                }
                // 조건에 맞는 품목 삭제
                shoppingCart.removeItem(search);
                break;
            }
        }
    }

    private MenuItem selectMenuItem(Menu menu) {
        int menuSelect = scannerInt(0, 4);
        if (menuSelect == 0) {
            return null;
        }
        // 메뉴 카테고리에 따라 품목이 3개 또는 4개라 예외처리 필요
        MenuItem menuItem = null;
        try {
            return menuItem = menu.getItems().get(menuSelect - 1);
        } catch (IndexOutOfBoundsException e) {
            // 품목이 3개인데 4를 입력할 경우
            System.out.println("유효하지 않은 번호입니다.");
            return null;
        }
    }

    private void confirmAdd(MenuItem menuItem) {
        System.out.printf("선택한 메뉴 : %s | W %3.1f | %s%n", menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인      2. 취소");

        int add = scannerInt(1, 2);
        if (add == 1) {
            // 장바구니에 품목 추가
            shoppingCart.addToCart(menuItem);
            System.out.println(menuItem.getName() + "가 장바구니에 추가되었습니다.");
        }
    }
}