package study.myshop.dto.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemUpdateParam {

    private String name;
    private Integer salesQuantityGram;
    private Integer salesQuantityNum;
    private Integer originalPrice;
    private Integer stock;
    private String description;

    public ItemUpdateParam(String name, Integer salesQuantityGram, Integer salesQuantityNum, Integer originalPrice, Integer stock, String description) {
        this.name = name;
        this.salesQuantityGram = salesQuantityGram;
        this.salesQuantityNum = salesQuantityNum;
        this.originalPrice = originalPrice;
        this.stock = stock;
        this.description = description;
    }
}
