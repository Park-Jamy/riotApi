package me.sangdosa.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.sangdosa.springbootdeveloper.dto.SummonerDto;
import me.sangdosa.springbootdeveloper.service.SummonerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// @Controller
// @RequiredArgsConstructor
// public class SummonerController {

//     private final SummonerService summonerService;

//     @GetMapping(value = "/summonerByName")
//     @ResponseBody
//     public SummonerDto callSummonerByName(String summonerName){

//         summonerName = summonerName.replaceAll(" ", "%20");

//         SummonerDto apiResult = summonerService.callRiotAPISummonerByName(summonerName);
//         //SummonerDto apiResult = new SummonerDto();

//         return apiResult;
//     }


// }

@Controller
@RequiredArgsConstructor
@RequestMapping("/riot")
public class SummonerController {

    private final Logger logger = LoggerFactory.getLogger(SummonerController.class);
    private final SummonerService summonerService;

    @GetMapping("/main")
    public String main(){
        return "main";
    }

    @GetMapping("/summoner/{summonerId}")
    public String SummonerByName(Model model, @PathVariable String summonerId){
        SummonerDto summoner = new SummonerDto();

        // 파라미터값 공백 체크
        summonerId = summonerId.replaceAll(" ","%20");

        if(summonerId == null || summonerId.isEmpty()){
            System.out.println("1######################################################");
            System.out.println(summonerId);
        }else {
            System.out.println("2######################################################");
            System.out.println(summonerId);
            summoner = summonerService.callRiotAPISummonerByName(summonerId);
        }
        System.out.println("ㅁㄴㅇㅁㄴㅇㄹㅁ" + summoner);
        model.addAttribute("summoner", summoner);
        System.out.println(model);
        return "summonerInfo";
    }
}

