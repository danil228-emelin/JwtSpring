package itmo.project.service;

import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
@Component
public class OtpGenerator {
    public  String generateCode() {
        String code;
        try {
            SecureRandom random = SecureRandom.getInstanceStrong();
            int c = random.nextInt(9000) + 1000;
            code = String.valueOf(c);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Problem when generating the random code.");}
        return code;
    }


}
