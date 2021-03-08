package cinema;

import java.util.Arrays;
import java.util.Scanner;

class Seat {
	int row;
	int col;

	Seat(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

class AlreadyTakenSeatException extends Exception {
}

class NonExistingSeatException extends Exception {
}

public class Cinema {
	private final int rows;
	private final int columns;
	private final String[][] seats;
	private int purchased = 0;
	private int currentIncome = 0;
	private static Scanner scanner = new Scanner(System.in);

	private Cinema(int rows, int cols) {
		this.rows = rows;
		this.columns = cols;
		seats = createEmptySeats(rows, cols);
	}

	private static String[][] createEmptySeats(int rows, int cols) {
		String[][] seats = new String[rows][];
		for (int i = 0; i < rows; i++) {
			String[] row = new String[cols];
			Arrays.fill(row, "S");
			seats[i] = row;
		}
		return seats;
	}

	private String getTopRow() {
		String[] topRow = new String[this.columns + 1];
		topRow[0] = " ";
		for (int i = 1; i < topRow.length; i++) {
			topRow[i] = String.valueOf(i);
		}
		return String.join(" ", topRow);
	}

	private int getPrice(Seat seat) {
		int price;
		if (columns * rows <= 60 || seat.row <= rows / 2) {
			price = 10;
		} else {
			price = 8;
		}
		return price;
	}

	private void printPrice(int price) {
		System.out.printf("Ticket price: $%s\n", price);
		System.out.println();
	}

	private void takeSeat(Seat seat) throws AlreadyTakenSeatException {
		if (seats[seat.row - 1][seat.col - 1].equals("B")) {
			throw new AlreadyTakenSeatException();
		} else {
			seats[seat.row - 1][seat.col - 1] = "B";
		}
	}

	private Seat selectSeat() throws NonExistingSeatException {
		System.out.println("Enter a row number:");
		int row = scanner.nextInt();
		System.out.println("Enter a seat number in that row:");
		int column = scanner.nextInt();
		System.out.println();

		if (row < 1 || row > rows || column < 1 || column > columns) {
			throw new NonExistingSeatException();
		}
		return new Seat(row, column);
	}

	private void buySeat() {
		try {
			Seat seat = selectSeat();
			takeSeat(seat);
			int price = getPrice(seat);
			currentIncome += price;
			purchased++;
			printPrice(price);
		} catch (AlreadyTakenSeatException e) {
			System.out.println("That ticket has already been purchased!");
			System.out.println();
			buySeat();
		} catch (NonExistingSeatException e) {
			System.out.println("Wrong input!");
			System.out.println();
			buySeat();
		}
	}

	private void printSeats() {
		System.out.println("Cinema:");
		String topRow = getTopRow();
		System.out.println(topRow);
		for (int i = 1; i <= seats.length; i++) {
			System.out.printf("%d ", i);
			String row = String.join(" ", seats[i - 1]);
			System.out.println(row);
		}
		System.out.println();
	}

	private int getTotalIncome() {
		if (columns * rows <= 60) {
			return rows * columns * 10;
		} else {
			return (rows / 2) * columns * 10 + (rows - rows / 2) * columns * 8;
		}
	}

	private String getPercentage() {
		double percentage = (double) purchased * 100 / (columns * rows);
		return String.format("%.2f", percentage);
	}

	private void printStatistics() {
		System.out.printf("Number of purchased tickets: %d\n", purchased);
		System.out.printf("Percentage: %s%%\n", getPercentage());
		System.out.printf("Current income: $%d\n", currentIncome);
		System.out.printf("Total income: $%d\n", getTotalIncome());
		System.out.println();
	}

	private void showMenu() {

		while (true) {
			System.out.println("1. Show the seats");
			System.out.println("2. Buy a ticket");
			System.out.println("3. Statistics");
			System.out.println("0. Exit");

			int input = scanner.nextInt();
			System.out.println();

			switch (input) {
				case 0:
					return;
				case 1:
					printSeats();
					break;
				case 2:
					buySeat();
					break;
				case 3:
					printStatistics();
					break;
				default:
					break;
			}
		}
	}

	private static Cinema readCinema() {
		System.out.println("Enter the number of rows:");
		int rows = scanner.nextInt();
		System.out.println("Enter the number of seats in each row:");
		int cols = scanner.nextInt();
		System.out.println();
		return new Cinema(rows, cols);
	}

	public static void main(String[] args) {
		Cinema cinema = readCinema();
		cinema.showMenu();
	}
}