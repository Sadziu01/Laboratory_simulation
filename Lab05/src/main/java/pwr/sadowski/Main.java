/**
 * @author Bartosz Sadowski
 *gradlew build
 *gradlew jar
 *java.exe -p Lab05_pop.jar -m Lab/pwr.sadowski.Main
 */
package pwr.sadowski;

import java.awt.EventQueue;

public class Main{

	public static MyFrame frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				frame = new MyFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}

