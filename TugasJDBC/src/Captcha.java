import java.security.SecureRandom;

public class Captcha {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CAPTCHA_LENGTH = 6;

    public static String generateCaptcha() {
        SecureRandom random = new SecureRandom();
        StringBuilder captchaCode = new StringBuilder();

        //membuat kode captcha secara random
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            captchaCode.append(CHARACTERS.charAt(randomIndex));
        }

        return captchaCode.toString();
    }
}
