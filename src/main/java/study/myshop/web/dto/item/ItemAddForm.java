package study.myshop.web.dto.item;

import lombok.Data;
import study.myshop.domain.item.ItemStatus;
import study.myshop.domain.member.Seller;

@Data
public class ItemAddForm {

    private String name;
    private Integer salesQuantityGram;
    private Integer salesQuantityNum;
    private Integer originalPrice;
    private Integer stock;
    private String description;

}
