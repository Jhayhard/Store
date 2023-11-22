
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public class Iphonestoreee {
    private static String customerName;
    private static final int[] inventory = {10, 15, 8, 5, 20}; // Initial inventory quantities

    public static void main(String[] args) {
        boolean continueShopping = true;

        while (continueShopping) {
            do {
                enterChangeCustomerName();

                int choice;
                do {
                    String[] options = {"View available iPhone items", "Exit"};
                    choice = JOptionPane.showOptionDialog(
                            null,
                            "Hello, " + customerName + "! How can we assist you today?",
                            "iPhone Store",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]
                    );

                    switch (choice) {
                        case 0:
                            viewAvailableItems();
                            break;
                        case 1:
                            JOptionPane.showMessageDialog(null, "Thank you for visiting the iPhone Store. Goodbye!");
                            System.exit(0);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                    }
                } while (choice != 1);

            } while (makeAnotherPurchase());

            // Ask if the user wants to make a purchase for another customer
            continueShopping = makeAnotherCustomer();
        }
    }

    private static void enterChangeCustomerName() {
        customerName = JOptionPane.showInputDialog("Welcome to the iPhone Store!\nEnter your name:");

        if (customerName == null || customerName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid name. Exiting.");
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(null, "Name set to: " + customerName);
        }
    }

    private static void viewAvailableItems() {
        int choice;
        do {
            String[] items = {"iPhone 11 Pro", "iPhone 12", "iPhone 12 Pro Max", "iPhone 13 Pro Max", "iPhone 14"};
            double[] prices = {31000.00, 35000.00, 48000.00, 56000.00, 67000.00};

            StringBuilder message = new StringBuilder("Available iPhone items:\n");
            for (int i = 0; i < items.length; i++) {
                message.append(i + 1).append(". ").append(items[i]).append(" - ₱").append(prices[i])
                        .append(" (Inventory: ").append(inventory[i]).append(")\n");
            }

            String selectedItem = (String) JOptionPane.showInputDialog(
                    null,
                    message + "\nSelect an item:",
                    "iPhone Store",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    items,
                    items[0]
            );

            if (selectedItem == null) {
                return; // User canceled the selection
            }

            String quantityString = JOptionPane.showInputDialog("Enter the quantity:");
            if (quantityString == null || quantityString.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Invalid quantity. Exiting.");
                return;
            }

            int quantity = Integer.parseInt(quantityString);

            if (quantity <= 0) {
                JOptionPane.showMessageDialog(null, "Invalid quantity. Exiting.");
                return;
            }

            int itemIndex = getIndex(selectedItem, items);

            if (itemIndex == -1) {
                JOptionPane.showMessageDialog(null, "Invalid item. Exiting.");
                return;
            }

            if (quantity > inventory[itemIndex]) {
                JOptionPane.showMessageDialog(null, "Insufficient inventory. Please choose a smaller quantity.");
                return;
            }

            double itemCost = prices[itemIndex] * quantity;

            // Calculate a discount of 10% if the total cost is more than ₱50000
            double discount = itemCost > 50000 ? 0.10 : 0.0;
            double discountedTotal = itemCost - (itemCost * discount);

            // No account balance deduction

            String purchaseMessage = "You've purchased " + quantity + " " + selectedItem +
                    "\nItem Cost: ₱" + itemCost +
                    "\nDiscounted Total Cost: ₱" + discountedTotal;

            JOptionPane.showMessageDialog(null, purchaseMessage);

            // Ask for payment and calculate change
            double payment = getValidPayment("Enter the payment amount:");
            double change = payment - discountedTotal;

            String paymentMessage = "Payment received: ₱" + payment +
                    "\nChange: ₱" + change;

            JOptionPane.showMessageDialog(null, paymentMessage);

            // Simulate a conversation with the seller
            JOptionPane.showMessageDialog(null, "Seller: Hi, " + customerName + "! Is there anything else you'd like to add?");

        } while (makeAnotherPurchase());
    }

    private static boolean makeAnotherPurchase() {
        String[] options = {"Make another purchase", "Back to main menu", "Exit"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "What would you like to do?",
                "iPhone Store",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (choice) {
            case 0:
                return true;  // Make another purchase
            case 1:
                return false; // Back to main menu
            case 2:
                JOptionPane.showMessageDialog(null, "Thank you for visiting the iPhone Store. Goodbye!");
                System.exit(0);
            default:
                return false; // Default to back to main menu
        }
    }

    private static boolean makeAnotherCustomer() {
        String[] options = {"Make a purchase for another customer", "Exit"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "What would you like to do?",
                "iPhone Store",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (choice) {
            case 0:
                // Reset customerName for the new customer
                enterChangeCustomerName();
                return true;  // Make a purchase for another customer
            case 1:
                return false; // Exit
            default:
                return false; // Default to exit
        }
    }

    private static int getIndex(String selectedItem, String[] items) {
        for (int i = 0; i < items.length; i++) {
            if (selectedItem.equals(items[i])) {
                return i;
            }
        }
        return -1; // Not found
    }

    private static double getValidPayment(String message) {
        double payment;
        while (true) {
            try {
                String paymentString = JOptionPane.showInputDialog(message);
                if (paymentString == null || paymentString.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Invalid payment. Exiting.");
                    System.exit(0);
                }

                payment = Double.parseDouble(paymentString);
                if (payment < 0) {
                    JOptionPane.showMessageDialog(null, "Payment cannot be negative. Exiting.");
                    System.exit(0);
                }

                break; // Break the loop if valid input
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid payment amount.");
            }
        }
        return payment;
    }
}





