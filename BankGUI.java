import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;
public class BankGUI extends JFrame {

private void saveAccounts() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accounts.dat"))) {
        oos.writeObject(accounts);
        System.out.println("Accounts saved successfully.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private void loadAccounts() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accounts.dat"))) {
        accounts = (ArrayList<BankAccount>) ois.readObject();
        System.out.println("Accounts loaded successfully.");
    } catch (IOException | ClassNotFoundException e) {
        System.out.println("No previous data found or failed to load.");
    }
}

    private void setOutput(String text) {
    output.setText(text);
    StyledDocument doc = output.getStyledDocument();
    SimpleAttributeSet center = new SimpleAttributeSet();
    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
    doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }
    

    private static void setUIFont(Font font) {
    java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
    while (keys.hasMoreElements()) {
        Object key = keys.nextElement();
        Object value = UIManager.get(key);
        if (value instanceof Font) {
            UIManager.put(key, font);
            }
        }
    }
    
    private ArrayList<BankAccount> accounts = new ArrayList<>();
    private JTextPane output = new JTextPane();

    public BankGUI() {
        Color lightBlue = new Color(173, 216, 230); // Light blue color
        getContentPane().setBackground(lightBlue);
        
        setTitle("Bank Management System");
        loadAccounts();
        ImageIcon icon = new ImageIcon(getClass().getResource("icon.png"));
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen window
        setLocationRelativeTo(null);

        output.setEditable(false);
        output.setFont(new Font("Monospaced", Font.PLAIN, 30)); // Larger font
        StyledDocument doc = output.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        JScrollPane scrollPane = new JScrollPane(output);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 4, 20, 20)); // 2 rows, 4 columns

        String[] btnLabels = {
            "Create Account", "Deposit", "Withdraw",
            "Transfer", "Search", "Show All Accounts", "Total Transactions", "Delete Account"
        };

        JButton[] buttons = new JButton[btnLabels.length];

        for (int i = 0; i < btnLabels.length; i++) {
            buttons[i] = new JButton(btnLabels[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 30));
            buttons[i].setPreferredSize(new Dimension(120, 120)); // Square shape

             // Create a wrapper panel with padding
            JPanel wrapper = new JPanel(new BorderLayout());
            wrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // top, left, bottom, right
            wrapper.add(buttons[i], BorderLayout.CENTER);

            buttonPanel.add(wrapper);
        }

        buttons[0].addActionListener(e -> createAccount());
        buttons[1].addActionListener(e -> deposit());
        buttons[2].addActionListener(e -> withdraw());
        buttons[3].addActionListener(e -> transfer());
        buttons[4].addActionListener(e -> search());
        buttons[5].addActionListener(e -> showAll());
        buttons[6].addActionListener(e -> output.setText("Total Transactions: " + BankAccount.no_of_trans));   JPanel topPanel = new JPanel(new BorderLayout());
        buttons[7].addActionListener(e -> deleteAccount());
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create and configure title label
      // Create and configure title label
        JLabel titleLabel = new JLabel("Admin Panel");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setForeground(new Color(0, 51, 102));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center in BoxLayout

        // Create a vertical box layout panel for title and buttons
        JPanel verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        verticalPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        verticalPanel.add(titleLabel);
        verticalPanel.add(Box.createVerticalStrut(30)); // space between title and buttons
        verticalPanel.add(buttonPanel);

        topPanel.add(verticalPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createEmptyBorder(80,80, 160, 80)); // adds padding (top, left, bottom, right)
        outputPanel.add(scrollPane, BorderLayout.CENTER);
        outputPanel.setBackground(lightBlue); // match background

        add(outputPanel, BorderLayout.CENTER);

        topPanel.setBackground(lightBlue);
        verticalPanel.setBackground(lightBlue);
        buttonPanel.setBackground(lightBlue);
        output.setBackground(Color.WHITE);

        setVisible(true);
    }

    // All method definitions below remain unchanged...

    private void createAccount() {
    // Create text fields
    JTextField name = new JTextField();
    name.setFont(new Font("Arial", Font.PLAIN, 22));
    JTextField address = new JTextField();
    address.setFont(new Font("Arial", Font.PLAIN, 22));
    JTextField type = new JTextField();
    type.setFont(new Font("Arial", Font.PLAIN, 22));
    JTextField balance = new JTextField();
    balance.setFont(new Font("Arial", Font.PLAIN, 22));

    // Create a panel with vertical layout and spacing
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(8, 1, 0, 10)); // 8 rows, 10px vertical gap
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // outer padding

    // Add label and field pairs
    panel.add(new JLabel("Name:"));
    panel.add(name);
    panel.add(new JLabel("Address:"));
    panel.add(address);
    panel.add(new JLabel("Type:"));
    panel.add(type);
    panel.add(new JLabel("Initial Balance:"));
    panel.add(balance);

    // Create JOptionPane and dialog
    JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
    JDialog dialog = optionPane.createDialog(this, "Create Account");

    // Set dialog size to half the window
    int width = getWidth() / 2;
    int height = getHeight() / 2;
    dialog.setSize(width, height);
    dialog.setLocationRelativeTo(this);
    dialog.setVisible(true);

    // Process input if OK clicked
    Object selectedValue = optionPane.getValue();
    if (selectedValue != null && selectedValue.equals(JOptionPane.OK_OPTION)) {
        try {
            BankAccount acc = new BankAccount(
                name.getText(),
                address.getText(),
                type.getText(),
                Double.parseDouble(balance.getText())
            );
            accounts.add(acc);
            saveAccounts();
            setOutput("Account Created!\n\n" + acc.toString());
        } catch (Exception ex) {
            setOutput("Error creating account.");
        }
    }
}



    private BankAccount selectAccount(String prompt) {
        String uid = JOptionPane.showInputDialog(this, prompt);
        for (BankAccount acc : accounts) {
            if (acc.uid.equalsIgnoreCase(uid)) return acc;
        }
        output.setText("Account not found.");
        return null;
    }


    private void deposit() {
        BankAccount acc = selectAccount("Enter Account ID:");
        if (acc != null) {
            String amt = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
            acc.balance += Double.parseDouble(amt);
            BankAccount.no_of_trans++;
            output.setText("Deposited successfully.\nNew Balance: tk" + acc.balance);
        }
        saveAccounts();
    }

    private void withdraw() {
        BankAccount acc = selectAccount("Enter Account ID:");
        if (acc != null) {
            String amt = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
            double amount = Double.parseDouble(amt);
            if (amount <= acc.balance) {
                acc.balance -= amount;
                BankAccount.no_of_trans++;
                output.setText("Withdrawn successfully.\nNew Balance: tk" + acc.balance);
            } else {
                output.setText("Insufficient balance.");
            }
            saveAccounts();
        }
    }

    private void transfer() {
        BankAccount sender = selectAccount("Enter Sender Account ID:");
        if (sender != null) {
            BankAccount receiver = selectAccount("Enter Receiver Account ID:");
            if (receiver != null) {
                String amt = JOptionPane.showInputDialog(this, "Enter amount to transfer:");
                double amount = Double.parseDouble(amt);
                if (amount <= sender.balance) {
                    sender.balance -= amount;
                    receiver.balance += amount;
                    BankAccount.no_of_trans++;
                    output.setText("Transferred successfully.\nSender New Balance: tk" + sender.balance);
                } else {
                    output.setText("Insufficient balance.");
                }
            }
        }
        saveAccounts();
    }

    private void search() {
        String query = JOptionPane.showInputDialog(this, "Enter name or ID:");
        for (BankAccount acc : accounts) {
            if (acc.name.equalsIgnoreCase(query) || acc.uid.equalsIgnoreCase(query)) {
                output.setText("Account Found!\n\n" + acc.toString());
                return;
            }
        }
        output.setText("Account not found.");
    }

    private void showAll() {
        StringBuilder sb = new StringBuilder("All Accounts:\n\n");
        for (BankAccount acc : accounts) {
            sb.append(acc.toString()).append("\n----------------------\n");
        }
        output.setText(sb.toString());
    }
    private void deleteAccount() {
    BankAccount acc = selectAccount("Enter Account ID to delete:");
    if (acc != null) {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this account?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            accounts.remove(acc);
            output.setText("Account deleted successfully.");
        } else {
            output.setText("Account deletion cancelled.");
        }
    }
    saveAccounts();
}



    public static void main(String[] args) {
    setUIFont(new Font("Arial", Font.PLAIN, 22)); // Set font size to 22 for all components
    new BankGUI();  // Now create the GUI
    }
}
