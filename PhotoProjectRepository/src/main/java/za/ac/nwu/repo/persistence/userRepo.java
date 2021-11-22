package za.ac.nwu.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.domain.dto.userDTO;
import za.ac.nwu.domain.persistence.userPersistence;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface userRepo //extends JpaRepository<User, Integer>
{
    userDTO findEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "" + "UPDATE User usr SET usr.password = :newPassword" + " WHERE usr.email = :email")
    void changePassword(String newPassword, String email);
    @Query(value = "SELECT usr.userID from User usr")
    List<Integer> getAllUserID();
    @Query(value = "select usr.userID from User usr where usr.email = :email")
    Integer getUserID(String email);
    @Query(value = "select usr.password from User usr where usr.email = :email")
    String getPassword(String email);
}
