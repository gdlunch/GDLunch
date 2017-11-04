package com.labuda.gd_lunch.entity;

import javax.persistence.*;

/**
 * Single menu item
 */
@Entity
@Table(name = "menu_item")
public class MenuItem {

    /**
     * Database ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the item
     */
    private String name;

    /**
     * Price
     */
    private Float price;

    /**
     * Constructor
     */
    public MenuItem() {

    }

    /**
     * Constructor
     *
     * @param name  item name
     * @param price price
     */
    public MenuItem(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;

        MenuItem menuItem = (MenuItem) o;

        if (Float.compare(menuItem.getPrice(), getPrice()) != 0) return false;
        return getName().equals(menuItem.getName());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + (getPrice() != +0.0f ? Float.floatToIntBits(getPrice()) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
