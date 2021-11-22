package za.ac.nwu.domain.persistence;

public class bucketPersistence
{
//    IMAGE("photoapp");

    private final String bucketName;

    bucketPersistence(String bucketName)
    {
        this.bucketName = bucketName;
    }
    public String getBucketName()
    {
        return bucketName;
    }
}
