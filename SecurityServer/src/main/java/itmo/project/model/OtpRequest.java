package itmo.project.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OtpRequest {
private String token;
private String login;
}
