import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

class GuessNumberGame {

    private static void clearLogFile() {
        try (FileWriter writer = new FileWriter("log.txt", false)) {
            writer.write("");
        } catch (IOException e) {
            System.out.println("Ошибка при очистке файла лога: " + e.getMessage());
        }
    }

    private static void logAttempt(int attempt, int guess) {
        try (FileWriter writer = new FileWriter("log.txt", true)) {
            writer.write("Попытка " + attempt + ": введено число " + guess + "\n");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл лога: " + e.getMessage());
        }
    }

    private static void logSuccess(int attempts) {
        try (FileWriter writer = new FileWriter("log.txt", true)) {
            writer.write("Угадано за " + attempts + " попыток\n");
        } catch (IOException e) {
            System.out.println("Ошибка при записи результата в файл: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int secretNumber = random.nextInt(100) + 1;
        int attempts = 0;
        int userGuess = 0;

        System.out.println("Задано число от 1 до 100. Попробуйте угадать.");

        clearLogFile();

        while (userGuess != secretNumber) {
            attempts++;
            System.out.print("Попытка " + attempts + ". Введите число: ");

            if (scanner.hasNextInt()) {
                userGuess = scanner.nextInt();

                logAttempt(attempts, userGuess);

                if (userGuess < 1 || userGuess > 100) {
                    System.out.println("Пожалуйста, введите число от 1 до 100!");
                } else if (userGuess < secretNumber) {
                    System.out.println("Больше!");
                } else if (userGuess > secretNumber) {
                    System.out.println("Меньше!");
                } else {
                    System.out.println("Поздравляю! Вы угадали число " + secretNumber + " за " + attempts + " попыток!");
                    logSuccess(attempts);
                }
            } else {
                System.out.println("Ошибка! Пожалуйста, введите целое число.");
                scanner.next();
                attempts--;
            }
        }

        scanner.close();
    }

    
}
