package study.myshop.domain.order;

public enum DeliveryStatus {

    READY("준비"), IN_DELIVERY("배송중"), COM("배송 완료");

    private String description;

    DeliveryStatus(String description) {
        this.description = description;
    }

}
