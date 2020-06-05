package top.xuwuruoshui.sociallogin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.xuwuruoshui.sociallogin.utils.QQUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class QQController {


    /**
     * 获取授权码
     * @param session
     * @return
     */
    @GetMapping("/qq/oauth")
    public String oauth(HttpSession session){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        session.setAttribute("state",uuid);

        String auth_url = "https://graph.qq.com/oauth2.0/authorize?response_type=code"
                +"&client_id="+ QQUtil.APP_ID
                +"&redirect_uri="+QQUtil.CALLBACK_URL
                +"&state="+uuid;
        return "redirect:"+auth_url;
    }

    @GetMapping("/qq/callback")
    public String callBack(HttpServletRequest request, HttpServletResponse response){

        ObjectMapper obj = new ObjectMapper();

        HttpSession session = request.getSession();
        String sessionState= (String)session.getAttribute("state");

        //获取授权码
        String code = request.getParameter("code");
        String paramState = request.getParameter("state");

        if(paramState!=null){
            if(!paramState.equals(sessionState)){
                throw new RuntimeException("QQ state错误");
            }
        }

        String accessUrl = "https://graph.qq.com/oauth2.0/token?"
                +"&grant_type=authorization_code"
                +"&client_id="+QQUtil.APP_ID
                +"&client_secret="+QQUtil.APP_KEY
                +"&code="+code
                +"&redirect_uri="+QQUtil.CALLBACK_URL;

        //获取token
        String accessToken = QQUtil.getAccessToken(accessUrl);

        //获取openId
        String openUrl = "https://graph.qq.com/oauth2.0/me?access_token="+accessToken;
        String openData = QQUtil.getOpenData(openUrl);
        String openStr = openData.substring(openData.indexOf("(")+1, openData.indexOf(")"));

        Map openJson = null;
        String openId = null;
        try {
            openJson = obj.readValue(openStr, HashMap.class);
            openId = (String)openJson.get("openid");
            System.err.println(openId);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //获取QQ用户信息
        String infoUrl = "https://graph.qq.com/user/get_user_info?"
                +"access_token="+accessToken+"&"
                +"oauth_consumer_key="+QQUtil.APP_ID+"&"
                +"openid="+openId;
        String infoStr = QQUtil.getQQInfo(infoUrl);
        Map infoJson = null;
        try {
            infoJson = obj.readValue(infoStr, HashMap.class);
            infoJson.entrySet().forEach(System.out::println);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }
}
