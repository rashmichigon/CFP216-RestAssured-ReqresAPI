package com.selenium.apitest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class ReqresAPI {
    @Test
    public void GetListUsersSuccess_ReturnsOK()
    {
        RestAssured.baseURI = "https://reqres.in/api/users?page=2";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "");
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());
    }
    @Test
    public void GetSingleUserSuccess_OK()
    {
        RestAssured.baseURI = "https://reqres.in/api/users/2";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "");
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());
    }
    @Test
    public void GetSingleUserNotFoundSuccess_OK()
    {
        RestAssured.baseURI = "https://reqres.in/api/users/23";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "");
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());
    }
    @Test
    public void GetListResourcesSuccess_OK()
    {
        RestAssured.baseURI = "https://reqres.in/api/unknown";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "");
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());
    }
    @Test
    public void GetSingleResourceSuccess_OK()
    {
        RestAssured.baseURI = "https://reqres.in/api/unknown/2";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "");
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());
    }
    @Test
    public void GetSingleResourceNotFoundSuccess_OK()
    {
        RestAssured.baseURI = "https://reqres.in/api/unknown/23";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "");
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());
    }

    @Test
    public void PostUsersSuccess_ReturnsOK()
    {
        Response response = given().accept("application/json")
                .body("{\n" +
                        "  \"name\": \"morpheus\",\n" +
                        "  \"job\": \"leader\",\n" +
                        "}")
                .when()
                .post("https://reqres.in/api/users");
        response.prettyPrint();
        response.then().assertThat().statusCode(201);
    }
    @Test
    public void PutUpdateUserSuccess_OK()
    {
        Response response = given().accept("application/json")
                .pathParam("name","morpheus")
                .pathParam("job","zion resident")
                .body("{\n" +
                        "  \"name\": \"morpheus\",\n" +
                        "  \"job\": \"zion resident\",\n" +
                        "}")
                .when()
                .put("https://reqres.in/api/users/{name}/{job}");

        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }
    @Test
    public void PatchUpdateUserSuccess_OK()
    {
        Response response = given().accept("application/json")
                .pathParam("name","morpheus")
                .pathParam("job","zion resident")
                .body("{\n" +
                        "  \"name\": \"morpheus\",\n" +
                        "  \"job\": \"zion resident\",\n" +
                        "}")
                .when()
                .patch("https://reqres.in/api/users/{name}/{job}");

        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }
    @Test
    public void DeleteUsersSuccess_ReturnsOK() throws SQLException
    {
        Response response = given().accept("application/json")
                .contentType("application/json")
                .when()
                .delete("https://reqres.in/api/users/2");
        response.prettyPrint();
        System.out.println(response.then().statusCode(204));
    }
    @Test
    public void PostRegisterSuccessfullUserSuccess_OK()
    {
        Response response = given().accept("application/json")
                .pathParam("email","eve.holt@reqres.in")
                .pathParam("password","pistol")
                .body("{\n" +
                        "  \"email\": \"eve.holt@reqres.in\",\n" +
                        "  \"password\": \"pistol\",\n" +
                        "}")
                        .when().post("https://reqres.in/api/register/{email}/{password}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }
    @Test
    public void PostRegisterUnsuccessfullSuccess_OK()
    {
        Response response = given().accept("application/json")
                .pathParam("email","sydney@fife")
                .body("{\n" +
                        "  \"email\": \"eve.holt@reqres.in\",\n" +
                        "}")
                .when().post("https://reqres.in/api/register/{email}");
        response.prettyPrint();
        response.then().assertThat().statusCode(400);
    }

    @Test
    public void LoginUserSuccess_ReturnOK()
    {
        Response response = given().header("accept","application/json")
                .queryParam("email","eve.holt@reqres.in")
                .queryParam("password","cityslicka")
                .when()
                .get("https://reqres.in/api/login?email&password");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }
    @Test
    public void LoginUserUnsuccessfullSuccess_ReturnOK()
    {
        Response response = given().header("accept","application/json")
                .queryParam("email","peter@klaven")
                .when()
                .post("https://reqres.in/api/login");
        response.prettyPrint();
        response.then().assertThat().statusCode(400);
    }
    @Test
    public void DelayedResponseSuccess_ReturnsOK()
    {
        RestAssured.baseURI = "https://reqres.in/api/users?delay=3";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "");
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());
    }



}


