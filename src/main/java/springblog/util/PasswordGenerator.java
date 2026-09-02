package springblog.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String password = "admin123";
        String encoded = encoder.encode(password);

        System.out.println(encoded);
    }
}
