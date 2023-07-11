package itmo.project.controller;

import itmo.project.model.OtpRequest;
import itmo.project.model.UserCredential;
import itmo.project.service.UserCredentialService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthorizationController {
    private UserCredentialService service;

    @PostMapping("/user/add")
    public void addUser(@RequestBody UserCredential userCredential) {
        service.addUser(userCredential);
    }

    @PostMapping("/user/auth")
    public void authUser(@RequestBody UserCredential userCredential) {
        service.auth(userCredential);
    }

    @PostMapping("/otp/check")
    public void checkOtp(@RequestBody OtpRequest otpRequest, HttpServletResponse response) {
        boolean result = service.checkOtp(otpRequest);
        if (result) {
            response.setStatus(HttpServletResponse.SC_OK);

        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
