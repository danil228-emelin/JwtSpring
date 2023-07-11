package itmo.project.config;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OtpAuthenticationProvider implements AuthenticationProvider {

    private AuthenticationServerProxy proxy;

    @Override
    public Authentication authenticate(Authentication authentication) {
        String username = authentication.getName();
        String code = String.valueOf(authentication.getCredentials());
        boolean result = proxy.sendOTP(username, code);
        if (result) {
            return new OtpAuthentication(username, code);
        } else {
            throw new BadCredentialsException("Bad credentials.");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OtpAuthentication.class.isAssignableFrom(aClass);
    }
}
