package com.qa.TestClass;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Base.TestBase;
import com.qa.Client.RestClient;
import com.qa.data.Users;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PUTAPITest extends TestBase {
    private TestBase base;
    private RestClient client;
    private String serviceURL;
    private String APIURL;
    private String URl;
    private CloseableHttpResponse httpResponse;

    @BeforeMethod
    public void Setup() {
        base = new TestBase();
        serviceURL = prop.getProperty("URL");
        APIURL = prop.getProperty("PUTSERVICEURL");
        URl = serviceURL + APIURL;
    }

    @Test
    public void getAPIPUTMethod() throws IOException {
        client = new RestClient();
        HashMap<String, String> headermap = new HashMap<String, String>();
        headermap.put("Content-Type", "application/json");

        //JackSON API
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);//disable ObjectMapper checking for unknown properties
        Users users = new Users("morpheus", "Yellow Stone");

        //Object to JSON file:
        mapper.writeValue(new File("C:\\Users\\Deepak\\IdeaProjects\\RestAPIHTTPClient\\src\\main\\java\\com\\qa\\data\\Users.json"), users);

        //java object to json in String
        String usersJsonString = mapper.writeValueAsString(users);

        //Validate Response From API
        httpResponse = client.put(URl, usersJsonString, headermap);
        System.out.println(httpResponse);
        int statuscode = httpResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statuscode, RESPONSE_STATUS_200, "Status code is not Matched.");

        //b.JSON String
        String responseStirng = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");// Charcater format
        JSONObject Responsejson = new JSONObject(responseStirng);
        System.out.println("response jason from API" + Responsejson);

        //Json to Java Object.
        Users usersResobj = mapper.readValue(responseStirng, Users.class);
        System.out.println(usersResobj);

        Assert.assertTrue(users.getName().equals(usersResobj.getName()));
        Assert.assertTrue(users.getJob().equals(usersResobj.getJob()));
    }
}
