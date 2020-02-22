package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

import itba.edu.ar.pfExpoBeaconadmin.api.stand.domain.Stand;

public class StandMapper {

    public StandDTO toDto(final Stand stand) {
        return new StandDTO(stand.getId(), stand.getTitle(),
                stand.getShortDescription(), stand.getDescription(),
                stand.getCover(), stand.getLatitude(),
                stand.getLongitude(), stand.getPictures());
    }

    public Stand toModel(final StandDTO standDTO) {
        return new Stand(standDTO.getId(), standDTO.getTitle(),
                standDTO.getShortDescription(), standDTO.getDescription(),
                standDTO.getCover(), standDTO.getLatitude(),
                standDTO.getLongitude(), standDTO.getPictures());
    }
}
