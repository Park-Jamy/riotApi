package me.sangdosa.springbootdeveloper.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import me.sangdosa.springbootdeveloper.dto.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MatchInfoDto {
   private MetadataDto metadataDto;
   private InfoDto infoDto;
}
