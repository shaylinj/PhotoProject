package za.ac.nwu.domain.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "project_schema",name = "shared")
public class shareimagePersistence
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SHARED_IMAGE_ID")
    private Integer sharedImageID;

    @Column(name = "IMAGE_NAME")
    private String imgName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID_SHARER")
    @JsonBackReference
    private userPersistence userIDSharer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID_SHARED")
    @JsonBackReference
    private userPersistence userIDShared;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IMAGE_ID")
    @JsonBackReference
    private imagePersistence imageID;

    public shareimagePersistence() {
    }

    public shareimagePersistence(Integer sharedImageID) {
        this.sharedImageID = sharedImageID;
    }

    public shareimagePersistence(Integer sharedImageID, String imgName, userPersistence userIDSharer, userPersistence userIDShared, imagePersistence imageID) {
        this.sharedImageID = sharedImageID;
        this.imgName = imgName;
        this.userIDSharer = userIDSharer;
        this.userIDShared = userIDShared;
        this.imageID = imageID;
    }

    public shareimagePersistence(Integer sharedImageID, String imgName, Integer userIDShared, Integer userIDSharer, Integer imageID) {
        this.sharedImageID = sharedImageID;
        this.imgName = imgName;
        this.userIDShared = new userPersistence(userIDShared);
        this.userIDSharer = new userPersistence(userIDSharer);
        this.imageID = new imagePersistence(imageID);
    }

    public shareimagePersistence(String imgName, Integer userIDShared, Integer userIDSharer, Integer imageID) {
        this.imgName = imgName;
        this.userIDShared = new userPersistence(userIDShared);
        this.userIDSharer = new userPersistence(userIDSharer);
        this.imageID = new imagePersistence(imageID);
    }

    public shareimagePersistence(String imgName, userPersistence userIDSharer, userPersistence userIDShared, imagePersistence imageID) {
        this.imgName = imgName;
        this.userIDSharer = userIDSharer;
        this.userIDShared = userIDShared;
        this.imageID = imageID;
    }

    public Integer getSharedImageID() {
        return sharedImageID;
    }

    public void setSharedImageID(Integer sharedImageID) {
        this.sharedImageID = sharedImageID;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public userPersistence getUserIDSharer() {
        return userIDSharer;
    }

    public void setUserIDSharer(userPersistence userIDSharer) {
        this.userIDSharer = userIDSharer;
    }

    public userPersistence getUserIDShared() {
        return userIDShared;
    }

    public void setUserIDShared(userPersistence userIDShared) {
        this.userIDShared = userIDShared;
    }

    public imagePersistence getImageID() {
        return imageID;
    }

    public void setImageID(imagePersistence imageID) {
        this.imageID = imageID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        shareimagePersistence that = (shareimagePersistence) o;
        return Objects.equals(sharedImageID, that.sharedImageID) && Objects.equals(imgName, that.imgName) && Objects.equals(userIDSharer, that.userIDSharer) && Objects.equals(userIDShared, that.userIDShared) && Objects.equals(imageID, that.imageID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sharedImageID, imgName, userIDSharer, userIDShared, imageID);
    }

    @Override
    public String toString() {
        return "SharedImage{" +
                "sharedImageID=" + sharedImageID +
                ", imgName='" + imgName + '\'' +
                ", userIDSharer=" + userIDSharer +
                ", userIDShared=" + userIDShared +
                ", imageID=" + imageID +
                '}';
    }
}
