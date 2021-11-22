package za.ac.nwu.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.domain.dto.imageDTO;
import za.ac.nwu.domain.persistence.imagePersistence;
import za.ac.nwu.domain.persistence.userPersistence;
import javax.transaction.Transactional;
import java.awt.*;
import java.util.List;

@Transactional
@Repository
public interface imageRepo extends JpaRepository<Image, Integer>
{
    void deleteLink(String imageLink);
    @Query(value = "SELECT img.name FROM Image img WHERE img.imageID = :imageID")
    String getImageName(Integer imgID);
    List<imageDTO> findAllByUserID(userPersistence userID);
}
