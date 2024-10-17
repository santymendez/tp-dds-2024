package utils.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Clase que se encarga de hashear y validar contraseñas de manera segura.
 */

public class PasswordHasher {
  private static final int ITERATIONS = 10000;
  private static final int KEY_LENGTH = 256;
  private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
  private static final int SALT_LENGTH = 16;

  /**
   * Hashea una contraseña de manera segura.
   *
   * @param password Contraseña a hashear.
   * @return Contraseña hasheada en formato Base64 junto con la sal, también en Base64.
   */

  public static String hashPassword(String password) {
    byte[] salt = generateSalt();
    byte[] hashedPassword = hash(password.toCharArray(), salt);

    // Devolver la contraseña hasheada en formato Base64 junto con la sal, también en Base64
    return Base64.getEncoder().encodeToString(salt) + ":"
        + Base64.getEncoder().encodeToString(hashedPassword);
  }

  /**
   * Valida una contraseña ingresada con un hash almacenado.
   *
   * @param password Contraseña ingresada.
   * @param storedHash Hash almacenado.
   * @return True si la contraseña es válida, false en caso contrario.
   */

  public static boolean validatePassword(String password, String storedHash) {
    // Separar la sal del hash
    String[] parts = storedHash.split(":");
    byte[] salt = Base64.getDecoder().decode(parts[0]);
    byte[] storedHashedPassword = Base64.getDecoder().decode(parts[1]);

    // Volver a hashear la contraseña ingresada usando la sal original
    byte[] hashedPassword = hash(password.toCharArray(), salt);

    // Comparar los dos hashes
    return slowEquals(storedHashedPassword, hashedPassword);
  }

  private static byte[] generateSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[SALT_LENGTH];
    random.nextBytes(salt);
    return salt;
  }

  private static byte[] hash(char[] password, byte[] salt) {
    try {
      PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
      SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
      return skf.generateSecret(spec).getEncoded();
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException("Error al hashear la contraseña", e);
    }
  }

  private static boolean slowEquals(byte[] a, byte[] b) {
    int diff = a.length ^ b.length;
    for (int i = 0; i < a.length && i < b.length; i++) {
      diff |= a[i] ^ b[i];
    }
    return diff == 0;
  }
}
