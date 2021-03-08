import java.util.Scanner;

public class Main {

    public static int getNumberOfMaxParam(int a, int b, int c) {
        int max;
        int number;
        if (a >= b) {
            max = a;
            number = 1;
        } else {
            max = b;
            number = 2;
        }
        if (max < c) {
            number = 3;
        }
        return number;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        final int c = scanner.nextInt();

        System.out.println(getNumberOfMaxParam(a, b, c));
    }
}