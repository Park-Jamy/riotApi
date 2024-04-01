package me.sangdosa.springbootdeveloper.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PerkStyleSelectionDto {
    private int perk;
    private int var1;
    private int var2;
    private int var3;
}
