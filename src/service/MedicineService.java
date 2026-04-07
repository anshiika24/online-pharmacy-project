//package service;
//
//import dao.MedicineDAO;
//import model.Medicine;
//
//import java.util.List;
//
//public class MedicineService {
//
//    private MedicineDAO medicineDAO = new MedicineDAO();
//
//    public boolean addMedicine(String name, String description, double price, int stock) {
//        Medicine m = new Medicine(0, name, description, price, stock);
//        return medicineDAO.addMedicine(m);
//    }
//
//    public List<Medicine> getAllMedicines() {
//        return medicineDAO.getAllMedicines();
//    }
//
//    public boolean updateMedicineStock(int id, int newStock) {
//        return medicineDAO.updateStock(id, newStock);
//    }
//}


//package service;
//
//import dao.MedicineDAO;
//import model.Medicine;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public class MedicineService {
//
//    private MedicineDAO medicineDAO = new MedicineDAO();
//
//    // ADD Medicine (with expiry date)
//    public boolean addMedicine(String name, String description, double price, int stock, LocalDate expiryDate) {
//        Medicine m = new Medicine(0, name, description, price, stock, expiryDate);
//        return medicineDAO.addMedicine(m);
//    }
//
//    // GET all
//    public List<Medicine> getAllMedicines() {
//        return medicineDAO.getAllMedicines();
//    }
//
//    // UPDATE Medicine fully (all fields)
//    public boolean updateMedicine(int id, String name, String description, double price, int stock, LocalDate expiryDate) {
//        Medicine m = new Medicine(id, name, description, price, stock, expiryDate);
//        return medicineDAO.updateMedicine(m);
//    }
//
//    // UPDATE stock only
//    public boolean updateMedicineStock(int id, int newStock) {
//        return medicineDAO.updateStock(id, newStock);
//    }
//
//    // DELETE
//    public boolean deleteMedicine(int id) {
//        return medicineDAO.deleteMedicine(id);
//    }
//
//    // Medicines expiring within X days
//    public List<Medicine> getExpiringSoon(int days) {
//        return medicineDAO.getExpiringWithinDays(days);
//    }
//
//    // Low stock medicines
//    public List<Medicine> getLowStockMedicines(int threshold) {
//        return medicineDAO.getLowStock(threshold);
//    }
//
//    // Get by ID
//    public Medicine getMedicineById(int id) {
//        return medicineDAO.getMedicineById(id);
//    }
//}



package service;

import dao.MedicineDAO;
import model.Medicine;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

public class MedicineService {

    private MedicineDAO medicineDAO = new MedicineDAO();

    // ADD Medicine (with category + expiry + image)
    public boolean addMedicine(String name, String description, double price, int stock,
                               String category, LocalDate expiryDate, String imagePath) {

        Medicine m = new Medicine(
                0,
                name,
                description,
                price,
                stock,
                category,
                expiryDate,
                imagePath
        );

        return medicineDAO.addMedicine(m);
    }

    // GET all
    public List<Medicine> getAllMedicines() {
        return medicineDAO.getAllMedicines();
    }

    // UPDATE Medicine fully
    public boolean updateMedicine(int id, String name, String description, double price,
                                  int stock, String category, LocalDate expiryDate, String imagePath) {

        Medicine m = new Medicine(
                id,
                name,
                description,
                price,
                stock,
                category,
                expiryDate,
                imagePath
        );

        return medicineDAO.updateMedicine(m);
    }

    // UPDATE stock only
    public boolean updateMedicineStock(int id, int newStock) {
        return medicineDAO.updateStock(id, newStock);
    }

    // DELETE
    public boolean deleteMedicine(int id) throws SQLIntegrityConstraintViolationException {
        return medicineDAO.deleteMedicine(id);
    }


    // Medicines expiring within X days
    public List<Medicine> getExpiringSoon(int days) {
        return medicineDAO.getExpiringWithinDays(days);
    }

    // Low stock medicines
    public List<Medicine> getLowStockMedicines(int threshold) {
        return medicineDAO.getLowStock(threshold);
    }

    // Get by ID
    public Medicine getMedicineById(int id) {
        return medicineDAO.getMedicineById(id);
    }

    // SEARCH (name, category)
    public List<Medicine> searchMedicines(String keyword) {
        return medicineDAO.searchMedicines(keyword);
    }
}
