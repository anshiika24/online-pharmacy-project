//package model;
//
//public class Medicine {
//
//    private int id;
//    private String name;
//    private String description;
//    private double price;
//    private int stock;
//
//    public Medicine() {}
//
//    public Medicine(int id, String name, String description, double price, int stock) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.price = price;
//        this.stock = stock;
//    }
//
//    // Getters & Setters
//    public int getId() { return id; }
//    public void setId(int id) { this.id = id; }
//
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//
//    public String getDescription() { return description; }
//    public void setDescription(String description) { this.description = description; }
//
//    public double getPrice() { return price; }
//    public void setPrice(double price) { this.price = price; }
//
//    public int getStock() { return stock; }
//    public void setStock(int stock) { this.stock = stock; }
//}
//



//package model;
//
//import java.time.LocalDate;
//
//public class Medicine {
//    private int id;
//    private String name;
//    private String description;
//    private double price;
//    private int stock;
//    private LocalDate expiryDate; // NEW
//
//    public Medicine() {}
//
//    public Medicine(int id, String name, String description, double price, int stock, LocalDate expiryDate) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.price = price;
//        this.stock = stock;
//        this.expiryDate = expiryDate;
//    }
//
//    // existing getters/setters...
//
//    public int getId() { return id; }
//    public void setId(int id) { this.id = id; }
//
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//
//    public String getDescription() { return description; }
//    public void setDescription(String description) { this.description = description; }
//
//    public double getPrice() { return price; }
//    public void setPrice(double price) { this.price = price; }
//
//    public int getStock() { return stock; }
//    public void setStock(int stock) { this.stock = stock; }
//
//    public LocalDate getExpiryDate() { return expiryDate; }
//    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
//}



package model;

import java.time.LocalDate;

public class Medicine {

    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String category;
    private LocalDate expiryDate;
    private String imagePath; // relative path stored in DB (medicine_images/xxx.png)

    public Medicine(int id, String name, String description, double price, int stock, String category, LocalDate expiryDate, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.expiryDate = expiryDate;
        this.imagePath = imagePath;
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public String getCategory() { return category; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public String getImagePath() { return imagePath; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
    public void setCategory(String category) { this.category = category; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}
