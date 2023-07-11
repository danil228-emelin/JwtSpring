package itmo.project.repository;

import itmo.project.model.UserCredential;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserCredential, Integer> {
    UserCredential findByLogin(String login);

@Modifying
@Query("update credential set token= :token where login= :login")
    void updateToken(String token,String login);
}
