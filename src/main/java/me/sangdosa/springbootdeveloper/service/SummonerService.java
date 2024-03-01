package me.sangdosa.springbootdeveloper.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.sangdosa.springbootdeveloper.dto.MatchInfoDto;
import me.sangdosa.springbootdeveloper.dto.SummonerDto;
import me.sangdosa.springbootdeveloper.constant.RiotConstant;
import me.sangdosa.springbootdeveloper.dto.SummonerMatchDto;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Service
public class SummonerService {

    private ObjectMapper objectMapper = new ObjectMapper();

    //@Value("${RGAPI-961e1a22-5f97-41e0-9fe5-34435d2b38d9}")
    private String apiKey = RiotConstant.API_KEY;
    private String API_URL_KR = RiotConstant.API_SERVER_KR;
    private String API_URL_ASIA = RiotConstant.API_SERVER_ASIA;

    public SummonerDto callRiotAPISummonerByName(String summonerName) {

        SummonerDto summonerDto = new SummonerDto(); // DTO 초기화
        String callUrl = API_URL_KR + "/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key=" + apiKey;  // URL 세팅
        try {
            String response = callRiotApi(callUrl); // URL을 통해 응답 요청
            summonerDto = objectMapper.readValue(response, SummonerDto.class); // JSON형식을 DTO형식으로 변환

        }catch (IOException e){
            e.printStackTrace();
        }

        return summonerDto;
    }

    public SummonerMatchDto callRiotAPISummonerMatchByPuuid(String puuid) {
        SummonerMatchDto summonerMatchDto = new SummonerMatchDto(); //DTO 초기화
        String callUrl = API_URL_ASIA + "/lol/match/v5/matches/by-puuid/" + puuid + "/ids?api_key=" + apiKey; // URL 세팅
        try {
            String response = callRiotApi(callUrl); // URL을 통해 응답 요청
            summonerMatchDto = objectMapper.readValue(response, SummonerMatchDto.class); // JSON형식을 DTO형식으로 변환

        }catch (IOException e){
            e.printStackTrace();
        }
        return summonerMatchDto;
    }

    public MatchInfoDto callRiotAPIMatchByMatchId(String matchId) {
        MatchInfoDto matchInfoDto = new MatchInfoDto(); //DTO 초기화
        String callUrl = API_URL_ASIA + "/lol/match/v5/matches/" + matchId + "?api_key=" + apiKey; // URL 세팅
        try {
            String response = callRiotApi(callUrl); // URL을 통해 응답 요청
            matchInfoDto = objectMapper.readValue(response, MatchInfoDto.class); // JSON형식을 DTO형식으로 변환

        }catch (IOException e){
            e.printStackTrace();
        }
        return matchInfoDto;
    }

    // Riot API와 통신
    // Json 값 리턴
    private String callRiotApi(String callUrl) {

        String responseToStr = ""; // 통신 Json 값

        try {
            URL url = new URL(callUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 요청 방식 설정 (GET)
            connection.setRequestMethod("GET");

            // 연결 타임아웃 설정
            connection.setConnectTimeout(5000); // 5 seconds
            connection.setReadTimeout(5000); // 5 seconds

            // 응답 코드 가져오기
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            StringBuilder response = new StringBuilder();

            // 응답 내용(BufferedReader)을 문자열로 변환
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // 응답 내용 출력
                System.out.println(response.toString());

                // 응답 내용 출력
                // 200 아닐 경우 잇셉션 내용 확인 필요
            }
            responseToStr = response.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return responseToStr;
    }
}

