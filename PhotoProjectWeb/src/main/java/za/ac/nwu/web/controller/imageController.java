package za.ac.nwu.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.nwu.aws.imageAWS;
import za.ac.nwu.domain.dto.imageDTO;
import za.ac.nwu.domain.service.response;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:3000")
public class imageController
{
    private final imageAWS imageAWS;

    @Autowired
    public imageController(imageAWS imageAWS)
    {
        this.imageAWS = imageAWS;
    }

    //  UPLOAD IMAGE
    @PostMapping(
            path = "/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Uploads new image.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image was uploaded", response = response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = response.class),
            @ApiResponse(code = 404, message = "Not Found", response = response.class)
    })
    public ResponseEntity<String> uploadImage(@RequestParam("userID") Integer userID, @RequestParam("file") MultipartFile file) throws IOException
    {
        return new ResponseEntity<>(imageAWS.uploadImage(file, userID), HttpStatus.OK);
    }

    //  DELETE IMAGE
    @DeleteMapping(path = "/image/delete/{userID}/{imgName}")
    @ApiOperation(value = "Deletes image.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image was deleted", response = response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = response.class),
            @ApiResponse(code = 404, message = "Not Found", response = response.class)
    })
    public ResponseEntity<String> deleteFile(
            @PathVariable Integer userID,
            @PathVariable String imageName) throws Exception
    {
        return new ResponseEntity<>(imageAWS.deleteImage(userID, imageName), HttpStatus.OK);
    }

    //  DOWNLOAD IMAGE
    @GetMapping(path = "/image/download/{userID}/{imgName}")
    @ApiOperation(value = "Downloads image to user's PC.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image downloaded", response = response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = response.class),
            @ApiResponse(code = 404, message = "Not Found", response = response.class)
    })
    public ResponseEntity<byte[]> downloadImage(
            @PathVariable Integer userID,
            @PathVariable String imageName) throws IOException
    {
        ByteArrayOutputStream byteArrayOutputStream = imageAWS.downloadImage(imageName, userID);
        return ResponseEntity.ok()
                .contentType(contentType(imageName))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + imageName + "\"")
                .body(byteArrayOutputStream.toByteArray());
    }

    @GetMapping(path = "/image/view/{userID}/{imgName}")
    @ApiOperation(value = "Views image of a user.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image viewed", response = response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = response.class),
            @ApiResponse(code = 404, message = "Not Found", response = response.class)
    })
    public ResponseEntity<byte[]> viewImage(
            @PathVariable Integer userID,
            @PathVariable String imageName) throws IOException
    {
        ByteArrayOutputStream byteArrayOutputStream = imageAWS.downloadImage(imageName, userID);
        return ResponseEntity.ok()
                .contentType(contentType(imageName))
                .body(byteArrayOutputStream.toByteArray());
    }

    //    GET ALL OBJECTS
    @GetMapping(path = "/image/viewAll/{userID}")
    @ApiOperation(value = "Gets all images of a user.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Images views", response = response.class),
            @ApiResponse(code = 400, message = "Bad Request", response = response.class),
            @ApiResponse(code = 404, message = "Not Found", response = response.class)
    })
    public ResponseEntity<response<List<imageDTO>>> viewAllImages(@PathVariable("userID") Integer userID) throws Exception
    {
        List<imageDTO> images = imageAWS.getUserImages(userID);
        response<List<imageDTO>> response = new response<>(true,images);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private MediaType contentType(String imageName)
    {
        if(imageName.contains("png"))
        {
            return MediaType.IMAGE_PNG;
        }
        else if(imageName.contains("jpeg") || imageName.contains("jpg"))
        {
            return MediaType.IMAGE_JPEG;
        }
        else if(imageName.contains("gif"))
        {
            return MediaType.IMAGE_GIF;
        }
        else
        {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}
