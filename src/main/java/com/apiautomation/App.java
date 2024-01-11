package com.apiautomation;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
            RestAssured.baseURI = "https://reqres.in/";

            // Perform API request and get the response
            Response response = RestAssured.given()
                    .param("paramName", "paramValue")
                    .get("api/users?page=2");

            // Validate API response
            int statusCode = response.getStatusCode();
            // Perform assertions and validations on the response
            System.out.println("API Status Code: " + response.asPrettyString());

    }  }