package za.ac.nwu.aws.impl;

//import com.amazonaws.services.s3.model.*;
//import javafx.embed.swing.DataFlavorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import com.amazonaws.AmazonClientException;
//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.imageDTO;
import za.ac.nwu.domain.persistence.bucketPersistence;
import za.ac.nwu.domain.persistence.imagePersistence;
import za.ac.nwu.domain.persistence.userPersistence;
import za.ac.nwu.repo.persistence.imageRepo;
import za.ac.nwu.repo.persistence.shareimageRepo;
import za.ac.nwu.aws.imageAWS;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Transactional
@Component("imageAWS")
public class imageAWSImpl
{
    private static final Logger LOGGER = LoggerFactory.getLogger(imageAWSImpl.class);

 //   private final AmazonS3 s3;
 //   private String bucketName = bucketPersistence.IMAGE.getBucketName();
    private final imageRepo imageRepository;
    private final shareimageRepo sharedImageRepository;

    @Autowired
    public imageAWSImpl(/*AmazonS3 s3*/ imageRepo imageRepository, shareimageRepo sharedImageRepository)
    {
        //this.s3 = s3;
        this.imageRepository = imageRepository;
        this.sharedImageRepository = sharedImageRepository;
    }

    //    UPLOAD IMAGE TO AWS ---> get userID from logged in user
    public String uploadImage(MultipartFile image, Integer userID) throws IOException
    {
        LOGGER.info("The input image is {} and the userID is {}", image, userID);
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", image.getContentType());
        metadata.put("Content-Length", String.valueOf(image.getSize()));
        //ObjectMetadata objectMetadata = mapMetadata(Optional.of(metadata));
        //String path = String.format("%s/%s", bucketName, userID);
        //String imageLink = path + "/" + image.getOriginalFilename();
        String imageName = image.getOriginalFilename();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date today = Calendar.getInstance().getTime();
        String imageDate = dateFormat .format(today);
        double imageSize = Math.round(image.getSize()/100)/10.0;
       // LOGGER.info("The image path: {} ", path);
       // LOGGER.info("The image link: {} ", imageLink);
        LOGGER.info("The image name: {} ", imageName);
        LOGGER.info("The image date: {} ", imageDate);
        LOGGER.info("The image size: {} ", imageSize);
        try
        {
            //s3.putObject(path, imageName, image.getInputStream(), objectMetadata);
            imageRepository.save(new image(imageDate /*imageLink*/, imageName, imageSize, userID)
            {
                @Override
                public ImageProducer getSource()
                {
                    return null;
                }
               // @Override
                public Graphics getGraphics()
                {
                    return null;
                }
                //@Override
                public Object getProperty(String name, ImageObserver observer)
                {
                    return null;
                }
            });
        }
        catch(IOException e)
        {
            throw new IllegalStateException(e);
        }
        return "Image :" + imageName + " uploaded to AWS";
    }

    public String deleteImage(Integer userID, String imageName) throws Exception {
        try{
            LOGGER.info("The image name is {} and the userID is {} ", imageName, userID);
            //String path = String.format("%s/%s", bucketName, userID);
            //String imageLink = path + "/" + imageName;
            String imageKey = userID + "/" + imageName;
            //LOGGER.info("The image path is {} ", path);
            //LOGGER.info("The image link is {} ", imageLink);
            LOGGER.info("The image key is {} ", imageKey);
//            DELETE SHARED IMAGES ASWELL
            //s3.deleteObject(bucketName, imageKey);
            //imageRepository.deleteLink(imageLink);
            return imageName + " has been permanently deleted";
        }catch (Exception e){
            throw new Exception("Image could not be deleted",e);
        }

    }

    //  DOWNLOAD IMAGE ---> add option for multi file download (array)
    /*public ByteArrayOutputStream downloadImage(String imageName, Integer userID) throws IOException
    {
        try
        {
            LOGGER.info("The image name is {} and the userID is {} ", imageName, userID);
            String imgKey = userID + "/" + imageName;
            //S3Object image = s3.getObject(new GetObjectRequest(bucketName, imgKey));
            //InputStream inputStream = image.getObjectContent();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int length;
            byte[] buffer = new byte[4096];
            while ((length = inputStream.read(buffer, 0, buffer.length)) != -1)
            {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            return byteArrayOutputStream;
        }
        catch (IOException ioe)
        {
            throw new IOException(ioe.getMessage());
        }
        catch (AmazonServiceException ase)
        {
            throw new AmazonServiceException(ase.getMessage());
        }
        catch (AmazonClientException ace)
        {
            throw new AmazonClientException(ace.getMessage());
        }
    }*/

    //@Override
    public List<String> listAllImages(Integer userID) throws Exception
    {
        try
        {
            LOGGER.info("The userID is {} ", userID);
            String prefix = userID + "/";
            List<String> names = new ArrayList<>();
            //ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withPrefix(prefix).withDelimiter("/");
            //ListObjectsV2Result listing = s3.listObjectsV2(req);
            /*for (S3ObjectSummary summary: listing.getObjectSummaries())
            {
                names.add(summary.getKey());
            }*/
            LOGGER.info("The output is: {}", names);
            return names;
        }
        catch (Exception e)
        {
            throw new Exception("Could not list all images");
        }
    }

   // @Override
    public List<imageDTO> getAllUserImage(Integer userID) throws Exception
    {
        try
        {
            LOGGER.info("The userID is {} ", userID);
            LOGGER.info("The output is: {}",imageRepository.findAllByUserID(new userPersistence(userID)) );
            return imageRepository.findAllByUserID(new userPersistence(userID));
        }
        catch (Exception e)
        {
            throw new Exception("Could not get all user images");
        }
    }

    /*private ObjectMetadata mapMetadata(Optional<Map<String, String>> optionalMetadata)
    {
        ObjectMetadata metadata = new ObjectMetadata();
        optionalMetadata.ifPresent(map ->
        {
            if(!map.isEmpty())
            {
                map.forEach(metadata::addUserMetadata);
            }
        });
        return metadata;
    }*/
}
