package za.ac.nwu.aws;

import org.springframework.stereotype.Service;
import za.ac.nwu.domain.dto.userDTO;
import java.sql.SQLException;

@Service
public interface userAWS
{
    userDTO addUser(userDTO userDTO) throws Exception;
    Boolean checkUserExists(Integer userID) throws Exception;
    Integer getUserID(String email) throws Exception;
    Boolean isUserValid(String email, String password) throws Exception;
}
