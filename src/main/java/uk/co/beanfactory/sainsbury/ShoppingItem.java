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
    private String size;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getSize() {
        return new Long(size.replaceAll("\\D+", ""));
    }

    public void setSize(long size) {
        this.size = String.valueOf(size/1000L) + "Kb";
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

    public static ShoppingItem create(String title, long size, String description, Double unitPrice) {
        ShoppingItem item = new ShoppingItem();
        item.title = title;
        item.setSize(size);
        item.description = description;
        item.unitPrice = unitPrice;

        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingItem)) return false;

        ShoppingItem item = (ShoppingItem) o;

        if (getSize() != item.getSize()) return false;
        if (!getTitle().equals(item.getTitle())) return false;
        if (!unitPrice.equals(item.unitPrice)) return false;
        return getDescription().equals(item.getDescription());

    }

    @Override
    public int hashCode() {
        int result = getTitle().hashCode();
        result = 31 * result + unitPrice.hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + (int) (getSize() ^ (getSize() >>> 32));
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
