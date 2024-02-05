package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class TokenManager {
    private static String access_token;
    private static Instant expairy_time;

    public synchronized static String getAccessToken(){
        try {
            if(access_token==null || Instant.now().isAfter(expairy_time)) {
                System.out.println("Renewing access token...");
                Response response = renew_token();
                access_token = response.path("access_token");
                int expairyDurationInSeconds = response.path("expires_in");
                expairy_time = Instant.now().plusSeconds(expairyDurationInSeconds - 300);
            }
            else{
                System.out.println("Token is good use");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("ABORT!!! failed to get the token");
        }

     return access_token;
    }

   private static Response renew_token(){
        HashMap<String,String> formData=new HashMap<>();
        formData.put("client_id", ConfigLoader.getInstance().getClientId());
        formData.put("client_secret",ConfigLoader.getInstance().getClientSecret());
        formData.put("refresh_token",ConfigLoader.getInstance().getRefreshToken());
        formData.put("grant_type",ConfigLoader.getInstance().getGrantType());

        Response response= RestResource.post_account(formData);
        if(response.statusCode()!=200){
            throw new RuntimeException("ABORT!!! renew token failed");
        }
        return response;
    }
}
