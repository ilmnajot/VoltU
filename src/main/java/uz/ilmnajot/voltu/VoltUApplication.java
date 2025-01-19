package uz.ilmnajot.voltu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
public class VoltUApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoltUApplication.class, args);

        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[64]; // 512-bit key
        secureRandom.nextBytes(randomBytes);
        String secretKey = Base64.getEncoder().encodeToString(randomBytes);
        System.out.println("Generated Secret Key: " + secretKey);
    }

}
