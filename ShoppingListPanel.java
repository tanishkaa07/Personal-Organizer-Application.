import java.awt.*;
import javax.swing.*;

class ShoppingListPanel extends JPanel {
    
    

    public ShoppingListPanel() {
        setLayout(new BorderLayout());

        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> itemList = new JList<>(model);
        JTextField inputField = new JTextField();

        JButton addBtn = new JButton("Add");
        JButton delBtn = new JButton("Delete");

        addBtn.addActionListener(e -> {
            String item = inputField.getText();
            if (!item.isEmpty()) {
                model.addElement(item);
                inputField.setText("");
            }
        });

        delBtn.addActionListener(e -> {
            int selected = itemList.getSelectedIndex();
            if (selected != -1) model.remove(selected);
        });

        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        top.add(inputField, BorderLayout.CENTER);
        top.add(addBtn, BorderLayout.EAST);

        JPanel bottom = new JPanel();
        bottom.add(delBtn);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(itemList), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }
}

