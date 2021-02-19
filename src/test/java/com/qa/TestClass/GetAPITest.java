package com.qa.TestClass;

import com.qa.Base.TestBase;
import com.qa.Client.RestClient;
import com.qa.Util.TestUtil;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GetAPITest extends TestBase {

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
        APIURL = prop.getProperty("ServiceURL");
        URl = serviceURL + APIURL;
    }

    @Test
    public void getTestMethod() throws IOException {
        client = new RestClient();
        httpResponse = client.get(URl);

        // a.Status code
        int statuscode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("status Code" + statuscode);
        Assert.assertEquals(statuscode, RESPONSE_STATUS_200, "Status code is not Matched.");

        //b.JSON String
        String responseStirng = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");// Charcater format
        JSONObject Responsejson = new JSONObject(responseStirng);
        System.out.println("response jason from API" + Responsejson);

        //single value assertion.
        String perPageValue = TestUtil.getValueByJPath(Responsejson, "/per_page");
        Assert.assertEquals(Integer.parseInt(perPageValue), 6);// Interger is wrapper class.

        //get the value in JsonArray:
        String lastname = TestUtil.getValueByJPath(Responsejson, "/data[0]/last_name");
        String id = TestUtil.getValueByJPath(Responsejson, "/data[0]/id");
        String avatar = TestUtil.getValueByJPath(Responsejson, "/data[0]/avatar");
        String firstname = TestUtil.getValueByJPath(Responsejson, "/data[0]/first_name");
        String email = TestUtil.getValueByJPath(Responsejson, "/data[0]/email");

        System.out.println(lastname);
        System.out.println(id);
        System.out.println(avatar);
        System.out.println(firstname);
        System.out.println(email);

        // c.All Headers
        // How to verify Header
        Header[] headersarray = httpResponse.getAllHeaders();
        HashMap<String, String> allHeader = new HashMap<String, String>();
        for (Header header : headersarray) {
            allHeader.put(header.getName(), header.getValue());
        }
        System.out.println("Header Array" + allHeader);
    }

    @Test
    public void getAPITestwithHeaders() throws IOException {
        client = new RestClient();
        HashMap<String, String> headermap = new HashMap<String, String>();
        headermap.put("Content-Type", "application/json");
//        headermap.put("Username", "test");
//        headermap.put("Password", "test123");
//        headermap.put("Auth Token", "test23456");
        httpResponse = client.get(URl, headermap);

        // a.Status code
        int statuscode = httpResponse.getStatusLine().getStatusCode();
        System.out.println("status Code" + statuscode);
        Assert.assertEquals(statuscode, RESPONSE_STATUS_200, "Status code is not Matched.");

        //b.JSON String
        String responseStirng = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");// Charcater format
        JSONObject Responsejson = new JSONObject(responseStirng);
        System.out.println("response jason from API" + Responsejson);

        //single value assertion.
        String perPageValue = TestUtil.getValueByJPath(Responsejson, "/per_page");
        Assert.assertEquals(Integer.parseInt(perPageValue), 6);// Interger is wrapper class.
        JSONArray getArray = Responsejson.getJSONArray("data");
        Iterator<Object> it = getArray.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
//        for (int i = 0; i < getArray.length(); i++) {
//            JSONObject objects = getArray.getJSONObject(i);
//            Iterator key = objects.keys();
//            while (key.hasNext()) {
//                String k = key.next().toString();
//                System.out.println("Key : " + k + ", value : "
//                        + objects.getString(k));
//            }
//            // System.out.println(objects.toString());
//            System.out.println("-----------");
//
//        }

        //get the value in JsonArray:
//        String lastname = TestUtil.getValueByJPath(Responsejson, "/data[0]/last_name");
//        String id = TestUtil.getValueByJPath(Responsejson, "/data[0]/id");
//        String avatar = TestUtil.getValueByJPath(Responsejson, "/data[0]/avatar");
//        String firstname = TestUtil.getValueByJPath(Responsejson, "/data[0]/first_name");
//        String email = TestUtil.getValueByJPath(Responsejson, "/data[0]/email");
//
//        System.out.println(lastname);
//        System.out.println(id);
//        System.out.println(avatar);
//        System.out.println(firstname);
//        System.out.println(email);

        // c.All Headers
        // How to verify Header
        Header[] headersarray = httpResponse.getAllHeaders();
        HashMap<String, String> allHeader = new HashMap<String, String>();
        for (Header header : headersarray) {
            allHeader.put(header.getName(), header.getValue());
        }
        System.out.println("Header Array" + allHeader);
    }

}
