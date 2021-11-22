package za.ac.nwu.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.aws.shareimageAWS;
import za.ac.nwu.domain.dto.imageDTO;
import za.ac.nwu.domain.service.response;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:3000")
public class shareimageController
{
    private final shareimageAWS sharedImageAWS;

    @Autowired
    public shareimageController(shareimageAWS sharedImageAWS)
    {
        this.sharedImageAWS = sharedImageAWS;
    }

    @PostMapping(path = "/sharedImage/shareImage")
    @ApiOperation(value = "Shares an image with another user.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image shared", response = response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = response.class),
            @ApiResponse(code = 404, message = "Not Found", response = response.class)
    })
    public ResponseEntity<String> shareImage(
            @RequestParam Integer sharedUserID,
            @RequestParam Integer imageid,
            @RequestParam String date,
            @RequestParam String imagelink,
            @RequestParam String name,
            @RequestParam double size,
            @RequestParam Integer user
    ) throws Exception
    {
        return new ResponseEntity<>(sharedImageAWS.shareImage(new imageDTO(imageid, imagelink, name, size, date, user), sharedUserID), HttpStatus.OK);
    }

    //  DELETE IMAGE
    @DeleteMapping(path = "/image/delete/{sharedImgID}/{sharedUserID}/{imgName}")
    @ApiOperation(value = "Deletes shared image.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image was deleted", response = response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = response.class),
            @ApiResponse(code = 404, message = "Not Found", response = response.class)
    })
    public ResponseEntity<String> deleteSharedImage(
            @PathVariable("sharedImageID") Integer sharedImageID,
            @PathVariable("sharedUserID") Integer sharedUserID,
            @PathVariable("imageName") String imageName) throws Exception
    {
        return new ResponseEntity<>(sharedImageAWS.deleteSharedImage(sharedImageID,imageName, sharedUserID), HttpStatus.OK);
    }
}
