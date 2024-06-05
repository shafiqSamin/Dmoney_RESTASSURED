package setup;

import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Setup {
        public Properties props;
        FileInputStream file;
        @BeforeTest
        public void initConfig() throws IOException {
            props = new Properties();
            file = new FileInputStream("./src/test/resources/config.properties");
            props.load(file);
        }
}
