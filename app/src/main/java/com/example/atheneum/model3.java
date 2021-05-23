package com.example.atheneum;



import java.time.LocalDate;
import java.util.Date;

public class model3 {
    String name,sem,usn,book,author,image,fine,email;
    Date issue,due;
    model3(){

    }

    public model3(String name, String sem, String usn, String book, String author, String image, String fine, Date issue, Date due, String email) {
        this.name = name;
        this.sem = sem;
        this.usn = usn;
        this.book = book;
        this.author = author;
        this.image = image;
        this.fine = fine;
        this.issue = issue;
        this.due = due;
        this.email=email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    public Date getIssue() {
        return issue;
    }

    public void setIssue(Date issue) {
        this.issue = issue;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
