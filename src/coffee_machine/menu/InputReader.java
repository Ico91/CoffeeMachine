package coffee_machine.menu;
import java.util.Scanner;

class InputReader {
	Scanner scanner;
	public InputReader() {
		this.scanner = new Scanner(System.in);
	}
	public String readInput() {
		String input = scanner.nextLine();
		return input;
	}
}
