package za.ac.nwu.aws.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.imageDTO;
import za.ac.nwu.domain.persistence.shareimagePersistence;
import za.ac.nwu.repo.persistence.imageRepo;
import za.ac.nwu.repo.persistence.shareimageRepo;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Transactional
@Component("SharedImageAWS")
public class shareimageAWSImpl
{
    private static final Logger LOGGER = LoggerFactory.getLogger(shareimageAWSImpl.class);

    //private final AmazonS3 s3;
    //private String bucketName = BucketName.IMAGE.getBucketName();
    private final shareimageRepo shareImageRepository;
    private final imageRepo imageRepository;

    @Autowired
    public shareimageAWSImpl(/*AmazonS3 s3,*/ shareimageRepo sharedImageRepository, imageRepo imageRepository) {
        //this.s3 = s3;
        this.shareImageRepository = sharedImageRepository;
        this.imageRepository = imageRepository;
    }

    public String shareImage(imageDTO image, Integer sharedID) throws Exception
    {
        try
        {
            shareimagePersistence shareImage = new shareimagePersistence(image.getName(), sharedID, image.getUserID(), image.getImageID());
            //String fromBucket = bucketName + "/" + image.getUserID();
            //String toBucket = bucketName + "/" + sharedID;
            String key=  image.getName();
            LOGGER.info("The image provided is {}, the userID of the user that the image is shared with is {}, from bucket: {}, to bucket: {}", image, sharedID /*fromBucket, toBucket*/);
            image.setImageID(null);
            image.setUserID(sharedID);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date today = Calendar.getInstance().getTime();
            image.setDate(dateFormat .format(today));
            //image.setLink(bucketName + "/" + sharedID + "/" + image.getName());
            //imageRepository.save(new Image(image));
            //s3.copyObject(fromBucket, key, toBucket, key);
            //shareimageRepo.save(shareImage);
            LOGGER.info("The output object is {}", shareImage);
            return "Image shared";
        }
        catch (Exception e)
        {
            throw new Exception("Image could not be shared", e);
        }

    }

    public String deleteSharedImage(Integer sharedImageID, String imageName, Integer sharedID) throws Exception
    {
        try
        {
            LOGGER.info("The shared image ID provided is {}, the userID of the user that the image is shared with is {} and the name" + " of the shared the image is {}", sharedImageID, sharedID, imageName);
            //String toBucket = bucketName + "/" + sharedID;
            //s3.deleteObject(toBucket, imageName);
            //shareimageRepo.deleteId(sharedImageID);
            return "Image deleted";
        }
        catch (Exception e)
        {
            throw new Exception("Image could not be deleted", e);
        }
    }
}
