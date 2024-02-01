package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.TokenManager.*;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.TokenManager.*;
import static io.restassured.RestAssured.given;

public class PlaylistApi {
  //  static String access_token="BQDWHwf_bfRScA0ipc-4_7UpOD4vNYnylCeWvkULeX_yfLr7Z0SUgp8nCsIezZuPKIfs51KgfQFdreOAiP7BySBJAxws1-SC4v3L9NSVbMh_zCR9-AoMaZnkKUQThZ8Pjl-YLPq2GqRSZi4a7lFO7E3AlpuJWY8BwLymZg1C8dzqcFWcZyii2EOK8TQjO75zE0KxqHPlw-1MxE2SdJpD88Assynx__1VTJ4zuki_tKW3GqralDk1GZRw7FVHUuymApF5QIM_dtUZaOqi";
    @Step
    public static Response post(Playlist playlist){
        return RestResource.post(USERS+ ConfigLoader.getInstance().getUserId() +PLAYLISTS, getAccessToken(),playlist);
    }
    @Step
    public static Response post(String token,Playlist playlist){
        return RestResource.post(USERS+ConfigLoader.getInstance().getUserId()+PLAYLISTS,token,playlist);
    }
    @Step
    public static Response get(String playlist_Id){
        return RestResource.get(PLAYLISTS+"/"+playlist_Id,getAccessToken());
    }
    @Step
    public static Response put(Playlist requestPlaylist,String playlist_Id){
        return RestResource.put(PLAYLISTS+"/"+playlist_Id,getAccessToken(),requestPlaylist);
    }

}
