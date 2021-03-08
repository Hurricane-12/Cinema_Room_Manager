import java.util.Scanner;

class SimpleCalculator {
    // Implement your methods here
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        subtractTwoNumbers(a, b);
        sumTwoNumbers(a, b);
        divideTwoNumbers(a, b);
        multiplyTwoNumbers(a, b);
    }

    public static void subtractTwoNumbers(long a, long b) {
        System.out.println(a - b);
    }

    public static long sumTwoNumbers(long a, long b) {
        System.out.println(a + b);
    }

    public static void divideTwoNumbers(long a, long b) {
        if (b != 0) {
            System.out.println(a / b);
        } else {
            System.out.println("Division by 0!");
        }
    }

    public static void multiplyTwoNumbers(long a, long b) {
        System.out.println(a * b);
    }
}