package za.ac.nwu.aws;

import org.springframework.stereotype.Service;
import za.ac.nwu.domain.dto.imageDTO;
import za.ac.nwu.domain.persistence.imagePersistence;

@Service
public interface shareimageAWS
{
    String shareImage(imageDTO image, Integer sharedID) throws Exception;
    String deleteSharedImage(Integer sharedImageID, String imageName, Integer sharedID) throws Exception;
}
