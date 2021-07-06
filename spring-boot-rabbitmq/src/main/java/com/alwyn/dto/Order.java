package com.alwyn.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order implements Serializable {

    private static final long serialVersionUID = -2015265146348983127L;

    private Long orderId;
    private Long shopId;

    public Order() {
    }

    public Order(Long orderId, Long shopId) {
        this.orderId = orderId;
        this.shopId = shopId;
    }
}