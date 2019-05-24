package de.todo42.adesso.config;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("prod")
public class ApplicationConfigTest {
    
    @Autowired
    private ApplicationConfig appConfig;
    
    
    @Test
    public void testConfig() throws Exception {
        assertEquals("val1", appConfig.getSingleProp());
        assertEquals(new Integer(80), appConfig.getPort());
        assertEquals("Test", appConfig.getWorkshop().getLabel());
    }
    
    
    
}
