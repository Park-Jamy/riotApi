package me.sangdosa.springbootdeveloper.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties
@Data
public class MatchInfoDto {
   private MetadataDto metadataDto;
   private InfoDto infoDto;
}
