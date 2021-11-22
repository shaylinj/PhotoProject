package za.ac.nwu.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.nwu.domain.persistence.shareimagePersistence;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface shareimageRepo //extends JpaRepository<shareImage, Integer>
{
    @Query(value = "SELECT shr.sharedImageID FROM SharedImage shr WHERE shr.imageID = :imageID AND shr.userIDShared.userID = :sharedID AND shr.userIDSharer.userID = :sharerID")
    Integer getSharedID(Integer imgID, Integer sharerID, Integer sharedID);
}
