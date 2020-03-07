package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

import itba.edu.ar.pfExpoBeaconadmin.api.beacon.application.BeaconNotAvailableException;
import itba.edu.ar.pfExpoBeaconadmin.api.beacon.application.BeaconService;
import itba.edu.ar.pfExpoBeaconadmin.api.beacon.model.Beacon;
import itba.edu.ar.pfExpoBeaconadmin.api.exception.*;
import itba.edu.ar.pfExpoBeaconadmin.api.stand.domain.Stand;
import itba.edu.ar.pfExpoBeaconadmin.api.stand.domain.StandRepository;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;

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

    @MockBean
    private BeaconService beaconService;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setUp() {
        final Stand stand1 = new Stand("0C:F3:EE:08:FC:DD", "stand1", "Primer stand de la feria");
        final Stand stand2 = new Stand("0C:F3:EE:04:19:2F", "stand2", "Segundo stand de la feria");

        final List<Stand> stands = new ArrayList<>();
        stands.add(stand1);
        stands.add(stand2);

        Mockito.when(standRepository.findAll()).thenReturn(stands);
        Mockito.when(standRepository.findById("0C:F3:EE:08:FC:DD")).thenReturn(Optional.of(stand1));
        Mockito.when(standRepository.findById("0C:F3:EE:04:19:2F")).thenReturn(Optional.of(stand2));
        //TODO se podria usar una expresion regular para que devuelva empty para todos los valores distintos a 1 y 2
        Mockito.when(standRepository.findById("3")).thenReturn(Optional.empty());
    }


    @Test
    public void create_newStandWhenValidData_isOk() throws BeaconNotAvailableException, ResourceNotFoundException {
        final Beacon beacon = new Beacon("1", -34.6403175, -58.4018125);
        Mockito.when(beaconService.getById("1")).thenReturn(beacon);
        final Stand stand1 = new Stand(beacon.getId(), "stand1", "Primer stand de la feria",
                "...", "...", beacon.getLatitude(), beacon.getLongitude(),
                Collections.EMPTY_LIST);
        Mockito.when(standRepository.save(any(Stand.class))).thenReturn(stand1);

        final StandDTO standDTO = new StandDTO("stand1", "Primer stand de la feria",
                "...", "...", beacon.getId());
        final StandDTO newStand = standService.create(standDTO);

        assertThat(newStand.getId(), is(stand1.getId()));
        assertThat(newStand.getTitle(), is(stand1.getTitle()));
        assertThat(newStand.getShortDescription(), is(stand1.getShortDescription()));
    }

    @Test
    public void getAll_notEmptyList_isOk() {
        final List<StandDTO> stands = standService.getAll();

        assertThat(stands, hasSize(2));
    }

    @Test
    public void getAll_emptyList_isOk() {
        Mockito.when(standRepository.findAll()).thenReturn(new ArrayList<>());

        final List<StandDTO> stands = standService.getAll();

        assertThat(stands, empty());
    }

    @Test
    public void getById_validId_isOk() throws ResourceNotFoundException {
        final StandDTO stand = standService.getById("0C:F3:EE:04:19:2F");

        assertThat(stand.getId(), is("0C:F3:EE:04:19:2F"));
    }

    @Test
    public void getById_notFoundId_throwException() throws ResourceNotFoundException {
        exceptionRule.expect(ResourceNotFoundException.class);
        exceptionRule.expectMessage("Stand not found for this id :: 3");
        standService.getById("3");
    }
}