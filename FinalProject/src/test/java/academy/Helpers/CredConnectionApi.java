package academy.Helpers;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CredConnectionApi {

    public HttpResponse makeConnection() throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("https://app.qa.axa.epm-rtc.projects.epam.com/oauth/token");
        post.setHeader("Authorization", "Basic d2ViYXBwOg==");
        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("password", "Staging@Axa"));
        pairs.add(new BasicNameValuePair("username", "selenium_chrome"));
        pairs.add(new BasicNameValuePair("grant_type", "password"));
        pairs.add(new BasicNameValuePair("scope", "deltix.axa.user"));
        post.setEntity(new UrlEncodedFormEntity(pairs));
        return httpClient.execute(post);
    }

    public String getToken(HttpResponse response) throws IOException {
        String token = EntityUtils.toString(response.getEntity());
        token = token.substring(token.indexOf(":") + 2);
        token = token.substring(0, token.indexOf("\""));
        return token;
    }

    public Header getAuthorization(String token) {
        return new BasicHeader("Authorization", "bearer " + token);
    }
}
