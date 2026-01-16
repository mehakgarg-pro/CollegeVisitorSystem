import javax.swing.*;
import ui.SplashScreen;
import ui.VisitorForm;

public class Main {
    public static void main(String[] args) {
        // Show splash screen
        SplashScreen splash = new SplashScreen();
        splash.showSplash(2000); // Show for 2 seconds
        
        // Then show main application window
        SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("Starting College Visitor Entry System...");
                System.out.println("Date: " + java.time.LocalDate.now());
                System.out.println("Time: " + java.time.LocalTime.now());
                System.out.println("=".repeat(50));
                
                VisitorForm form = new VisitorForm();
                form.setVisible(true);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}