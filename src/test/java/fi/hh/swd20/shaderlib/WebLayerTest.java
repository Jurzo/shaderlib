package fi.hh.swd20.shaderlib;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUnauthorizedAccessToDash() throws Exception {
        this.mockMvc.perform(get("/dashboard")).andDo(print())
        .andExpect(status().is(401));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void testAuthorizedAccessToDash() throws Exception {
        this.mockMvc.perform(get("/dashboard")).andDo(print())
        .andExpect(status().is(200)).andExpect(content().string(containsString("Shaders")));
    }

    @Test
    public void testLoginForm() throws Exception {
        this.mockMvc.perform(get("/signin")).andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(containsString("User Name")));
    }

    @Test
    public void testRegisterForm() throws Exception {
        this.mockMvc.perform(get("/register")).andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(containsString("Username")));
    }
    
}
