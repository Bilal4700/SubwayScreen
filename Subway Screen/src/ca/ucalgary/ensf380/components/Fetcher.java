package ca.ucalgary.ensf380.components;

/**
 * @author Muhammad Bilal
 * The Fetcher interface defines a contract for classes that perform 
 * data fetching operations. Implementing classes should provide the 
 * logic for fetching data within the {@code fetch} method.
 */
interface Fetcher {

    /**
     * fetches data.
     * 
     * @throws Exception if an error occurs during the fetching process.
     */
    void fetch() throws Exception;
}
