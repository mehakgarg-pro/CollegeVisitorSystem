import java.awt.*;
import javax.swing.*;

public class SplashScreen extends JWindow {
    
    public SplashScreen() {
        // Create splash screen content
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(new Color(240, 245, 255));
        
        // Center panel with icon and text
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(240, 245, 255));
        
        JLabel iconLabel = new JLabel("🏫", JLabel.CENTER);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 100));
        iconLabel.setForeground(new Color(0, 100, 200));
        centerPanel.add(iconLabel, BorderLayout.CENTER);
        
        JLabel collegeLabel = new JLabel("College Visitor Entry System", JLabel.CENTER);
        collegeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        collegeLabel.setForeground(new Color(0, 70, 140));
        centerPanel.add(collegeLabel, BorderLayout.SOUTH);
        
        content.add(centerPanel, BorderLayout.CENTER);
        
        // Progress bar at bottom
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setBorder(BorderFactory.createEmptyBorder(20, 50, 30, 50));
        content.add(progressBar, BorderLayout.SOUTH);
        
        setContentPane(content);
        setSize(500, 350);
        setLocationRelativeTo(null);
    }
    
    public void showSplash(int duration) {
        setVisible(true);
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dispose();
    }
}