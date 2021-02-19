package com.qa.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    public Properties prop;
    public int RESPONSE_STATUS_200=200;
    public int RESPONSE_STATUS_201=201;
    public int RESPONSE_STATUS_500=500;
    public int RESPONSE_STATUS_400=400;
    public int RESPONSE_STATUS_401=401;


    public TestBase() {
        try {
            prop = new Properties();
            System.out.println(System.getProperty("user.dir"));
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/qa/ConfigFile/config.properties");
            prop.load(ip);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
