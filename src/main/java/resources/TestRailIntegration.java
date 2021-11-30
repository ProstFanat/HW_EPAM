package resources;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.IClass;
import org.testng.ITestResult;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TestRailIntegration {
    private static final Logger LOG = Logger.getLogger(TestRailIntegration.class);

    public void postTestResult(ITestResult result) throws APIException, IOException, NoSuchMethodException {
        APIClient client = new APIClient("https://adrianhw.testrail.io/");
        client.setUser("adrianboyshuk2001@gmail.com");
        client.setPassword("Test100955%");

        String TestID = null;
        IClass obj = result.getTestClass();
        Class newobj = obj.getRealClass();
        Method testMethod = newobj.getMethod(result.getName());
        if (testMethod.isAnnotationPresent(TestRailAnnotations.UseAsTestRailId.class))
        {
            TestRailAnnotations.UseAsTestRailId useAsTestName = testMethod.getAnnotation(TestRailAnnotations.UseAsTestRailId.class);
            TestID = Integer.toString(useAsTestName.testRailId());
            LOG.info("Test Rail ID = " + TestID);
        }

        Map data = new HashMap();
        if(result.getStatus() == ITestResult.SUCCESS){
            data.put("status_id", 1);
            data.put("comment", "This test worked fine!");
            LOG.info(String.format("Test %s - passed", TestID));
        } else if(result.getStatus() == ITestResult.FAILURE){
            data.put("status_id", 5);
            data.put("comment", "This test FAILED");
            LOG.info(String.format("Test %s - failed", TestID));
        }
        JSONObject r = (JSONObject) client.sendPost(String.format("add_result_for_case/%s/1",TestID), data);
    }


}
