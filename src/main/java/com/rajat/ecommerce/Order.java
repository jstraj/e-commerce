package com.rajat.ecommerce;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

@Entity
@Table(name = "ecommerce_orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "customer_id")
    private Long customerId;

    @NotNull
    @Column(name = "item_id")
    private Long itemId;

    @NotNull
    @Column(name = "number_of_items_ordered")
    private Integer numberOfItemsOrdered;

    @NotBlank
    @Column(name = "customer_email")
    private String customerEmail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getNumberOfItemsOrdered() {
        return numberOfItemsOrdered;
    }

    public void setNumberOfItemsOrdered(Integer numberOfItemsOrdered) {
        this.numberOfItemsOrdered = numberOfItemsOrdered;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
