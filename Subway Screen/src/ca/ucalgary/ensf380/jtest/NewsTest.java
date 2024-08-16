package ca.ucalgary.ensf380.jtest;

import static org.junit.Assert.*;
import org.junit.Test;
import ca.ucalgary.ensf380.components.News;

/**
 * @author Fateh Ali
 * The NewsTest class provides unit tests for the News class, 
 * focusing on the constructors, fetching news, and retrieving news content.
 */
public class NewsTest {
    private News news;

    /**
     * Tests the default constructor of the News class.
     * Verifies that the default constructor sets the country code to "ca".
     */
    @Test
    public void testDefaultConstructor() {
        news = new News();
        // Check if the default constructor sets the country code to "ca"
        assertEquals("ca", news.countryCode);
    }

    /**
     * Tests the parameterized constructor of the News class.
     * Verifies that the parameterized constructor correctly sets the provided country code.
     */
    @Test
    public void testParameterizedConstructor() {
        news = new News();
        // Check if the parameterized constructor sets the country code correctly
        News newsWithCountryCode = new News("us");
        assertEquals("us", newsWithCountryCode.countryCode);
    }

    /**
     * Tests the fetch method of the News class using the default country code "ca".
     * Verifies that news content is fetched and that the content is not null or empty.
     * 
     * @throws Exception if an error occurs during the fetch operation.
     */
    @Test
    public void testFetchNews() throws Exception {
        news = new News();
        // Fetch news with default country code "ca"
        news.fetch();
        String fetchedNews = news.getNews();
        
        // Since the content is dynamic, we just check if something was fetched
        assertNotNull("News should not be null after fetching", fetchedNews);
        assertTrue("News should contain some content", fetchedNews.length() > 0);
    }

    /**
     * Tests the fetch method of the News class using a different country code "us".
     * Verifies that news content is fetched and that the content is not null or empty.
     * 
     * @throws Exception if an error occurs during the fetch operation.
     */
    @Test
    public void testFetchNewsWithDifferentCountry() throws Exception {
        news = new News();
        // Fetch news with a different country code "us"
        News newsWithCountryCode = new News("us");
        newsWithCountryCode.fetch();
        String fetchedNews = newsWithCountryCode.getNews();
        
        // Again, check that something was fetched
        assertNotNull("News should not be null after fetching", fetchedNews);
        assertTrue("News should contain some content", fetchedNews.length() > 0);
    }

    /**
     * Tests the getNews method of the News class.
     * Verifies that the getNews method returns the fetched news content correctly.
     * 
     * @throws Exception if an error occurs during the fetch operation.
     */
    @Test
    public void testGetNews() throws Exception {
        news = new News();
        // Fetch news and check if getNews() returns it correctly
        news.fetch();
        String fetchedNews = news.getNews();
        
        assertNotNull("News should not be null after fetching", fetchedNews);
        assertTrue("News should contain some content", fetchedNews.length() > 0);
    }
}
