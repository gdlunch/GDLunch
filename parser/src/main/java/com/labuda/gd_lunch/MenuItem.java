package com.labuda.gd_lunch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Single menu item
 */
public class MenuItem {

    /**
     * Logger
     */
    private final static Logger log = LoggerFactory.getLogger(MenuItem.class);

    /**
     * Name of the item
     */
    private String name;

    /**
     * Price
     */
    private float price;

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
        log.trace("Created MenuItem with name=[" + name + "] and price=[" + price + "]");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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
