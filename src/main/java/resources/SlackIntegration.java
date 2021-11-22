package resources;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;

import java.io.File;
import java.io.IOException;

public class SlackIntegration {
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
}
