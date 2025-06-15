import javax.swing.*;


public class MainApp extends JFrame {
    public MainApp() {
        setTitle("NeverForget");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        JTabbedPane tabs = new JTabbedPane();

        // Add each custom panel to a tab
        tabs.addTab("🛒 Shopping List", new ShoppingListPanel());
        tabs.addTab("🎂 Birthday Reminder", new BirthdayPanel());
        tabs.addTab("🎬 Watchlist", new WatchlistPanel());
        tabs.addTab("💡 Idea Logger", new IdeaLoggerPanel());
        
        

        add(tabs);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp().setVisible(true));
    }
}
