package model;

public class Order {

    private int id;
    private int userId;
    private int medicineId;
    private int quantity;
    private double totalPrice;

    public Order() {}

    public Order(int id, int userId, int medicineId, int quantity, double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getMedicineId() { return medicineId; }
    public void setMedicineId(int medicineId) { this.medicineId = medicineId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}

