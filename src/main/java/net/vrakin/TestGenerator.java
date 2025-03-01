package net.vrakin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class TestGenerator {

    // Обчислення біноміального коефіцієнта C(n, k)
    public static BigInteger binomialCoefficient(int n, int k) {
        BigInteger result = BigInteger.ONE;
        for (int i = 0; i < k; i++) {
            result = result.multiply(BigInteger.valueOf(n - i))
                    .divide(BigInteger.valueOf(i + 1));
        }
        return result;
    }

    // Обчислення кількості шляхів (динамічне програмування)
    public static BigInteger countPaths(int n, int m) {
        if (n <= 0 || m <= 0) return BigInteger.valueOf(-1); // Аномальні випадки
        return binomialCoefficient(n + m, m);
    }

    // Запис у файли
    public static void saveTestToFile(String filename, String content) {
        try (FileWriter writer = new FileWriter(new File(filename))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + filename);
        }
    }

    public static void main(String[] args) {
        int[][] testCases = {
                {3, 3}, {5, 5}, {10, 10}, {10, 20}, {20, 10}, {20, 20},
                {50, 50}, {50, 100}, {100, 50}, {100, 100},
                // Аномальні тести
                {0, 0}, {1, 0}, {0, 1}, {-5, 10}, {10, -5}, {-1, -1}
        };

        for (int[] test : testCases) {
            int n = test[0], m = test[1];

            // Запис вхідних даних у .dat файл
            String datFilename = String.format("test_%dx%d.dat", n, m);
            saveTestToFile(datFilename, n + " " + m);

            // Запис очікуваного результату у .ans файл
            BigInteger paths = countPaths(n, m);
            String ansFilename = String.format("test_%dx%d.ans", n, m);
            saveTestToFile(ansFilename, paths.toString());
        }

        System.out.println("✅ Генерація тестів завершена!");
    }
}
