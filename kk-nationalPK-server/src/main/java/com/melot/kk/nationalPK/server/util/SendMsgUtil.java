package com.melot.kk.nationalPK.server.util;

import com.google.gson.JsonObject;
import com.melot.kk.nationalPK.server.config.ConfigService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 通用功能服务
 * @author Administrator
 *
 */
@Service
public class SendMsgUtil {
	
	private static Logger logger = Logger.getLogger(SendMsgUtil.class);

	@Autowired
    private ConfigService configService;

    /**
     * 发送消息到房间
     * @param type
     * @param msg
     * @return
     */
    public boolean sendMsgToRoom (int type, int roomId, int userId, int platform, JsonObject msg) {
        boolean result = false;
        
        HttpURLConnection url_con = null;
        try {
            StringBuffer queryParamsBuffer = new StringBuffer("?type=" + type + "&msg=" + URLEncoder.encode(msg.toString(), "utf-8"));
            if (roomId > 0) {
                queryParamsBuffer.append("&roomId=" + roomId);
            }
            if (userId > 0) {
                queryParamsBuffer.append("&userId=" + userId);
            }
            if (platform > 0) {
                queryParamsBuffer.append("&platform=" + platform);
            }
            String queryParams = queryParamsBuffer.toString();
            logger.info("GeneralService sendRunwayMsg request: " + configService.getRunwayUrl() + queryParams);
            URL url = new URL(configService.getRunwayUrl() + queryParams);
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("GET");
            url_con.setConnectTimeout(10000);
            url_con.setReadTimeout(5000);
            url_con.setDoInput(true);
            
            InputStream in = url_con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuffer tempStr = new StringBuffer();
            String tempLine = null;
            while ((tempLine = rd.readLine()) != null) {
                tempStr.append(tempLine);
            }
            logger.info("GeneralService sendRunwayMsg response: " + tempStr);
            
            rd.close();
            in.close();
            
            result = true;
        } catch (Exception e) {
            logger.error("房间跑道消息推送请求异常", e);
        } finally {
            if (url_con != null) {
                url_con.disconnect();
            }
        }
        
        return result;
    }

    /**
     * 发送消息到房间
     * @param type
     * @param msg
     * @param appId
     * @return
     */
    public boolean sendMsgToRoom (int type, int roomId, int userId, int platform, int appId, String msg) {
        boolean result = false;
        
        HttpURLConnection url_con = null;
        try {
            StringBuffer queryParamsBuffer = new StringBuffer("?type=" + type + "&msg=" + URLEncoder.encode(msg, "utf-8"));
            if (roomId > 0) {
                queryParamsBuffer.append("&roomId=" + roomId);
            }
            if (userId > 0) {
                queryParamsBuffer.append("&userId=" + userId);
            }
            if (platform > 0) {
                queryParamsBuffer.append("&platform=" + platform);
            }
            if (appId > 0) {
                queryParamsBuffer.append("&appId=" + appId);
            }
            String queryParams = queryParamsBuffer.toString();
            logger.info("GeneralService sendRunwayMsg request: " + configService.getRunwayUrl() + queryParams);
            URL url = new URL(configService.getRunwayUrl() + queryParams);
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("GET");
            url_con.setConnectTimeout(10000);
            url_con.setReadTimeout(5000);
            url_con.setDoInput(true);
            
            InputStream in = url_con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuffer tempStr = new StringBuffer();
            String tempLine = null;
            while ((tempLine = rd.readLine()) != null) {
                tempStr.append(tempLine);
            }
            logger.info("GeneralService sendRunwayMsg response: " + tempStr);
            
            rd.close();
            in.close();
            
            result = true;
        } catch (Exception e) {
            logger.error("房间跑道消息推送请求异常", e);
        } finally {
            if (url_con != null) {
                url_con.disconnect();
            }
        }
        
        return result;
    }
 
    
    public static void main(String args[]) {
        JsonObject msg = new JsonObject();
        msg.addProperty("MsgTag", 30000001);
        msg.addProperty("content", "测试测试测试测试测试测试测试");
        
        String urlAddress = "http://10.0.3.8:8080/";
        
        HttpURLConnection url_con = null;
        try {
            StringBuffer queryParamsBuffer = new StringBuffer("?type=" + 2 + "&msg=" + URLEncoder.encode(msg.toString(), "utf-8"));
            if (1193456 > 0) {
                queryParamsBuffer.append("&roomId=" + 1193456);
            }
            if (1000080 > 0) {
                queryParamsBuffer.append("&userId=" + 1000080);
            }
            String queryParams = queryParamsBuffer.toString();
            logger.info("GeneralService sendRunwayMsg request: " + urlAddress + queryParams);
            URL url = new URL(urlAddress + queryParams);
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("GET");
            url_con.setConnectTimeout(10000);
            url_con.setReadTimeout(5000);
            url_con.setDoInput(true);
            
            InputStream in = url_con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            StringBuffer tempStr = new StringBuffer();
            String tempLine = null;
            while ((tempLine = rd.readLine()) != null) {
                tempStr.append(tempLine);
            }
            logger.info("GeneralService sendRunwayMsg response: " + tempStr);
            
            rd.close();
            in.close();
            
        } catch (Exception e) {
            logger.error("房间跑道消息推送请求异常", e);
        } finally {
            if (url_con != null) {
                url_con.disconnect();
            }
        }
    }

}
