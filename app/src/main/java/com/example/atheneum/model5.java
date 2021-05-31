package com.example.atheneum;


public class model5 {
    String name,author,qty,image,duedate;
    model5(){

    }
    public model5(String name, String qty, String author,String image,String duedate) {
        this.name = name;
        this.qty = qty;
        this.author=author;
        this.duedate=duedate;
        this.image = image;
        //this.timestamp=timestamp;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name= name;
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



    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

//    public String getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(String timestamp) {
//        this.timestamp = timestamp;
//    }

}
