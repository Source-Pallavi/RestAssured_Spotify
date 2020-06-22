package com.bridgelabz;

import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ResetAssuredTEST
{Response response;
    private static String token ;
    private String userID ;
    private String JSON = "application/json";
    private int totalPlayList;
    private String[] playlists;
    private String[] trackId;


    @BeforeMethod
    public void setUp()
    {
        token=
                "Bearer BQCUfUzDZipmQ2RC-W8LRapV929CxqFm4_0yKq2GV02YBnTa5CfKg2-O0Oxj9EiHNXsylFCYhgMrnSH3JRKVdrV6X52sfpgzw_YYNLhOLF7cOPo_Yw_mnMqai2VBQcWccNS8hri2_wk49Paweuyuf0EnhSE6wuDnafP2alxDy2paFAmNOjDtdSC4XBTJRifaVcLrQpo__-Xk518KBPrT2KMiz-EcPubNee46-ByiYGKH_Ksd-KqS6fLBFujl35jo1uRnCieipmsesYQf-8ugHAodT0HKKEcj17HV";

    }
    @Test
    public void getUserDetail_Name() {
        response = given().contentType(JSON).accept(JSON).header("Authorization", token).when().get("https://api.spotify.com/v1/me");
        userID = response.path("id");
        response.then().assertThat().statusCode(200);
        System.out.println(userID);
        /* to fetch the user id*/
        String name = response.path("display_name");
        response.then().assertThat().statusCode(200);
        System.out.println(name);
    }
/*get information of playlist*/
    @Test
    public void get_UserProfile() {
        response = given().contentType(JSON).accept(JSON).header("Authorization", token).when().get("https://api.spotify.com/v1/me");
        userID = response.path("id");
        response.then().assertThat().statusCode(200);
        System.out.println(userID);
        /* to fetch the user id*/
        response.path("id");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
    }
    /*to create play list*/
    @Test(priority = 3)
    public void create_PlayListSpotify() {
         response = given().accept(JSON).contentType(JSON).header("Authorization", token)
                .body("{\"name\": \"Prad\"," +
                        "\"description\": \"New playlist description\"," +
                        "\"public\": false}").when().post(" https://api.spotify.com/v1/users/" + userID + "/playlists");
        System.out.println("PlayList Name:" + response.path("name"));
        response.then().assertThat().statusCode(201);
    }
    /*to find the total no of playlist is present*/
    @Test(priority = 2)
    public void getPlayList() {
         response = given().accept(JSON).contentType(JSON).header("Authorization", token).when().get("https://api.spotify.com/v1/users/" + userID + "/playlists");
        response.then().assertThat().statusCode(200);
        System.out.println("Total no. of PlayList:" +  response.path("total"));
    }
    @Test(priority = 1)
    public void getUser_Id() {
         response = given()
                .contentType(JSON)
                .accept(JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/me");
        userID = response.path("id");
        response.then().assertThat().statusCode(200);
        System.out.println(userID);
        response = given()
                .accept(JSON)
                .contentType(JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/users/" + userID + "/");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        response = given()
                .accept(JSON)
                .contentType(JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/users/" + userID + "/playlists");
        response.then().assertThat().statusCode(200);
        totalPlayList = response.path("total"); //get total playlist
        System.out.println("Total PlayList:" + totalPlayList);
        response = given()
                .accept(JSON)
                .contentType(JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/users/" + userID + "/playlists");
        //response.prettyPrint();
        playlists = new String[totalPlayList];
        for(int i = 0; i < playlists.length; i++) {
            playlists[i] = response.path("items[" + i + "].id");
        }
        for(String id : playlists) {
            System.out.println("PlayList Id" + id);
        }
        response = given()
                .accept(JSON)
                .contentType(JSON)
                .header("Authorization", token)
                .when().accept(JSON)
                .get("https://api.spotify.com/v1/playlists/" + playlists[0] + "/tracks");
        int totalTracks = response.path("total");
        trackId = new String[totalTracks];
        for(int i = 0; i < trackId.length; i++) {
            trackId[i] = response.path("items[" + i + "].track.uri"); //get uri of track
        }
        for(String track : trackId)
        {
            System.out.println("Tracks:" + track);
        }
        response = given()
                .accept(JSON)
                .contentType(JSON)
                .header("Authorization", token)
                .when().accept(JSON)
                .get("https://api.spotify.com/v1/playlists/" + playlists[0] + "/tracks");
        totalTracks = response.path("total");
        trackId = new String[totalTracks];
        for(int i = 0; i < trackId.length; i++) {
            trackId[i] = response.path("items[" + i + "].track.uri");
        }
        for(String track : trackId) {
            System.out.println("Tracks:" + track);
        }
        response = given()
                .accept(JSON)
                .contentType(JSON)
                .header("Authorization", token)
                .body("{\"uris\": [\"" + trackId[1] + "\"]}")
                .when()
                .delete("https://api.spotify.com/v1/playlists/" + playlists[0] + "/tracks/");
        System.out.println("Track Id is:" + response.path("snapshot_id"));
    }




}
