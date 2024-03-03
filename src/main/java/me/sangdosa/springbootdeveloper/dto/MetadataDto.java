package me.sangdosa.springbootdeveloper.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class MetadataDto {
    private String dataVersion;
    private String matchId;
    private List<String> participants;
}
