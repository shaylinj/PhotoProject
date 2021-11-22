package za.ac.nwu.domain.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import za.ac.nwu.domain.dto.imageDTO;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(schema = "project_schema",name = "images")
public class imagePersistence
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "imageID")
    private Integer imageID;

    @Column(name = "imagelink")
    private String imagelink;

    @Column(name = "imgname")
    private String name;

    @Column(name = "size")
    private double size;

    @Column(name = "img_date")
    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    @JsonBackReference
    private userPersistence userID;

    @OneToMany(targetEntity = shareimagePersistence.class, fetch = FetchType.LAZY,mappedBy = "imageID")
    @JsonManagedReference
    private Set<shareimagePersistence> sharedImage;

    public imagePersistence()
    {
    }

    public imagePersistence(String imagelink)
    {
        this.imagelink = imagelink;
    }

    public imagePersistence(Integer imageID)
    {
        this.imageID = imageID;
    }

    public imagePersistence(String date, String imagelink, String name, double size,  Integer userID)
    {
        this.imagelink = imagelink;
        this.name = name;
        this.size = size;
        this.date = date;
        this.userID = new userPersistence(userID);
    }

    public imagePersistence(imageDTO imageDTO)
    {
        this.imagelink = imageDTO.getLink();
        this.name = imageDTO.getName();
        this.size = imageDTO.getSize();
        this.date = imageDTO.getDate();
        this.userID = new userPersistence(imageDTO.getUserID());
    }

    public imagePersistence(String imagelink, userPersistence userID)
    {
        this.imagelink = imagelink;
        this.userID = userID;
    }

    public Integer getImageID()
    {
        return imageID;
    }

    public void setImageID(Integer imageID)
    {
        this.imageID = imageID;
    }

    public String getLink()
    {
        return imagelink;
    }

    public void setLink(String link)
    {
        this.imagelink = imagelink;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getSize()
    {
        return size;
    }

    public void setSize(double size)
    {
        this.size = size;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public userPersistence getUserID()
    {
        return userID;
    }

    public void setUserID(userPersistence userID)
    {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        imagePersistence image = (imagePersistence) o;
        return Double.compare(image.size, size) == 0 && Objects.equals(imageID, image.imageID) &&
                Objects.equals(imagelink, image.imagelink) && Objects.equals(name, image.name) &&
                Objects.equals(date, image.date) && Objects.equals(userID, image.userID) &&
                Objects.equals(sharedImage, image.sharedImage);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(imageID, imagelink, name, size, date, userID, sharedImage);
    }

    @Override
    public String toString()
    {
        return "Image{" +
                "imageID=" + imageID +
                ", link='" + imagelink + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", date='" + date + '\'' +
                ", userID=" + userID +
                ", sharedImage=" + sharedImage +
                '}';
    }

}
