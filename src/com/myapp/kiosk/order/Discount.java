package com.myapp.kiosk.order;
@FunctionalInterface
interface Discount {
    double discount(double price);
}
