import java.util.Scanner;

public class CaesarCipher {

    // Метод для шифрования сообщения
    public static String encrypt(String message, int shift) {
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ch = (char) ((ch - base + shift) % 26 + base);
            }
            encryptedMessage.append(ch);
        }

        return encryptedMessage.toString();
    }

    // Метод для дешифрования сообщения
    public static String decrypt(String encryptedMessage, int shift) {
        return encrypt(encryptedMessage, 26 - shift);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите сообщение для шифрования:");
        String message = scanner.nextLine();

        System.out.println("Введите ключ (сдвиг):");
        int shift = scanner.nextInt();

        String encryptedMessage = encrypt(message, shift);
        System.out.println("Зашифрованное сообщение: " + encryptedMessage);

        String decryptedMessage = decrypt(encryptedMessage, shift);
        System.out.println("Расшифрованное сообщение: " + decryptedMessage);

        scanner.close();
    }
}
