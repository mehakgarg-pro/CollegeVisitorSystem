
import javax.swing.*;
import ui.VisitorForm;

public class Main {

    public static void main(String[] args) {
        // Run GUI in Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Set look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                System.out.println(" Starting College Visitor Entry System...");
                System.out.println("Date: " + java.time.LocalDate.now());
                System.out.println("Time: " + java.time.LocalTime.now());
                System.out.println("=".repeat(50));

                // Create and show form
                new VisitorForm();

            } catch (Exception e) {
                System.out.println(" Error starting application: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
