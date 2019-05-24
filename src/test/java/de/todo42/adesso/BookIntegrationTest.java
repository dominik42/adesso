package de.todo42.adesso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.todo42.adesso.book.Book;
import de.todo42.adesso.book.BookRestController;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BookIntegrationTest {

    @Autowired
    private BookRestController bookController;
    
    @Autowired
    private WebApplicationContext wac;
    
    @Autowired
    private ObjectMapper mapper;
    
    private MockMvc mockMvc; 
    
    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }
    
    
    @Test
    public void testGetAllBooks() throws Exception {
        Collection<Book> books = bookController.getAllBooks();
        assertEquals(3,  books.size());
    }

    @Test
    public void testGetAllBooksByHttp() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/book")
                .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andReturn();
        
        String json = mvcResult.getResponse().getContentAsString();
        assertNotNull(json);
        log.debug("JSON response {}", json);
        Collection<Book> books = (Collection<Book>) mapper.readValue(json, Collection.class);
        assertEquals(3,  books.size());
    }

    @Test
    public void testGetAllBooksByHttpAndJsonPath() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/book")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[1].title").value("Clean Code"));
    }
    
    
}
