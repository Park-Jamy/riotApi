package me.sangdosa.springbootdeveloper.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties
@Data
public class ParticipantDto {
    private int participantId;
    private String summonerName;
    private String championName;       // https://ddragon.leagueoflegends.com/cdn/10.6.1/img/champion/Shyvana.png
    private int spell1Casts;              // https://ddragon.leagueoflegends.com/cdn/10.6.1/img/spell/SummonerBoost.png
    private int spell2Casts;
    private int level;
    private int deaths;
    private int assists;
    private int kills;
    private int item0;               // https://ddragon.leagueoflegends.com/cdn/10.6.1/img/item/3108.png
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    private int goldEarned;
    private int totalminionsKilled;
    private PerksDto perks;               // https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/Precision/PressTheAttack/PressTheAttack.png //  //https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/7200_Domination.png
    private int teamId;

    private boolean win;
}
