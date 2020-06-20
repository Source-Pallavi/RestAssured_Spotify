package com.bridgelabz;

import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ResetAssuredTEST
{Response response;
    private static String token = "";
    private String playlists[];
    private int totalPlayList;
    private String trackId[];
    String uid = "";
    String JSON = "application/json";


    @BeforeMethod
    public void setUp()
    {
        token=
                "Bearer BQC80Va9aw91gG8eSDlAa_5n9RTkxgkz_vyvwKORpqShLSJJ-XJdEillqmkC7TVkUZJNGIOLuME8Nsch-bt6yJ_mWY72ONYoUMd3mwrDLRcoIbnykv3WAcnRE7glRuin7NKPSDt01fpqMk1C8xEv20RhKPzzujPD_frqzj_dWfoyezhTlbufFZXawfN-mWpDaA5cyLjfxldgILKtTb1b1me65SWtiCVNSXFfE00Xjd7UqswmZZyzFnXuACyTOTSFhjF9yWzTy5oAH_Ie5ECb_q-xzQisaXEyHXp1";
         response = given().contentType(JSON).accept(JSON).header("Authorization", token).when().get("https://api.spotify.com/v1/me");
        uid = response.path("id");
        response.then().assertThat().statusCode(200);
        System.out.println(uid);
        /* to fetch the user id*/
    }
    @Test
    public void getUserDetail_Name() {
        String name = response.path("display_name");
        response.then().assertThat().statusCode(200);
        System.out.println(name);
    }
    @Test
    public void get_UserProfile() {
        response.path("id");
        response.then().assertThat().statusCode(200);//check for status code
        response.prettyPrint(); //print current user profile
    }
}
