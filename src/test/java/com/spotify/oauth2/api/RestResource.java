package com.spotify.oauth2.api;

import com.spotify.oauth2.pojo.Playlist;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

/*
PlaylistApi → WHAT action to perform (create/get/update playlist)

RestResource → HOW the HTTP request is executed

This avoids duplication and improves maintainability */


public class RestResource {
    static String access_token="BQCGyxKVVUoc4W8YCJSGK3p03a8Jx0y_170Kxtur-z08ZEtgOZFDIv4tHwz82ZrLdY8QTfKiicPueNhYX9aSELNRgqPq2erfO6gx7sIq80hyQBetb52P2BeeQd4AQ9G348ShqhdKFdAWcYntP9d8gE-_lVuo7dswmCKFG09FlVYhNhageSQAEROpRWmxf9OcRK-9xFyLbDyMpgVLPfmPYdovcLOVUgNStIsOw3f_8bdkzdLCVzmC56vfAE_N6pu1pGgG1CJd94Nh-zju";
    public static Response post(String path,String token,Object object){
        return given(getRequestSpec()).
                body(object).
                auth().oauth2(token).
        when().
                post(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response post_account(HashMap<String,String> formsData){
        return given(getAccountRequestSpec()).
                formParams(formsData).
                when().
                post(API+TOKEN).
                then().spec(SpecBuilder.getResponseSpec()).
                extract().
                response();
    }
    public static Response get(String path, String token){
        return  given(getRequestSpec()).
                auth().oauth2(token).
                when().
                get(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }
    public static Response put(String path,String token,Object object){
         return given(getRequestSpec()).
                 auth().oauth2(token).
                body(object).
                when().
                put(path).
                then().spec(getResponseSpec()).
                 extract().response();
    }

}
