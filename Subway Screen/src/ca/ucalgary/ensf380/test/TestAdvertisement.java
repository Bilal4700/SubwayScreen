package ca.ucalgary.ensf380.test;

import ca.ucalgary.ensf380.components.Advertisement;

public class TestAdvertisement {

    public static void main(String[] args) throws Exception {
        Advertisement adManager = new Advertisement();
        adManager.createConnection(); 
        adManager.fetchAd();
        String paths = adManager.getAdPaths();
        System.out.println("Fetched Ad Paths:\n" + paths);
    }

}
