package me.sangdosa.springbootdeveloper.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PerkStatsDto {
    private int defense;
    private int flex;
    private int offense;
}
