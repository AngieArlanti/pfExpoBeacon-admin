package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

import itba.edu.ar.pfExpoBeaconadmin.api.stand.domain.Stand;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StandController.class)
public class StandControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StandService standService;

    @Before
    public void setUp() {
        final Stand stand1 = new Stand("1", "stand1", "Primer stand de la feria");
        final Stand stand2 = new Stand("2", "stand2", "Segundo stand de la feria");

        final List<Stand> stands = new ArrayList<>();
        stands.add(stand1);
        stands.add(stand2);

        Mockito.when(standService.getAll()).thenReturn(stands);
    }

    @WithMockUser(value = "admin")
    @Test
    public void getAllStands_withAuth_shouldSucceedWith200() throws Exception {
        mvc.perform(get("/admin/stands").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllStands_withoutAuth_shouldSucceedWith200() throws Exception {
        mvc.perform(get("/admin/stands").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}