package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

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
        final StandDTO stand1 = new StandDTO("1", "stand1", "Primer stand de la feria",
                "...", "...", 1);
        final StandDTO stand2 = new StandDTO("2", "stand2", "Segundo stand de la feria",
                "...", "...", 2);

        final List<StandDTO> standDTOList = new ArrayList<>();
        standDTOList.add(stand1);
        standDTOList.add(stand2);

        Mockito.when(standService.getAll()).thenReturn(standDTOList);
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