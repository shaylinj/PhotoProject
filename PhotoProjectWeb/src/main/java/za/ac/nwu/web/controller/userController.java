package za.ac.nwu.web.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.aws.userAWS;
import za.ac.nwu.domain.dto.userDTO;
import za.ac.nwu.domain.service.response;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:3000")
public class userController
{
    private userAWS userAWS;

    @Autowired
    public userController(userAWS userAWS)
    {
        this.userAWS = userAWS;
    }

    @GetMapping(path = "/")
    public String login()
    {
        return "login";
    }

    @PostMapping(path = "/user/add")
    @ApiOperation(value = "Adds new user to the system.", notes = "Adds new user to the system.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was added", response = response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = response.class),
            @ApiResponse(code = 404, message = "Not Found", response = response.class)
    })
    public ResponseEntity<userDTO> addUser(@ModelAttribute userDTO userDTO) throws Exception
    {
        return new ResponseEntity<>(userAWS.addUser(userDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/user/exists/{userID}")
    @ApiOperation(value = "Checks if a user exists.", notes = "Checks if a user exists")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All user IDs displayed", response = response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = response.class),
            @ApiResponse(code = 404, message = "Not Found", response = response.class)
    })
    public ResponseEntity<response<Boolean>> checkUserExists(@PathVariable("userID") Integer userID) throws Exception
    {
        Boolean exists = userAWS.checkUserExists(userID);
        response<Boolean> response = new response<>(true,exists);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/user/userID/{email}")
    @ApiOperation(value = "Gets userID based on email provided.", notes = "Gets userID based on email provided.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User ID provided", response = response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = response.class),
            @ApiResponse(code = 404, message = "Not Found", response = response.class)
    })
    public ResponseEntity<response<Integer>> getUserID(@PathVariable("email") String email) throws Exception
    {
        Integer userID = userAWS.getUserID(email);
        response<Integer> response = new response<>(true,userID);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/user/validUser/{email}/{password}")
    @ApiOperation(value = "Checks if user is valid.", notes = "Checks if user is valid.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User validadted", response = response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = response.class),
            @ApiResponse(code = 404, message = "Not Found", response = response.class)
    })
    public ResponseEntity<response<Boolean>> validUser(
            @PathVariable("email") String email,
            @PathVariable("password") String password) throws Exception
    {
        Boolean valid = userAWS.isUserValid(email,password);
        response<Boolean> response = new response<>(true,valid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
