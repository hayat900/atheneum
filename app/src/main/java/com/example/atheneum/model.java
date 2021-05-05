package com.example.atheneum;

public class model {
    String name,author,qty,image,timestamp;
    model(){

    }
    public model(String name, String author, String qty, String image,String timestamp) {
        this.name = name;
        this.author = author;
        this.qty = qty;
        this.image = image;
        this.timestamp=timestamp;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
