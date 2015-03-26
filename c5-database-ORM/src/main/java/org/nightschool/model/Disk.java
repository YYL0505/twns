package org.nightschool.model;

public class Disk {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String name;
    private String img_url;
    private String description;
    private int count;
    private double price;

    public Disk(String name, String imgUrl, String desc, int number, double price) {
        this.name = name;
        this.img_url = imgUrl;
        this.description = desc;
        this.count = number;
        this.price = price;
    }

    public Disk() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
