package testrunner;

import com.github.javafaker.Faker;
import controller.UserController;
import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.UserModel;
import utils.Utils;

import java.io.IOException;

public class LoginTestRunner {
    @Test(priority = 1,description = "Admin can login successfully")
    public void doLogin() throws IOException, javax.naming.ConfigurationException, ConfigurationException {
        UserController userController = new UserController();
        JsonPath jsonObj = userController.doLoginAsAdmin("salman@roadtocareer.net","1234");
        String messageActual = jsonObj.get("message");
        String messageExpected = "Login successfully";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }

}
