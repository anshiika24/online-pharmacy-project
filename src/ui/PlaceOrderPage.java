
package ui;

import dao.MedicineDAO;
import dao.OrderDAO;
import model.Medicine;
import model.Order;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PlaceOrderPage extends JFrame {

    private JComboBox<String> medicineDropdown;
    private JTextField quantityField;
    private JLabel priceLabel;

    private List<Medicine> medicineList;
    private User loggedUser;

    public PlaceOrderPage(User user) {

        this.loggedUser = user;
        setTitle("Place Order");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        JLabel medLabel = new JLabel("Select Medicine:");
        JLabel qtyLabel = new JLabel("Quantity:");
        JLabel totalLabel = new JLabel("Price:");

        medicineDropdown = new JComboBox<>();
        quantityField = new JTextField();
        priceLabel = new JLabel("0.0");

        JButton orderBtn = new JButton("Place Order");

        // Fetch medicines from DB
        MedicineDAO medDAO = new MedicineDAO();
        medicineList = medDAO.getAllMedicines();

        for (Medicine m : medicineList) {
            medicineDropdown.addItem(m.getName());
        }

        // When a medicine is selected → show its price
        medicineDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = medicineDropdown.getSelectedIndex();
                priceLabel.setText(medicineList.get(index).getPrice() + "");
            }
        });

        // Place order button
        orderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int medIndex = medicineDropdown.getSelectedIndex();
                Medicine selectedMed = medicineList.get(medIndex);

                int qty = Integer.parseInt(quantityField.getText());
                double totalPrice = qty * selectedMed.getPrice();

                Order order = new Order(
                        0,                         // id (auto)
                        loggedUser.getId(),        // user
                        selectedMed.getId(),       // medicine
                        qty,
                        totalPrice
                );

                OrderDAO dao = new OrderDAO();
                boolean success = dao.addOrder(order);

                if (success) {
                    JOptionPane.showMessageDialog(null,
                            "Order placed successfully!\nTotal Price: ₹" + totalPrice);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to place order!");
                }
            }
        });

        // Add components
        add(medLabel);
        add(medicineDropdown);
        add(qtyLabel);
        add(quantityField);
        add(totalLabel);
        add(priceLabel);
        add(new JLabel(""));
        add(orderBtn);

        setVisible(true);
    }
}





