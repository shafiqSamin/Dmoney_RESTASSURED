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

public class AgentTestRunner extends Setup {
    @Test(priority = 1,description = "Agent login successful")
    public void doLogin() throws IOException {
        UserController userController = new UserController();
        JsonPath jsonObj = userController.doLogin(props.getProperty("agentEmail"), props.getProperty("agentPassword"));

        String messageActual = jsonObj.get("message");
        String messageExpected = "Login successfully";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
    @Test(priority = 2, description = "Agent can deposit money successfully")
    public void depositMoneyToCustomer() throws IOException, ParseException {
        String from_account = props.getProperty("agentPhone");
        String to_account = props.getProperty("customer1Phone");
        String amount = "8000";

        UserController userController = new UserController();
        UserModel model = new UserModel();
        model.setFrom_account(from_account);
        model.setTo_account(to_account);
        model.setAmount(amount);
        JsonPath jsonObj = userController.depositToAgent(model);

        String messageActual = jsonObj.get("message");
        String messageExpected = "Deposit successful";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }

}
