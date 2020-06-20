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


    @BeforeMethod
    public void setUp()
    {
        token=
                "Bearer BQCUfUzDZipmQ2RC-W8LRapV929CxqFm4_0yKq2GV02YBnTa5CfKg2-O0Oxj9EiHNXsylFCYhgMrnSH3JRKVdrV6X52sfpgzw_YYNLhOLF7cOPo_Yw_mnMqai2VBQcWccNS8hri2_wk49Paweuyuf0EnhSE6wuDnafP2alxDy2paFAmNOjDtdSC4XBTJRifaVcLrQpo__-Xk518KBPrT2KMiz-EcPubNee46-ByiYGKH_Ksd-KqS6fLBFujl35jo1uRnCieipmsesYQf-8ugHAodT0HKKEcj17HV";
         response = given().contentType(JSON).accept(JSON).header("Authorization", token).when().get("https://api.spotify.com/v1/me");
        userID = response.path("id");
        response.then().assertThat().statusCode(200);
        System.out.println(userID);
        /* to fetch the user id*/
    }
    @Test
    public void getUserDetail_Name() {
        String name = response.path("display_name");
        response.then().assertThat().statusCode(200);
        System.out.println(name);
    }
/*get information of playlist*/
    @Test
    public void get_UserProfile() {
        response.path("id");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
    }
    /*to create play list*/
    @Test
    public void create_PlayListSpotify() {
         response = given().accept(JSON).contentType(JSON).header("Authorization", token)
                .body("{\"name\": \"Prad\"," +
                        "\"description\": \"New playlist description\"," +
                        "\"public\": false}").when().post(" https://api.spotify.com/v1/users/" + userID + "/playlists");
        System.out.println("PlayList Name:" + response.path("name"));
        response.then().assertThat().statusCode(201);
    }
    /*to find the total no of playlist is present*/
    @Test
    public void getPlayList() {
         response = given().accept(JSON).contentType(JSON).header("Authorization", token).when().get("https://api.spotify.com/v1/users/" + userID + "/playlists");
        response.then().assertThat().statusCode(200);
        System.out.println("Total no. of PlayList:" +  response.path("total"));
    }
}
