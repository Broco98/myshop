package study.myshop.domain.order;

public enum OrderStatus {

    // TODO 추가될 수 있음
    ORDER("주문"), DELIVER("배송중"), COMP("완료"),
    CANCEL("취소");

    final String description;

    OrderStatus(String description) {
        this.description = description;
    }

}
