package com.labuda.gdlunch.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Daily Menu DTO class
 */
public class DailyMenuDTO {

    /**
     * Database ID
     */
    private Long id;

    /**
     * Date
     */
    private LocalDate date;

    /**
     * List of courses on the menu on specific day
     */
    private List<MenuItemDTO> menu = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<MenuItemDTO> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItemDTO> menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DailyMenuDTO)) {
            return false;
        }
        DailyMenuDTO that = (DailyMenuDTO) o;
        return Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getMenu(), that.getMenu());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getMenu());
    }

    @Override
    public String toString() {
        return "DailyMenuDTO{" +
                "id=" + id +
                ", date=" + date +
                ", menu=" + menu +
                '}';
    }
}
