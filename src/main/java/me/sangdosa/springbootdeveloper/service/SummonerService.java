package me.sangdosa.springbootdeveloper.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.sangdosa.springbootdeveloper.dto.MatchDto;
import me.sangdosa.springbootdeveloper.dto.MatchInfoDto;
import me.sangdosa.springbootdeveloper.dto.SummonerDto;
import me.sangdosa.springbootdeveloper.constant.RiotConstant;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import org.springframework.stereotype.Service;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


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
            //HttpResponse response = callRiotApi(callUrl);
            JSONObject response2 = callRoitApi2(callUrl);
            //summonerDto = objectMapper.readValue(response.getEntity().getContent(), SummonerDto.class); // JSON형식을 DTO형식으로 변환
            summonerDto = objectMapper.readValue(response2.toString(), SummonerDto.class);

        }catch (IOException e){
            e.printStackTrace();
        }

        return summonerDto;
    }

    public List<String> callRiotAPISummonerMatchByPuuid(String puuid) {
        List<String> matchList = new ArrayList<>(); // List 초기화
        String callUrl = API_URL_ASIA + "/lol/match/v5/matches/by-puuid/" + puuid + "/ids?api_key=" + apiKey; // URL 세팅
        try {
            HttpResponse response = callRiotApi(callUrl);

            matchList = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<List<String>>() {});

        }catch (IOException e){
            e.printStackTrace();
        }
        return matchList;
    }

    public List<MatchDto> callRiotAPIMatchByMatchId(List<String> matchId) {

        List<MatchDto> matchsDto = new ArrayList<>();

        int matchListLen = matchId.size();
        for(int i = 0; i < matchListLen; i++) {
            MatchDto matchDto = new MatchDto(); //DTO 초기화
            String callUrl = API_URL_ASIA + "/lol/match/v5/matches/" + matchId.get(i) + "?api_key=" + apiKey; // URL 세팅
            try {
                HttpResponse response = callRiotApi(callUrl);

                matchDto = objectMapper.readValue(response.getEntity().getContent(), MatchDto.class); // JSON형식을 DTO형식으로 변환
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                matchsDto.add(matchDto);
            }
        }
        return matchsDto;
    }

    // Riot API와 통신
    // Json 값 리턴
    private HttpResponse callRiotApi(String url) throws IOException {
        try {
            HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성

            HttpGet getRequest = new HttpGet(url); //GET 메소드 URL 생성
            HttpResponse response = client.execute(getRequest);
            return response;
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    // HttpURLConnection 사용 HTTP 통신
    private JSONObject callRoitApi2(String url) throws IOException {
        HttpURLConnection connection = null;
        JSONObject jsonObject = null;

        try{
            URL sendUrl = new URL(url);

            connection = (HttpURLConnection)sendUrl.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null){
                sb.append(line);
            }
            jsonObject = new JSONObject(sb.toString());
        }
        catch (IOException e){
            e.printStackTrace();
            return jsonObject;
        }
        finally {
            return jsonObject;
        }

    }
}

