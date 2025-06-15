import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.util.ArrayList;

class WatchlistPanel extends JPanel {

    public WatchlistPanel() {
        setLayout(new BorderLayout());

        DefaultListModel<String> linkModel = new DefaultListModel<>();
        JList<String> linkList = new JList<>(linkModel);
        linkList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        linkList.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JTextField linkInput = new JTextField();

        JButton addBtn = new JButton("Add Link");
        JButton delBtn = new JButton("Delete");

        // ➕ Add Button Logic
        addBtn.addActionListener(e -> {
            String link = linkInput.getText().trim();
            if (!link.isEmpty()) {
                linkModel.addElement(link);
                linkInput.setText("");
            }
        });

        // 🗑️ Delete Button Logic
        delBtn.addActionListener(e -> {
            int selected = linkList.getSelectedIndex();
            if (selected != -1) {
                linkModel.remove(selected);
            }
        });

        // 🌐 Click-to-Open in Browser
        linkList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // double-click
                    String selectedLink = linkList.getSelectedValue();
                    if (selectedLink != null && selectedLink.startsWith("http")) {
                        try {
                            Desktop.getDesktop().browse(new URI(selectedLink));
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Invalid URL or no browser available.");
                        }
                    }
                }
            }
        });

        // Layouts
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(linkInput, BorderLayout.CENTER);
        inputPanel.add(addBtn, BorderLayout.EAST);

        JPanel btnPanel = new JPanel();
        btnPanel.add(delBtn);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(linkList), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
    }
}

