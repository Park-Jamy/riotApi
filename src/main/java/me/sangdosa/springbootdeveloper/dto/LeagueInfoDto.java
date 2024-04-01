package me.sangdosa.springbootdeveloper.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LeagueInfoDto {

    private String queueType;   // 랭크정보
    private String tier;        // 티어 || 챌린저, 그랜드마스터, 마스터,,,
    private String rank;        // 티어 등급 || I, II,,,
    private int leaguePoints;   // 티어 점수
    private int wins;           // 승
    private int losses;         // 패

}
