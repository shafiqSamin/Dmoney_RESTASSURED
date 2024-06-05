package testrunner;

import controller.UserController;
import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.Setup;
import utils.UserModel;

import java.io.IOException;

public class Customer2TestRunner extends Setup {
    @Test(priority = 1,description = "Customer login successful")
    public void doLogin() throws IOException, ConfigurationException {
        UserController userController = new UserController();
        JsonPath jsonObj = userController.doLogin(props.getProperty("customer2Email"), props.getProperty("customer2Password"));

        String messageActual = jsonObj.get("message");
        String messageExpected = "Login successfully";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }



    @Test(priority = 2, description = "Payment successful to merchant")
    public void paymentToMerchant() throws IOException {
        String from_account = props.getProperty("customer2Phone");
        String to_account = props.getProperty("merchantPhone");
        String amount = "100";

        UserController userController = new UserController();
        UserModel model = new UserModel();
        model.setFrom_account(from_account);
        model.setTo_account(to_account);
        model.setAmount(amount);
        JsonPath jsonObj =  userController.payment(model);

        String messageActual = jsonObj.get("message");
        String messageExpected = "Payment successful";
        Assert.assertTrue(messageActual.contains(messageExpected));

    }

    @Test(priority = 3,description = "Customer can successfully check balance")
    public void checkBalance() throws IOException {
        UserController controller = new UserController();
        JsonPath jsonObj =  controller.checkBalance(props.getProperty("customer2Phone"));

        String messageActual = jsonObj.get("message");
        String messageExpected = "User balance";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
}
