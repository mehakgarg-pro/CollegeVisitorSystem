package ui;

import dao.VisitorDAO;
import model.Visitor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VisitorForm extends JFrame {

    private JTextField txtName, txtPhone, txtPurpose, txtMeet;
    private DefaultTableModel tableModel;
    private VisitorDAO dao = new VisitorDAO();
    private JLabel statusLabel;

    public VisitorForm() {
        setTitle("College Visitor Entry System");
        setSize(1200, 800); // Increased size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        loadVisitors();
    }

    private void initComponents() {
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(240, 245, 250));

        // ================= HEADER PANEL =================
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(30, 60, 120));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel lblTitle = new JLabel("COLLEGE VISITOR ENTRY SYSTEM");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel lblSubtitle = new JLabel("Visitor Management Portal");
        lblSubtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        lblSubtitle.setForeground(new Color(200, 220, 255));
        lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
        
        headerPanel.add(lblTitle, BorderLayout.CENTER);
        headerPanel.add(lblSubtitle, BorderLayout.SOUTH);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // ================= CENTER PANEL (Table) =================
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
            "VISITORS LIST",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Arial", Font.BOLD, 14),
            new Color(30, 60, 120)
        ));
        centerPanel.setBackground(Color.WHITE);

        // Table setup
        tableModel = new DefaultTableModel(
            new String[]{"ID", "Name", "Phone", "Purpose", "Meet With", "Date", "Time"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(220, 230, 245));
        table.getTableHeader().setForeground(new Color(30, 60, 120));
        table.setGridColor(new Color(200, 210, 230));
        table.setSelectionBackground(new Color(180, 200, 240));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Table controls panel
        JPanel tableControls = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tableControls.setBackground(Color.WHITE);
        
        JButton btnRefresh = new JButton("Refresh List");
        btnRefresh.setFont(new Font("Arial", Font.PLAIN, 12));
        btnRefresh.addActionListener(e -> loadVisitors());
        tableControls.add(btnRefresh);
        
        centerPanel.add(tableControls, BorderLayout.SOUTH);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // ================= LEFT PANEL (Input Form) - SIMPLIFIED =================
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
            "ADD NEW VISITOR",
            javax.swing.border.TitledBorder.CENTER,
            javax.swing.border.TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 18),
            new Color(30, 60, 120)
        ));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setPreferredSize(new Dimension(400, 0));

        // Create form panel with simple GridLayout
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Visitor Name
        JPanel namePanel = createInputPanel("Visitor Name:", txtName = new JTextField(25));
        formPanel.add(namePanel);
        formPanel.add(Box.createVerticalStrut(15));

        // Phone Number
        JPanel phonePanel = createInputPanel("Phone Number:", txtPhone = new JTextField(25));
        formPanel.add(phonePanel);
        formPanel.add(Box.createVerticalStrut(15));

        // Visit Purpose
        JPanel purposePanel = createInputPanel("Visit Purpose:", txtPurpose = new JTextField(25));
        formPanel.add(purposePanel);
        formPanel.add(Box.createVerticalStrut(15));

        // Person to Meet
        JPanel meetPanel = createInputPanel("Person to Meet:", txtMeet = new JTextField(25));
        formPanel.add(meetPanel);
        formPanel.add(Box.createVerticalStrut(25));

        // Add form panel to input panel
        inputPanel.add(formPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 30, 40));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ADD VISITOR Button
        JButton btnAdd = new JButton("ADD VISITOR");
        btnAdd.setFont(new Font("Arial", Font.BOLD, 16));
        btnAdd.setBackground(new Color(40, 140, 60));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFocusPainted(false);
        btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdd.setMaximumSize(new Dimension(300, 50));
        btnAdd.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(30, 120, 50), 2),
            BorderFactory.createEmptyBorder(12, 40, 12, 40)
        ));
        btnAdd.addActionListener(e -> addVisitor());

        // Hover effect
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdd.setBackground(new Color(50, 160, 70));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAdd.setBackground(new Color(40, 140, 60));
            }
        });

        buttonPanel.add(btnAdd);
        buttonPanel.add(Box.createVerticalStrut(15));

        // CLEAR Button
        JButton btnClear = new JButton("CLEAR FORM");
        btnClear.setFont(new Font("Arial", Font.PLAIN, 14));
        btnClear.setBackground(new Color(220, 220, 220));
        btnClear.setForeground(new Color(80, 80, 80));
        btnClear.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnClear.setMaximumSize(new Dimension(200, 40));
        btnClear.addActionListener(e -> clearFields());
        buttonPanel.add(btnClear);

        inputPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(inputPanel, BorderLayout.WEST);

        // ================= STATUS BAR =================
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBackground(new Color(245, 245, 245));
        statusPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(200, 210, 220)),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        
        statusLabel = new JLabel("Status: Ready | Total Visitors: 0");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        statusLabel.setForeground(new Color(80, 80, 80));
        
        JLabel timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        timeLabel.setForeground(new Color(100, 100, 100));
        
        // Update time every second
        Timer timer = new Timer(1000, e -> {
            timeLabel.setText("Time: " + java.time.LocalTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("hh:mm:ss a")));
        });
        timer.start();
        
        statusPanel.add(statusLabel, BorderLayout.WEST);
        statusPanel.add(timeLabel, BorderLayout.EAST);
        
        mainPanel.add(statusPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private JPanel createInputPanel(String labelText, JTextField textField) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panel.setBackground(Color.WHITE);
        
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setPreferredSize(new Dimension(130, 30));
        label.setForeground(new Color(50, 50, 50));
        
        // Make text field BIG and visible
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(250, 40)); // BIGGER SIZE
        textField.setMinimumSize(new Dimension(250, 40));
        textField.setMaximumSize(new Dimension(250, 40));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 140, 180), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        textField.setBackground(new Color(255, 255, 255));
        
        panel.add(label);
        panel.add(textField);
        
        return panel;
    }

    private void addVisitor() {
        String name = txtName.getText().trim();
        String phone = txtPhone.getText().trim();
        String purpose = txtPurpose.getText().trim();
        String meet = txtMeet.getText().trim();

        // Validation
        if (name.isEmpty() || phone.isEmpty() || purpose.isEmpty() || meet.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please fill all fields!",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE);
            txtName.requestFocus();
            return;
        }

        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid 10-digit phone number!",
                "Invalid Phone",
                JOptionPane.WARNING_MESSAGE);
            txtPhone.selectAll();
            txtPhone.requestFocus();
            return;
        }

        Visitor visitor = new Visitor(name, phone, purpose, meet);
        
        if (dao.addVisitor(visitor)) {
            JOptionPane.showMessageDialog(this,
                "Visitor added successfully!\n\n" +
                "Name: " + name + "\n" +
                "Phone: " + phone + "\n" +
                "Purpose: " + purpose + "\n" +
                "Meeting: " + meet + "\n" +
                "Time: " + java.time.LocalTime.now().format(
                    java.time.format.DateTimeFormatter.ofPattern("hh:mm a")),
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            clearFields();
            loadVisitors();
        } else {
            JOptionPane.showMessageDialog(this,
                "Failed to add visitor!\nPlease check database connection.",
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadVisitors() {
        tableModel.setRowCount(0);
        List<Visitor> visitors = dao.getAllVisitors();
        
        if (visitors.isEmpty()) {
            System.out.println("No visitors found in database.");
        } else {
            System.out.println("Loaded " + visitors.size() + " visitors.");
        }
        
        for (Visitor v : visitors) {
            tableModel.addRow(new Object[]{
                v.getId(),
                v.getName(),
                v.getPhone(),
                v.getPurpose(),
                v.getPersonToMeet(),
                v.getDate(),
                v.getTime()
            });
        }
        
        // Update status bar
        if (statusLabel != null) {
            statusLabel.setText("Status: Ready | Total Visitors: " + visitors.size() + 
                " | Last Updated: " + java.time.LocalTime.now().format(
                    java.time.format.DateTimeFormatter.ofPattern("hh:mm:ss a")));
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtPhone.setText("");
        txtPurpose.setText("");
        txtMeet.setText("");
        txtName.requestFocus();
    }

    // For testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VisitorForm form = new VisitorForm();
            form.setVisible(true);
        });
    }
}