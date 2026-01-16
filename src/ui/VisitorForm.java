package ui;

import dao.VisitorDAO;
import model.Visitor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VisitorForm extends JFrame {

    private JTextField txtName, txtPhone, txtPurpose, txtMeet;
    private DefaultTableModel tableModel;
    private VisitorDAO dao = new VisitorDAO();

    public VisitorForm() {

        setTitle("College Visitor Entry System");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ---------- LEFT FORM ----------
        JPanel form = new JPanel(new GridLayout(9,1,8,8));
        form.setBorder(BorderFactory.createTitledBorder("Visitor Details"));

        txtName = new JTextField();
        txtPhone = new JTextField();
        txtPurpose = new JTextField();
        txtMeet = new JTextField();

        form.add(new JLabel("Visitor Name"));
        form.add(txtName);
        form.add(new JLabel("Phone Number"));
        form.add(txtPhone);
        form.add(new JLabel("Purpose"));
        form.add(txtPurpose);
        form.add(new JLabel("Person to Meet"));
        form.add(txtMeet);

        JButton btnAdd = new JButton("Add Visitor");
        JButton btnClear = new JButton("Clear");

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAdd);
        btnPanel.add(btnClear);

        form.add(btnPanel);
        add(form, BorderLayout.WEST);

        // ---------- TABLE ----------
        tableModel = new DefaultTableModel(
                new String[]{"ID","Name","Phone","Purpose","Meeting","Date","Time"},0
        );
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ---------- ACTIONS ----------
        btnAdd.addActionListener(e -> addVisitor());
        btnClear.addActionListener(e -> clearForm());

        loadVisitors();
        setVisible(true);
    }

    private void addVisitor() {

        if (txtName.getText().isEmpty() ||
            txtPhone.getText().isEmpty() ||
            txtPurpose.getText().isEmpty() ||
            txtMeet.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this,"Fill all fields");
            return;
        }

        Visitor v = new Visitor(
                txtName.getText(),
                txtPhone.getText(),
                txtPurpose.getText(),
                txtMeet.getText()
        );

        if (dao.addVisitor(v)) {
            loadVisitors();
            clearForm();
        }
    }

    private void loadVisitors() {
        tableModel.setRowCount(0);
        List<Visitor> list = dao.getAllVisitors();

        for (Visitor v : list) {
            tableModel.addRow(new Object[]{
                    v.getId(),
                    v.getName(),
                    v.getPhone(),
                    v.getPurpose(),
                    v.getPersonToMeet(),
                    v.getVisitDate(),
                    v.getVisitTime()
            });
        }
    }

    private void clearForm() {
        txtName.setText("");
        txtPhone.setText("");
        txtPurpose.setText("");
        txtMeet.setText("");
    }
}
