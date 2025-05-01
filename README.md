# 키오스크 프로그램

사용자에게 다양한 카테고리의 메뉴를 보여주고, 메뉴를 선택하여 장바구니에 추가 및 주문을 할 수 있도록 해주는 콘솔 기반 프로그램입니다.

## 사용 기술
`Java`, `Enum`, `HashMap`, `Lambda & Stream` 등

## 기능 설명
- 메뉴의 카테고리를 선택
- 메뉴 선택 및 장바구니에 추가
- 장바구니 조회 및 입력받은 3글자의 문자열이 포함된 메뉴를 장바구니에서 제거
- 주문 취소
- 사용자 유형별 할인율을 적용한 결제

## 파일 디렉토리 구조
```
src/com/myapp/kiosk/
├── Main.java
├── menu/
│   ├── Menu.java
│   └── MenuItem.java
└── order/
    ├── Kiosk.java
    ├── ShoppingCart.java
    ├── Discount.java
    └── UserTypeDiscount.java
```

## 트러블슈팅
https://github.com/kcc5107/TIL/blob/main/2025-04-28.md