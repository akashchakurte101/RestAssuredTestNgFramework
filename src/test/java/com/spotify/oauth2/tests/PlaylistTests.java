package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.SpecBuilder.*;
import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import com.spotify.oauth2.utils.DataLoader;
import com.spotify.oauth2.utils.FakerUtils;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
@Epic("spotify Oauth2.0")
@Feature("Plalist Api")
public class PlaylistTests extends BaseTest {

    @Story("Create a plalist")
    @Link("https://example.org")
    @Link(name="allure",type="mylink")
    @TmsLink("123")
    @Issue("1234")
    @Description("this is description")
    @Test(description = "should be able to create a playlist")
    public void should_create_a_playlist(){
        Playlist requestPlaylist=playlistBuilder(FakerUtils.generateName(),FakerUtils.generateDescription(),false);
        Response response= PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201);
        assertPlaylistEqual(response.as(Playlist.class),requestPlaylist);

    }
    @Test
    public void should_get_a_playlist(){

        Response response=PlaylistApi.get(DataLoader.getInstance().getGetPlalistId());
        assertStatusCode(response.statusCode(),StatusCode.CODE_200);
        Playlist responsePlaylist=response.as(Playlist.class);

      /*  assertThat(responsePlaylist.getName(),equalTo("New PlayList"));
        assertThat(responsePlaylist.getDescription(),equalTo("New PlayList Description"));
        assertThat(responsePlaylist.get_public(),equalTo(true));*/

    }
    @Test
    public void update_playlist(){
        Playlist requestPlaylist=playlistBuilder(FakerUtils.generateName(),FakerUtils.generateDescription(),false);
        Response response=PlaylistApi.put(requestPlaylist, DataLoader.getInstance().getUpdatePlaylistId());
        assertStatusCode(response.statusCode(),StatusCode.CODE_200);
    }
    @Story("Create a plalist")
    @Test
    public void should_not_create_a_playlist_withoutName(){
        Playlist requestPlaylist=playlistBuilder("",FakerUtils.generateDescription(),false);
           Response response=PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(),StatusCode.CODE_400);

        assertError(response.as(Error.class),StatusCode.CODE_400);
    }
    @Story("Create a plalist")
    @Test
    public void should_not_create_a_playlist_withExpired_token(){
        String invalid_token="12345";
        Playlist requestPlaylist=playlistBuilder(FakerUtils.generateName(),FakerUtils.generateDescription(),false);
        Response response= PlaylistApi.post(invalid_token,requestPlaylist);
        assertStatusCode(response.statusCode(),StatusCode.CODE_401);

        assertError(response.as(Error.class),StatusCode.CODE_401);
    }
    @Step
    public Playlist playlistBuilder(String name,String description,boolean _public){
      return Playlist.builder().
              name(name).
              description(description).
              _public(_public).build();
    }
    @Step
    public void assertPlaylistEqual(Playlist responsePlaylist,Playlist requestPlaylist){
        assertThat(requestPlaylist.getName(),equalTo(responsePlaylist.getName()));
        assertThat(requestPlaylist.getDescription(),equalTo(responsePlaylist.getDescription()));
        assertThat(requestPlaylist.get_public(),equalTo(responsePlaylist.get_public()));
    }
    @Step
    public void assertError(Error responseError,StatusCode code){
        assertThat(responseError.getError().getStatus(),equalTo(code.code));
        assertThat(responseError.getError().getMessage(),equalTo(code.msg));
    }
    @Step
    public void assertStatusCode(int actualStatusCode,StatusCode statuscode){
        assertThat(actualStatusCode,equalTo(statuscode.code));
    }
}
