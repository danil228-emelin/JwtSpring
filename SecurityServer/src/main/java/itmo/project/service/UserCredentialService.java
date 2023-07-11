package itmo.project.service;

import itmo.project.model.OtpRequest;
import itmo.project.model.UserCredential;
import itmo.project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class UserCredentialService implements UserDetailsService {
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;
    private OtpGenerator generator;

    public void auth(UserCredential userCredential) {

        UserDetails userFromDB = loadUserByUsername(userCredential.getUsername());
        if (!passwordEncoder.matches(userCredential.getPassword(), userFromDB.getPassword())) {
            throw new BadCredentialsException("bad credentials");
        }
        createOtp(userCredential);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential credential = repository.findByLogin(username);
        if (credential == null) {
            throw new UsernameNotFoundException("something went wrong");
        }
        return credential;
    }

    private void createOtp(UserCredential credential) {
        String otp = generator.generateCode();
        repository.updateToken(otp, credential.getLogin());
    }

    public void addUser(UserCredential userCredential) {
        try {
            loadUserByUsername(userCredential.getUsername());
            throw new BadCredentialsException("bad credentials");
        } catch (UsernameNotFoundException exception) {
            userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
            repository.save(userCredential);
        }
    }

    public boolean checkOtp(OtpRequest request) {
        try {
            UserCredential userFromDB = repository.findByLogin(request.getLogin());
            return userFromDB.getToken().equals(request.getToken());
        } catch (UsernameNotFoundException ignored) {
        }
        return false;
    }
}
