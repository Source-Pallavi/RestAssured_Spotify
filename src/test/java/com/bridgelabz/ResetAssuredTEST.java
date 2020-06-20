package com.bridgelabz;

import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ResetAssuredTEST
{
    private static String token = "";
    private String playlists[];
    private int totalPlayList;
    private String trackId[];
    String uid = "";
    String JSON = "application/json";

    //Before method
    @BeforeMethod
    public void setUp()
    {
        token="Bearer BQBoqlJrc5LRPhPHthUWE43ETWp_o1vC7riBabRwJDpljGYU3l-t6DsjcNsaDTKEEAs10XxjDxF535PqyVfAlOau-rrYiARKSPLRgB66VGkV63FsLp9fs9Roqk_8zCwpXUpwZDiLKYs8VOCr0aIuO_WomRPt0AHMLHqMaoCudfChTXQJErfj-WeGUctjKdzdwYVaoBzATXaWO7cnFgfNMlsnxsmRhxi_r4wBhZ1hjqht_MsMFjldCkriN0nLGCD4eCURbWj3epqKuqNCZ5ImJyI2jAYLpjfUXHdR";
        Response response = given().contentType(JSON).accept(JSON).header("Authorization", token).when().get("https://api.spotify.com/v1/me");
        uid = response.path("id");
        response.then().assertThat().statusCode(200);
        System.out.println(uid);
        /* to fetch the user id*/
    }
    @Test
    public void getUserDetail_Name() {
        String name = given().contentType(JSON).accept(JSON).header("Authorization", token).when().get("https://api.spotify.com/v1/me")
       .path("display_name"); //get user display name
        given().contentType(JSON).accept(JSON).header("Authorization", token).when().get("https://api.spotify.com/v1/me").then().assertThat().statusCode(200); //check for status code
        System.out.println(name);
    }

}
