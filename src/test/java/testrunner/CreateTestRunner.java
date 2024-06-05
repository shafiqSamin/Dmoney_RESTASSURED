package testrunner;

import com.github.javafaker.Faker;
import controller.UserController;
import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.UserModel;
import utils.Utils;

import java.io.IOException;

public class CreateTestRunner {
    @Test(priority = 1 , description = "Admin can create first user successfully")
    public void createUser() throws IOException, ConfigurationException, ParseException {
        Faker faker = new Faker();
        UserController userController = new UserController()    ;
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = faker.number().digits(4);
        String phone_number = "017"+faker.phoneNumber().subscriberNumber(8);
        String nid = faker.number().digits(8);
        String role = "Customer";

        UserModel model = new UserModel();
        model.setName(name);
        model.setEmail(email);
        model.setPassword(password);
        model.setPhone_number(phone_number);
        model.setNid(nid);
        model.setRole(role);

        JsonPath jsonObj = userController.createUser(model);
        String Email = jsonObj.get("user.email");
        String Phone = jsonObj.get("user.phone_number");
        String Password = jsonObj.get("user.password");

        Utils.setEnvVar("customer1Email", Email);
        Utils.setEnvVar("customer1Phone", Phone);
        Utils.setEnvVar("customer1Password", Password);
        Utils.saveUsers(model);

        String messageActual = jsonObj.get("message");
        String messageExpected = "User created";
        Assert.assertTrue(messageActual.contains(messageExpected));

    }
    @Test(priority = 2 , description = "Admin can create second user successfully")
    public void createUser2() throws IOException, ConfigurationException, ParseException {
        Faker faker = new Faker();
        UserController userController = new UserController();
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = faker.number().digits(4);
        String phone_number = "017"+faker.phoneNumber().subscriberNumber(8);
        String nid = faker.number().digits(8);
        String role = "Customer";

        UserModel model = new UserModel();
        model.setName(name);
        model.setEmail(email);
        model.setPassword(password);
        model.setPhone_number(phone_number);
        model.setNid(nid);
        model.setRole(role);
        JsonPath jsonObj = userController.createUser(model);
        String Email = jsonObj.get("user.email");
        String Phone = jsonObj.get("user.phone_number");
        String Password = jsonObj.get("user.password");

        Utils.setEnvVar("customer2Email", Email);
        Utils.setEnvVar("customer2Phone", Phone);
        Utils.setEnvVar("customer2Password", Password);
        Utils.saveUsers(model);

        String messageActual = jsonObj.get("message");
        String messageExpected = "User created";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
    @Test(priority = 3 , description = "Admin can create agent successfully")
    public void createAgent() throws IOException, ConfigurationException, ParseException {
        Faker faker = new Faker();
        UserController userController = new UserController();
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = faker.number().digits(4);
        String phone_number = "017"+faker.phoneNumber().subscriberNumber(8);
        String nid = faker.number().digits(8);
        String role = "Agent";

        UserModel model = new UserModel();
        model.setName(name);
        model.setEmail(email);
        model.setPassword(password);
        model.setPhone_number(phone_number);
        model.setNid(nid);
        model.setRole(role);
        JsonPath jsonObj = userController.createUser(model);
        String Email = jsonObj.get("user.email");
        String Phone = jsonObj.get("user.phone_number");
        String Password = jsonObj.get("user.password");

        Utils.setEnvVar("agentEmail", Email);
        Utils.setEnvVar("agentPhone", Phone);
        Utils.setEnvVar("agentPassword", Password);
        Utils.saveUsers(model);

        String messageActual = jsonObj.get("message");
        String messageExpected = "User created";
        Assert.assertTrue(messageActual.contains(messageExpected));

    }@Test(priority = 4 , description = "Admin can create merchant successfully")
    public void createMerchant() throws IOException, ConfigurationException, ParseException {
        Faker faker = new Faker();
        UserController userController = new UserController();
        String name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = faker.number().digits(4);
        String phone_number = "017"+faker.phoneNumber().subscriberNumber(8);
        String nid = faker.number().digits(8);
        String role = "Merchant";

        UserModel model = new UserModel();
        model.setName(name);
        model.setEmail(email);
        model.setPassword(password);
        model.setPhone_number(phone_number);
        model.setNid(nid);
        model.setRole(role);
        JsonPath jsonObj = userController.createUser(model);
        String Phone = jsonObj.get("user.phone_number");


        Utils.setEnvVar("merchantPhone", Phone);
        Utils.saveUsers(model);

        String messageActual = jsonObj.get("message");
        String messageExpected = "User created";
        Assert.assertTrue(messageActual.contains(messageExpected));

    }
}
