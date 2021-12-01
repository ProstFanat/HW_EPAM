package resources;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.*;
import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.IClass;
import org.testng.ITestContext;
import org.testng.ITestResult;
import com.codepine.api.testrail.TestRail.Projects;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestRailIntegration {
    private static final Logger LOG = Logger.getLogger(TestRailIntegration.class);

    private TestRail client;
    private final static String endPoint = ConfProperties.getProperty("TEST_RAIL_ENDPOINT");
    private final static String username = ConfProperties.getProperty("TEST_RAIL_USER_NAME");
    private final static String password = ConfProperties.getProperty("TEST_RAIL_PASSWORD");
    private static int suiteId;
    public static Run run;

    public TestRail createTestRailInstance() {
        LOG.info("Creating new test Instance");
        if (client == null) {
            client = TestRail.builder(endPoint, username, password).build();
        }
        return client;
    }


    public TestRailIntegration createRun() throws ParseException {
        LOG.info("Creating new test run");
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyy kk mm s");
        Date date = new Date();
        String dateString = format.format(date);
        String runName = "Automation " + dateString;
        try{
            run = new Run();
            run = client.runs().add(Integer.parseInt(ConfProperties.getProperty("TEST_RAIL_PROJECT_ID")), run.setSuiteId(getSuiteId()).setName(runName).setIncludeAll(false)).execute();
            setRun(run);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return this;
    }

    public TestRailIntegration updateRun(List<Integer> testIdList){
        LOG.info("Adding tests to test run");
        try{
            getRun().setCaseIds(testIdList);
            client.runs().update(this.getRun()).execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return this;
    }

    public TestRailIntegration addResults(List<ITestResult> resultList, List<Integer> testIdList){
        LOG.info("Update test results in test run");
        for (int i = 0 ; i < testIdList.size(); i++) {
            try {
                if (null != client) {
                    List customResultFields = client.resultFields().list().execute();

                    client.results().addForCase(getRun().getId(),  testIdList.get(i),
                            new Result().setStatusId(getStatusIdFromTestResult(resultList.get(i))),
                            customResultFields).execute();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public void closeRun(){
        LOG.info("Closing test run");
        try{
            client.runs().close(getRun().getId()).execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setRun(Run run) {
        this.run = run;
    }

    public Run getRun() {
        return run;
    }

    public static int getSuiteId() {
        return suiteId;
    }
    public void setSuiteId(int suiteId) {
        this.suiteId = suiteId;
    }

    private int getStatusIdFromTestResult(ITestResult iTestResult){
        if(iTestResult.getStatus() == ITestResult.SUCCESS){
            return  1;
        } else {
            return  5;
        }
    }


}
