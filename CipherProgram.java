import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CipherProgram extends JFrame {
    
    // Интерфейс для шифрования и дешифрования
    interface Cipher {
        String encrypt(String message, int key);
        String decrypt(String message, int key);
    }

    // Реализация шифра Цезаря
    static class CaesarCipher implements Cipher {
        
        @Override
        public String encrypt(String message, int shift) {
            StringBuilder encryptedMessage = new StringBuilder();

            for (int i = 0; i < message.length(); i++) {
                char ch = message.charAt(i);
                if (Character.isLetter(ch)) {
                    char base = Character.isLowerCase(ch) ? 'a' : 'A';
                    ch = (char) ((ch - base + shift + 26) % 26 + base);
                }
                encryptedMessage.append(ch);
            }

            return encryptedMessage.toString();
        }

        @Override
        public String decrypt(String encryptedMessage, int shift) {
            return encrypt(encryptedMessage, -shift);
        }
    }

    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JTextField keyField;
    private JButton encryptButton;
    private JButton decryptButton;
    private Cipher cipher;

    public CipherProgram() {
        cipher = new CaesarCipher();

        setTitle("Caesar Cipher Program");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Панель ввода
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        inputPanel.add(new JLabel("Input Text:"));
        inputTextArea = new JTextArea(3, 20);
        inputPanel.add(new JScrollPane(inputTextArea));

        inputPanel.add(new JLabel("Key (Shift):"));
        keyField = new JTextField();
        inputPanel.add(keyField);

        container.add(inputPanel, BorderLayout.NORTH);

        // Панель вывода
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new GridLayout(1, 1));

        outputPanel.add(new JLabel("Output Text:"));
        outputTextArea = new JTextArea(3, 20);
        outputTextArea.setEditable(false);
        outputPanel.add(new JScrollPane(outputTextArea));

        container.add(outputPanel, BorderLayout.CENTER);

        // Панель кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String inputText = inputTextArea.getText();
                    int key = Integer.parseInt(keyField.getText());
                    String encryptedText = cipher.encrypt(inputText, key);
                    outputTextArea.setText(encryptedText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CipherProgram.this, "Invalid key. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(encryptButton);

        decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String inputText = inputTextArea.getText();
                    int key = Integer.parseInt(keyField.getText());
                    String decryptedText = cipher.decrypt(inputText, key);
                    outputTextArea.setText(decryptedText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CipherProgram.this, "Invalid key. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(decryptButton);

        container.add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CipherProgram().setVisible(true);
            }
        });
    }
}