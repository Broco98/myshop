package study.myshop.domain.item;

public enum Marker {
    DISCOUNT("할인"), NEW("신상품"),
    PESTICIDE_FREE("무농약"), ORGANIC("유기농"), GREEN("친환경"),
    GAP("인증마크"),
    PB("자체 브랜드");

    final String description;

    Marker(String description) {
        this.description = description;
    }
}
