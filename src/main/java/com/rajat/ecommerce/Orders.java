package com.rajat.ecommerce;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Orders {

    @NotNull
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
