package com.softeng2.PantherCafe.Order;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(
        name = "orders",
        uniqueConstraints = {
                @UniqueConstraint(name = "item_unique_itemname", columnNames = "itemName")
        }
)
public class Orders {
    @Id
    @SequenceGenerator(
            name ="order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    @Column(
            name = "order_id",
            updatable = false
    )
    private Long orderId;

    @Column(
            name = "itemName",
            nullable = false,
            columnDefinition = "TEXT"
    )

    private String item;

    @Column(
            name= "qty",
            nullable = false,
            columnDefinition = "numeric"
    )
    private int quantity;

    public Orders(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Orders() {

    }

    public Long getOrderId() {
        return orderId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders order = (Orders) o;
        return quantity == order.quantity && Objects.equals(orderId, order.orderId) && Objects.equals(item, order.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, item, quantity);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", item='" + item + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
