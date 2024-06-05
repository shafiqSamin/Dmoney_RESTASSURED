package testrunner;

import controller.UserController;
import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.Setup;
import utils.UserModel;
import utils.Utils;

import java.io.IOException;

public class CustomerTestRunner extends Setup {
    @Test(priority = 1,description = "Customer login successful")
    public void doLogin() throws IOException, ConfigurationException {
        UserController userController = new UserController();
        JsonPath jsonObj = userController.doLogin(props.getProperty("customer1Email"), props.getProperty("customer1Password"));

        String messageActual = jsonObj.get("message");
        String messageExpected = "Login successfully";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
    @Test(priority = 2, description = "Customer can withdraw successfully from agent")
    public void withdrawMoneyToAgent() throws IOException {
        String from_account = props.getProperty("customer1Phone");
        String to_account = props.getProperty("agentPhone");
        String amount = "500";

        UserController userController = new UserController();
        UserModel model = new UserModel();
        model.setFrom_account(from_account);
        model.setTo_account(to_account);
        model.setAmount(amount);
        JsonPath jsonObj = userController.withdraw(model);

        String messageActual = jsonObj.get("message");
        String messageExpected = "Withdraw successful";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
    @Test(priority = 3, description = "Customer can send money successfully to another customer")
    public void sendMoneyTOAnotherCustomer() throws IOException {
        String from_account = props.getProperty("customer1Phone");
        String to_account = props.getProperty("customer2Phone");
        String amount = "2000";

        UserController userController = new UserController();
        UserModel model = new UserModel();
        model.setFrom_account(from_account);
        model.setTo_account(to_account);
        model.setAmount(amount);
        JsonPath jsonObj = userController.sendMoney(model);

        String messageActual = jsonObj.get("message");
        String messageExpected = "Send money successful";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
}
