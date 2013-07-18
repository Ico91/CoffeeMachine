package modules.menuModule;
import java.util.Scanner;

class InputReader {
	public InputReader() {
		
	}
	public String readInput() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		return input;
	}
}
