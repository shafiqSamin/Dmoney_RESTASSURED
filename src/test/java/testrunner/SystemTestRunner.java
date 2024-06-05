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

public class SystemTestRunner extends Setup {

    @Test(priority = 1,description = "system can successfully deposit money to agent")
    public void addMoneyToAgent() throws IOException {
        String from_account = "System";
        String to_account = props.getProperty("agentPhone");
        String amount = "10000";

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
