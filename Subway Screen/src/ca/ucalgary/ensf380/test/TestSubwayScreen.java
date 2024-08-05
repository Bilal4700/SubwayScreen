package ca.ucalgary.ensf380.test;



import javax.swing.SwingUtilities;

import ca.ucalgary.ensf380.gui.SubwayScreen;

public class TestSubwayScreen {
    public static void main(String[] args) {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Error: Please provide one or two command-line arguments.");
            System.out.println("Usage: java ca.ucalgary.ensf380.gui.TestSubwayScreen <city> [countrycode]");
            return;
        }

        String city = args[0];
        String countrycode = (args.length == 3) ? args[2] : null;
        String trainNumb = args[1];
        SwingUtilities.invokeLater(() -> {
			try {
				new SubwayScreen(city, countrycode, trainNumb);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    }
}
