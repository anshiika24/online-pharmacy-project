package service;

import dao.MedicineDAO;
import dao.OrderDAO;
import model.Medicine;
import model.Order;

public class OrderService {

    private OrderDAO orderDAO = new OrderDAO();
    private MedicineDAO medicineDAO = new MedicineDAO();

    public boolean placeOrder(int userId, int medicineId, int quantity) {

        // Step 1: Get all medicines and find the required one
        for (Medicine m : medicineDAO.getAllMedicines()) {

            if (m.getId() == medicineId) {

                if (m.getStock() < quantity) {
                    System.out.println("❌ Not enough stock!");
                    return false;
                }

                double totalPrice = m.getPrice() * quantity;

                Order order = new Order(0, userId, medicineId, quantity, totalPrice);

                boolean success = orderDAO.addOrder(order);

                if (success) {
                    // Reduce stock after order
                    medicineDAO.updateStock(medicineId, m.getStock() - quantity);
                    System.out.println("✔ Order placed successfully!");
                }

                return success;
            }
        }

        System.out.println("❌ Medicine not found!");
        return false;
    }
}

