package me.sangdosa.springbootdeveloper.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class InfoDto {
    private String gameName;
    private String gameMode;
    private String gameType;
    private List<ParticipantDto> participants;
    private List<TeamDto> teams;

}
