package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import setup.Setup;
import utils.UserModel;
import utils.Utils;

import java.io.IOException;


import static io.restassured.RestAssured.given;

public class UserController extends Setup {
    public UserController() throws IOException {
        initConfig();
    }

    public JsonPath doLoginAsAdmin(String email, String password) throws org.apache.commons.configuration.ConfigurationException {
        RestAssured.baseURI = props.getProperty("baseUrl");
        UserModel model = new UserModel();
        model.setEmail(email);
        model.setPassword(password);
        Response res = given().contentType("application/json").body(model).when().post("/user/login")
                .then().assertThat().statusCode(200).extract().response();

        System.out.println(res.asString());
        JsonPath jsonObj = res.jsonPath();
        String token = jsonObj.get("token");
        System.out.println(token);
        Utils.setEnvVar("token", token);
        return res.jsonPath();
    }

    public JsonPath doLogin(String email, String password) {
        RestAssured.baseURI = props.getProperty("baseUrl");
        UserModel model = new UserModel();
        model.setEmail(email);
        model.setPassword(password);
        Response res = given().contentType("application/json").body(model).when().post("/user/login")
                .then().assertThat().statusCode(200).extract().response();
        return res.jsonPath();
    }

    public JsonPath createUser(UserModel model) throws ConfigurationException {
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response res = given().contentType("application/json").body(model)
                .header("Authorization", props.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", props.getProperty("secretKey"))
                .when().post("user/create")
                .then().assertThat().statusCode(201).extract().response();
        System.out.println(res.asString());
        return res.jsonPath();

    }

    public JsonPath depositToAgent(UserModel model) {
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response res = given().contentType("application/json").body(model)
                .header("Authorization", props.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", props.getProperty("secretKey"))
                .when().post("transaction/deposit");
        System.out.println(res.asString());
        return res.jsonPath();
    }

    public JsonPath withdraw(UserModel model) {
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response res = given().contentType("application/json").body(model)
                .header("Authorization", props.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", props.getProperty("secretKey"))
                .when().post("transaction/withdraw");
        System.out.println(res.asString());
        return res.jsonPath();
    }

    public JsonPath sendMoney(UserModel model) {
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response res = given().contentType("application/json").body(model)
                .header("Authorization", props.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", props.getProperty("secretKey"))
                .when().post("transaction/sendMoney");
        System.out.println(res.asString());
        return res.jsonPath();
    }

    public JsonPath payment(UserModel model) {
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response res = given().contentType("application/json").body(model)
                .header("Authorization", props.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", props.getProperty("secretKey"))
                .when().post("transaction/payment");
        System.out.println(res.asString());
        return res.jsonPath();
    }

    public JsonPath checkBalance(String phoneNumber) {
        RestAssured.baseURI = props.getProperty("baseUrl");
        Response res = given().contentType("application/json")
                .header("Authorization", props.getProperty("token"))
                .header("X-AUTH-SECRET-KEY", props.getProperty("secretKey"))
                .when().get("transaction/balance/" + phoneNumber);
        System.out.println(res.asString());
        return res.jsonPath();
    }

}
