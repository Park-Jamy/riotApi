package me.sangdosa.springbootdeveloper.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TeamDto {
    private int teamId;
    private boolean win;
    private BanDto[] bans;
    private ObjectivesDto objectives;
}
