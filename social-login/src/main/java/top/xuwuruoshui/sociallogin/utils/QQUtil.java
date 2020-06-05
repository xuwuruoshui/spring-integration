package top.xuwuruoshui.sociallogin.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


public class QQUtil {
    public static final String APP_ID="101882612";
    public static final String APP_KEY="be7cabdde6eddecedfe6862ece6972b1";
    public static final String CALLBACK_URL = "http://www.xuwuruoshui.club/qq/callback";


    private static final RestTemplate restTemplate = new RestTemplate();



    public static String getAccessToken(String accessUrl){

        String access = restTemplate.getForObject(accessUrl, String.class);

        //返回的个格式
        //access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14
        if(null == access) {
            throw new RuntimeException("access为空");
        }

        return access.split("&")[0].split("=")[1];
    }

    public static String getOpenData(String token){

        String openData = restTemplate.getForObject(token, String.class);
        if(null == openData){
            throw new RuntimeException("openObj为空");
        }
        return openData;
    }

    public static String getQQInfo(String infoUrl) {
        String info = restTemplate.getForObject(infoUrl, String.class);
        if(null == info){
            throw new RuntimeException("QQInfo为空");
        }
        return info;
    }
}
