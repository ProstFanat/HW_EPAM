package resources;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import org.apache.log4j.Logger;
import org.testng.ITestResult;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SlackIntegration {
    private static final Logger LOG = Logger.getLogger(SlackIntegration.class);
    private static String urlSlackWebHook = "https://hooks.slack.com/services/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";
    private static String channelName = "study";
    private static String botUserOAuthAccessToken = "xoxb-2777636683632-2756200412772-Y0r62EdNgO1nujmV0jMkZWgM";

    public void sendTestExecutionStatusToSlack(String message) throws Exception {
        try {
            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder.append(message);
            Payload payload = Payload.builder().channel(channelName).text(messageBuilder.toString()).build();

            WebhookResponse webhookResponse = Slack.getInstance().send(urlSlackWebHook, payload);
            webhookResponse.getMessage();
        } catch (IOException e) {
            System.out.println("Unexpected Error! WebHook:" + urlSlackWebHook);
        }
    }

    public String generateTestReport(List<ITestResult> allTests) {
        List<ITestResult> passedTests = allTests.stream().filter(test -> test.getStatus() == ITestResult.SUCCESS).collect(Collectors.toList());
        List<ITestResult> failedTests = allTests.stream().filter(test -> test.getStatus() == ITestResult.FAILURE).collect(Collectors.toList());
        List<ITestResult> otherStatusTests = allTests.stream().filter(test -> test.getStatus() != ITestResult.FAILURE && test.getStatus() != ITestResult.SUCCESS).collect(Collectors.toList());
        String buildStatus = passedTests.size() == allTests.size() ? "Success" : "Failed";
        return "Build status : " + buildStatus + "\n" +
                "Tests: " + allTests.size() + "\n" +
                "Passed: " + passedTests.size() + "; Failed: " + failedTests.size() + "; Other: " + otherStatusTests.size();
    }
}
