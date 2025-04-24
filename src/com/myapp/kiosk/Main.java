package com.myapp.kiosk;

import com.myapp.kiosk.menu.Menu;
import com.myapp.kiosk.menu.MenuItem;
import com.myapp.kiosk.order.Kiosk;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<MenuItem> burgerMenu = new ArrayList<>(List.of(
                new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
                new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거")
        ));
        List<MenuItem> drinkMenu = new ArrayList<>(List.of(
                new MenuItem("Cola", 1.8, "시원한 코카콜라"),
                new MenuItem("Cider", 1.8, "깔끔한 레몬라임 사이다"),
                new MenuItem("IcedTea", 2.0, "달콤한 복숭아티"),
                new MenuItem("Milkshake", 2.3, "부드러운 밀크쉐이크")
        ));
        List<MenuItem> dessertMenu = new ArrayList<>(List.of(
                new MenuItem("VanillaIceCream", 1.8, "시원한 바닐라맛 아이스크림"),
                new MenuItem("FrenchFries", 2.2, "바삭한 감자튀김"),
                new MenuItem("FriedChicken", 4.2, "두툼한 닭다리 1조각")
        ));

        Menu burgers = new Menu("Burgers", burgerMenu);
        Menu drinks = new Menu("Drinks", drinkMenu);
        Menu desserts = new Menu("Desserts", dessertMenu);

        Kiosk kiosk = new Kiosk(burgers, drinks, desserts);
        kiosk.start();
    }
}
