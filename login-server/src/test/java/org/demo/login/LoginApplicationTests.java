package org.demo.login;

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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class LoginApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("redirect from '/' to login page if not authenticated")
    void indexWhenNotAuthenticatedThenRedirectsToLoginPage() throws Exception {
        MockHttpServletRequestBuilder request = get("/").accept(MediaType.TEXT_HTML);
        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/oauth2/authorization/keycloak"));
    }

    @Test
    @WithMockUser
    @DisplayName("successfully go to index page when authenticated")
    void indexWhenAuthenticatedThenOk() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName("successfully login when authenticated")
    void loginWhenSuccessThenAuthenticated() throws Exception {
        mockMvc.perform(formLogin())
                .andExpect(authenticated());
    }

    @Test
    @DisplayName("fail login when not authenticated")
    void loginWhenFailThenNotAuthenticated() throws Exception {
        mockMvc.perform(formLogin().user("invalid"))
                .andExpect(unauthenticated());
    }
}
