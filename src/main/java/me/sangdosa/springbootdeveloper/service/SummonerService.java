package me.sangdosa.springbootdeveloper.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.sangdosa.springbootdeveloper.dto.SummonerDto;
import me.sangdosa.springbootdeveloper.constant.RiotConstant;
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

    public SummonerDto callRiotAPISummonerByName(String summonerName) {

        SummonerDto summonerDto = null;

        String API_URL = "https://kr.api.riotgames.com";

        try {
            String encodeSummonerName = URLEncoder.encode(summonerName, StandardCharsets.UTF_8.toString());

            URL url = new URL(API_URL + "/lol/summoner/v4/summoners/by-name/" + encodeSummonerName + "?api_key=" + apiKey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 요청 방식 설정 (GET)
            connection.setRequestMethod("GET");

            // 연결 타임아웃 설정
            connection.setConnectTimeout(5000); // 5 seconds
            connection.setReadTimeout(5000); // 5 seconds

            // 응답 코드 가져오기
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            StringBuilder response;

            // 응답 내용(BufferedReader)을 문자열로 변환
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // 응답 내용 출력
                System.out.println(response.toString());
                summonerDto = objectMapper.readValue(response.toString(), SummonerDto.class);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return summonerDto;
    }
}
