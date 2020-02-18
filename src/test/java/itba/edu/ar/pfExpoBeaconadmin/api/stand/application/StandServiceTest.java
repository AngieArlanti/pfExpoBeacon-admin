package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

import itba.edu.ar.pfExpoBeaconadmin.api.stand.domain.Stand;
import itba.edu.ar.pfExpoBeaconadmin.api.stand.domain.StandRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
public class StandServiceTest {

    @TestConfiguration
    static class StandServiceTestContextConfiguration {

        @Bean
        public StandService standService() {
            return new StandService();
        }
    }

    @Autowired
    private StandService standService;

    @MockBean
    private StandRepository standRepository;

    @Test
    public void create_newStandWhenValidData_isOk(){
        Stand stand1 = new Stand("1", "stand1", "Primer stand de la feria");
        Mockito.when(standRepository.save(stand1)).thenReturn(stand1);

        final Stand newStand = standService.create(stand1);

        assertThat(newStand, is(stand1));
    }

}