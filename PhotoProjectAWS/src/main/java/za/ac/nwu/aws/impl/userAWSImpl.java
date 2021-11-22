package za.ac.nwu.aws.impl;

//import com.amazonaws.services.s3.AmazonS3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.userDTO;
import za.ac.nwu.repo.persistence.userRepo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Transactional
@Component("UserAWS")

public class userAWSImpl
{
    private static final Logger LOGGER = LoggerFactory.getLogger(userAWSImpl.class);

    //private final AmazonS3 s3;
    //private String bucketName = BucketName.IMAGE.getBucketName();
    private userRepo userRepository;
    //private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public userAWSImpl(/*AmazonS3 s3,*/ userRepo userRepository /*BCryptPasswordEncoder passwordEncoder*/) {
        //this.s3 = s3;
        this.userRepository = userRepository;
        //this.passwordEncoder = passwordEncoder;
    }

    //    ADD NEW USER
    public userDTO addUser(userDTO userDTO) throws Exception
    {
        try
        {
            LOGGER.info("The input is {}", userDTO);
            if(null == userDTO.getFirstName())
            {
                userDTO.setUserID(0);
                userDTO.setFirstName("Name");
                userDTO.setLastName("Surname");
                userDTO.setEmail("email@gmail.com");
                userDTO.setPassword("123456789");
            }
            String password = userDTO.getPassword();
            //String encryptedPassword = passwordEncoder.encode(password);
            //userDTO.setPassword(encryptedPassword);
            //userRepository.save(userDTO.getUser());
//            delete images from s3 folder
            LOGGER.info("The output object is {}", userDTO.getUser());
            return userDTO;
        }
        catch (Exception e)
        {
            throw new Exception("User could not be added",e);
        }

    }

    public Boolean checkUserExists(Integer userID) throws Exception
    {
        try
        {
            Boolean userExists = false;
            List<Integer> users = userRepository.getAllUserID();
            LOGGER.info("The users are {}", users);
            for(Integer user: users){
                if(Objects.equals(userID, user))
                {
                    userExists = true;
                }
            }
            LOGGER.info("The output is {}", userExists);
            return userExists;
        }
        catch(Exception e)
        {
            throw new Exception("Users could not be obtained",e);
        }

    }

    public Integer getID(String email) throws Exception
    {
        try
        {
            return userRepository.getUserID(email);
        }
        catch (Exception e)
        {
            throw new Exception("Users could not be obtained",e);
        }
    }

    /*public Boolean isUserValid(String email, String password) throws Exception
    {
        try
        {
            LOGGER.info("The email is {}, and the password is {}", email,password);
            Boolean valid;
            String validPW = userRepository.getPassword(email);
            String encryptPassword = passwordEncoder.encode(password);
            LOGGER.info("The valid password is {}, and the provided password is {}", validPW,encryptPassword);
            if(validPW == encryptPassword)
            {
                valid = true;
            }
            else
            {
                valid = false;
            }
            LOGGER.info("The output is {}", valid);
            return valid;
        }
        catch (Exception ex)
        {
            throw new Exception("User credentials incorrect");
        }
    }*/
}
