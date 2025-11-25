package util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Этот утилитный класс должен реализовывать
 * единственный метод -- хэширование пароля
 */
public class HashUtil {
    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt(12); // cost 12
        return BCrypt.hashpw(password, salt);
    }

    public static boolean verify(String raw, String hashed) {
        return BCrypt.checkpw(raw, hashed);
    }

    public static void main(String[] args) {
        System.out.println( verify("1234", "$2a$12$r/fcZpguHyxdFKNwKgbqxukH9CWoB921aNjiMBFh5lHznDMk5JynG"));
    }
}