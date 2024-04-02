# OP.GG 클론 코딩

## 기능
1. 소환사 검색
2. 20경기에 대한 정보

## 생성
1. Summoner-V4
- 입력된 소환사 이름을 통해 소환사 데이터 조회
- 주요 데이터는 name, puuid, revisionDate, profileIconId, summonerLevel 가 존재
- 전송 URL: API_URL_KR + "/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key=" + apiKey;
- 화면에서 받은 name 데이터를 Controller에 전송 후, name 데이터만 Service로 넘겨 API 통신 진행 후, 응답값을 Jackson으로 DTO 형식으로 변경해 화면으로 노출시켜 준다.

2. Match-V5
- 소환사 puuid 값을 통해 Match에 대한 데이터 조회
- 주요 데이터는 MatchId가 존재
- 전송 URL: API_URL_ASIA + "/lol/match/v5/matches/by-puuid/" + puuid + "/ids?api_key=" + apiKey;
- Summoner-V4 API를 통해 얻은 puuid으로 MatchId 리스트 조회
- 리스트 형식으로 리턴 받기 위해서 BufferedReader에서 HttpClient으로 호출 방식 변경
- match-id 값을 통해 Match에 대한 데이터 조회
- 주요 데이터는 kills, cs, gold 등이 존재
- 전송 URL: API_URL_ASIA + "/lol/match/v5/matches + matchId + "?api_key=" + apiKey;

3. League-V4
- 소환사 summoner-id 값을 통해 summoner의 league 데이터 조회
- 주요 데이터는 wins, losses, tier, leaguePoint 등
- 전송 URL: API_URL_KR + "/lol/league/v4/entries/by-summoner/" + summoner_id + "?api_key=" + apiKey;