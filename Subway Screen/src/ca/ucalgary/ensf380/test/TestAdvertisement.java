package ca.ucalgary.ensf380.test;

import ca.ucalgary.ensf380.components.Advertisement;

public class TestAdvertisement {

    public static void main(String[] args) throws Exception {
        Advertisement adManager = new Advertisement();
        adManager.createConnection(); // Establish the database connection


       
        // Fetch and print the advertisement paths
        String adPaths = adManager.fetchAd();
        System.out.println("Fetched Ad Paths:\n" + adPaths);
    }

}
