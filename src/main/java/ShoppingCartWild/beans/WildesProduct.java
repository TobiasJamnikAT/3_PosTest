package ShoppingCartWild.beans;

import java.util.Objects;

public class WildesProduct {
    private String name;
    private String price;
    private int quantity;


    public WildesProduct(String raw) {
        String[] split = raw.split(";");
        this.name = split[0];
        this.price = split[1];
        this.quantity = 1;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "name= " + name + " price= " + price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public Double getPriceDouble() {
        return Double.parseDouble(getPrice().split("€")[1].replace(',', '.'));
    }

    public void setPrice(String price) {
        this.price = price;
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
        WildesProduct that = (WildesProduct) o;
        return Objects.equals(name, that.name) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
