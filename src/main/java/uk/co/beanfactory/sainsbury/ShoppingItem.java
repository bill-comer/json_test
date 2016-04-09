package uk.co.beanfactory.sainsbury;

import java.net.URL;

/**
 * Details of an indevidual item from a store display
 * Created by bill on 09/04/2016.
 */
public class ShoppingItem {
    private String title;
    private Double unitPrice;
    private String description;
    private Double size;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnit_price() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public static ShoppingItem create(String title, Double size, String description, Double unitPrice) {
        ShoppingItem item = new ShoppingItem();
        item.title = title;
        item.size = size;
        item.description = description;
        item.unitPrice = unitPrice;

        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingItem)) return false;

        ShoppingItem item = (ShoppingItem) o;

        if (getTitle() != null ? !getTitle().equals(item.getTitle()) : item.getTitle() != null) return false;
        if (unitPrice != null ? !unitPrice.equals(item.unitPrice) : item.unitPrice != null) return false;
        if (getDescription() != null ? !getDescription().equals(item.getDescription()) : item.getDescription() != null)
            return false;
        return getSize() != null ? getSize().equals(item.getSize()) : item.getSize() == null;

    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getSize() != null ? getSize().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ShoppingItem{" +
                "title='" + title + '\'' +
                ", unitPrice=" + unitPrice +
                ", description='" + description + '\'' +
                ", size=" + size +
                '}';
    }
}
