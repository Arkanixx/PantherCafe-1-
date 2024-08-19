package com.softeng2.PantherCafe.Menu;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(
        name = "menu",
        uniqueConstraints = {
                @UniqueConstraint(name = "item_unique_name", columnNames = "itemName")
        }
)
public class Menu {
    @Id
    @Column(
            name = "itemName",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String itemName;
    @Column(
            name = "price",
            nullable = false,
            columnDefinition = "numeric"
    )
    private double price;

    public Menu(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    public Menu() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Double.compare(price, menu.price) == 0 && Objects.equals(itemName, menu.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, price);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "itemName='" + itemName + '\'' +
                ", price=" + price +
                '}';
    }
}
