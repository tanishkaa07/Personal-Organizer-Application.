import java.awt.*;
import javax.swing.*;

class IdeaLoggerPanel extends JPanel {

    public IdeaLoggerPanel() {
        setLayout(new BorderLayout());

        DefaultListModel<String> ideaModel = new DefaultListModel<>();
        JList<String>  ideaList = new JList<>(ideaModel);
        ideaList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTextArea ideaInput = new JTextArea(3, 20);
        ideaInput.setLineWrap(true);
        ideaInput.setWrapStyleWord(true);
        JScrollPane inputScroll = new JScrollPane(ideaInput);

        JButton addBtn = new JButton("Save Idea");
        JButton delBtn = new JButton("Delete");

        // ➕ Add Idea
        addBtn.addActionListener(e -> {
            String idea = ideaInput.getText().trim();
            if (!idea.isEmpty()) {
                ideaModel.addElement(idea);
                ideaInput.setText("");
            }
        });

        // 🗑️ Delete Idea
        delBtn.addActionListener(e -> {
            int selected = ideaList.getSelectedIndex();
            if (selected != -1) {
                ideaModel.remove(selected);
            }
        });

        // Layout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JLabel("Write your idea:"), BorderLayout.NORTH);
        topPanel.add(inputScroll, BorderLayout.CENTER);
        topPanel.add(addBtn, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(delBtn);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(ideaList), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
