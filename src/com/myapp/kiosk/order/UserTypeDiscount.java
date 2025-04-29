package com.myapp.kiosk.order;

public enum UserTypeDiscount {
    NATIONAL_MERIT("국가유공자", 1, 0.1, price -> price * 0.9),
    SOLDIER("군인", 2, 0.05, price -> price * 0.95),
    STUDENT("학생", 3, 0.03, price -> price * 0.97),
    NORMAL("일반", 4, 0, price -> price);
// 사용 예? : String str = UserTypeDiscount.SOLDIER.userType;
    // UserTypeDiscount user = UserTypeDiscount.SOLDIER;
    // String str = user.userType;

    private final String userType;
    private final double rate;
    private final Discount discountPrice;
    private final int typeNum;

    UserTypeDiscount(String userType, int typeNum, double rate, Discount discountPrice) {
        this.userType = userType;
        this.typeNum = typeNum;
        this.rate = rate;
        this.discountPrice = discountPrice;
    }

    static UserTypeDiscount selectUserType(int userTypeNum) {
        for (UserTypeDiscount type : UserTypeDiscount.values()) {
            if (type.typeNum == userTypeNum) {
                return type;
            }
        }
        throw new IllegalArgumentException();
    }

    double applyDiscount(double price) {
        return discountPrice.discount(price);
    }

    static void printUserTypeDiscount() {
        UserTypeDiscount[] values = UserTypeDiscount.values();
        for (int i=0; i<values().length; i++) {
            System.out.printf("%d. %-7s : %.0f%%%n", i+1, values[i].userType, values[i].rate*100);
        }
    }

//    private double discount(double price) {
//        return price - (price * rate);
//    }
}
