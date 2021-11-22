package za.ac.nwu.aws;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import za.ac.nwu.domain.dto.imageDTO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public interface imageAWS
{
    String uploadImage(MultipartFile image, Integer userID) throws IOException;
    String deleteImage(Integer userID, String imageName) throws Exception;
    ByteArrayOutputStream downloadImage(String imageName, Integer userID) throws IOException;
    List<String> listImages(Integer userID) throws Exception;
    List<imageDTO> getUserImages(Integer userID) throws Exception;
    String updateMetadata(Integer userID, String oldName, String newName);
}
