package study.myshop.domain.item;

public enum ItemStatus {
    SALE("판매"), STOP("중지");

    final String description;

    ItemStatus(String description) {
        this.description = description;
    }
}
