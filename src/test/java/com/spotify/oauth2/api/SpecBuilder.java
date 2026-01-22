package com.spotify.oauth2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.oauth2.api.Route.BASEPATH;

public class SpecBuilder {
    static String access_token="BQCGyxKVVUoc4W8YCJSGK3p03a8Jx0y_170Kxtur-z08ZEtgOZFDIv4tHwz82ZrLdY8QTfKiicPueNhYX9aSELNRgqPq2erfO6gx7sIq80hyQBetb52P2BeeQd4AQ9G348ShqhdKFdAWcYntP9d8gE-_lVuo7dswmCKFG09FlVYhNhageSQAEROpRWmxf9OcRK-9xFyLbDyMpgVLPfmPYdovcLOVUgNStIsOw3f_8bdkzdLCVzmC56vfAE_N6pu1pGgG1CJd94Nh-zju";
    public static RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder().
//                setBaseUri(System.getProperty("BASE_URI")).
                setBaseUri("https://api.spotify.com").
                setBasePath(BASEPATH).
                setContentType(ContentType.JSON).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).build();
    }

    public static RequestSpecification getAccountRequestSpec(){
        return new RequestSpecBuilder().
//                setBaseUri(System.getProperty("ACCOUNT_BASE_URI")).
                setBaseUri("https://accounts.spotify.com").
                setContentType(ContentType.URLENC).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).build();
    }

    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).build();
    }
}
