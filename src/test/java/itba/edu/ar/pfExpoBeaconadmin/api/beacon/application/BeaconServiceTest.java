package itba.edu.ar.pfExpoBeaconadmin.api.beacon.application;

import itba.edu.ar.pfExpoBeaconadmin.api.beacon.model.Beacon;
import itba.edu.ar.pfExpoBeaconadmin.api.beacon.model.BeaconRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
public class BeaconServiceTest {

    @TestConfiguration
    static class BeaconServiceTestContextConfiguration {

        @Bean
        public BeaconService standService() {
            return new BeaconService();
        }
    }


    @Autowired
    private BeaconService beaconService;

    @MockBean
    private BeaconRepository beaconRepository;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


}