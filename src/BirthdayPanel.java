import java.awt.*;
import java.time.*;
import java.time.format.DateTimeParseException;
import javax.swing.*;

// Class to store name and birthday
class BirthdayEntry {
    String name;
    LocalDate date;

    BirthdayEntry(String inputname, LocalDate inputdate) {
        inputname = name;
        inputdate = date;
    }
    
    @Override
    public String toString() {
        return name + " - " + date.toString();
    }
}

// Birthday Reminder Panel
class BirthdayPanel extends JPanel {

    public BirthdayPanel() {
        setLayout(new BorderLayout());

        DefaultListModel<BirthdayEntry> model = new DefaultListModel<>();
        JList<BirthdayEntry> bdayList = new JList<>(model);

        JTextField nameField = new JTextField();
        JTextField dateField = new JTextField();

        JButton addBtn = new JButton("Add");
        JButton delBtn = new JButton("Delete");

        // Add birthday entry
        addBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String dateStr = dateField.getText().trim();
            if (!name.isEmpty() && !dateStr.isEmpty()) {
                try {
                    LocalDate date = LocalDate.parse(dateStr);
                    model.addElement(new BirthdayEntry(name, date));
                    nameField.setText("");
                    dateField.setText("");
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid date format! Use yyyy-mm-dd.");
                }
            }
        });

        // Delete selected entry
        delBtn.addActionListener(e -> {
            int selected = bdayList.getSelectedIndex();
            if (selected != -1) model.remove(selected);
        });

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Birthday (yyyy-mm-dd):"));
        inputPanel.add(dateField);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(addBtn, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(delBtn);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(bdayList), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Check for today's birthdays
        checkBirthdays(model);

        // Optional: Daily check if app stays open
        new Timer(86400000, ev -> checkBirthdays(model)).start();
    }

    private void checkBirthdays(DefaultListModel<BirthdayEntry> model) {
        LocalDate today = LocalDate.now();
        for (int i = 0; i < model.size(); i++) {
            BirthdayEntry entry = model.get(i);
            if (entry.date.getDayOfMonth() == today.getDayOfMonth() &&
                entry.date.getMonth() == today.getMonth()) {
                JOptionPane.showMessageDialog(this, "🎉 Today is " + entry.name + "'s Birthday!");
            }
        }
    }
}
