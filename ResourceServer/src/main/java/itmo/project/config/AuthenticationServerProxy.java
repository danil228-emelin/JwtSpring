package itmo.project.config;

import itmo.project.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AuthenticationServerProxy {
    private RestTemplate restTemplate;
    @Value("${auth.server.base.url}")
    private String baseUrl;

    public void sendAuth(String username, String password) {
        String url = baseUrl + "/user/auth";
        User body = new User();
        body.setPassword(password);
        body.setUsername(username);
        var request = new HttpEntity<>(body);
        restTemplate.postForEntity(url, request, Void.class);
    }

    public boolean sendOTP(String username, String code) {
        String url = baseUrl + "/otp/check";
        var body = new User();
        body.setUsername(username);
        body.setToken(code);
        var request = new HttpEntity<>(body);
        var response = restTemplate.postForEntity(url, request, Void.class);
        return response.getStatusCode().equals(HttpStatus.OK);
    }
}
