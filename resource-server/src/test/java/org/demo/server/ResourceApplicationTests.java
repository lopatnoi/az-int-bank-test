package org.demo.server;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ResourceApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("'/' fail with 401 error if not authenticated")
    void indexWhenNotAuthenticatedThenUnauthorized() throws Exception {
        MockHttpServletRequestBuilder request = get("/").accept(MediaType.TEXT_HTML);
        mockMvc.perform(request)
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    @DisplayName("'/' fail with 404 error when authenticated")
    void indexWhenAuthenticatedThenNotFound() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    @DisplayName("'products' page call when authenticated")
    void productsWhenAuthenticatedThenOk() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("'products' page fail when not authenticated")
    void productsWhenFailThenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(unauthenticated());
    }
}
