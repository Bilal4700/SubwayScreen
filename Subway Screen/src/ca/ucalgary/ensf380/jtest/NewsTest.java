package ca.ucalgary.ensf380.jtest;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ca.ucalgary.ensf380.components.News;

public class NewsTest {

    private News news;

    @Before
    public void setUp() {
        // Setting up with default country code "ca"
        news = new News();
    }

    @Test
    public void testDefaultConstructor() {
        // Check if the default constructor sets the country code to "ca"
        assertEquals("ca", news.countryCode);
    }

    @Test
    public void testParameterizedConstructor() {
        // Check if the parameterized constructor sets the country code correctly
        News newsWithCountryCode = new News("us");
        assertEquals("us", newsWithCountryCode.countryCode);
    }

    @Test
    public void testFetchNews() throws Exception {
        // Fetch news with default country code "ca"
        news.fetch();
        String fetchedNews = news.getNews();
        
        // Since the content is dynamic, we just check if something was fetched
        assertNotNull("News should not be null after fetching", fetchedNews);
        assertTrue("News should contain some content", fetchedNews.length() > 0);
    }

    @Test
    public void testFetchNewsWithDifferentCountry() throws Exception {
        // Fetch news with a different country code "us"
        News newsWithCountryCode = new News("us");
        newsWithCountryCode.fetch();
        String fetchedNews = newsWithCountryCode.getNews();
        
        // Again, check that something was fetched
        assertNotNull("News should not be null after fetching", fetchedNews);
        assertTrue("News should contain some content", fetchedNews.length() > 0);
    }

    @Test
    public void testGetNews() throws Exception {
        // Fetch news and check if getNews() returns it correctly
        news.fetch();
        String fetchedNews = news.getNews();
        
        assertNotNull("News should not be null after fetching", fetchedNews);
        assertTrue("News should contain some content", fetchedNews.length() > 0);
    }
}
