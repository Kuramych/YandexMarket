package model;

public class ProductModel {

    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public ProductModel withName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ProductModel withPrice(double price) {
        this.price = price;
        return this;
    }

}
