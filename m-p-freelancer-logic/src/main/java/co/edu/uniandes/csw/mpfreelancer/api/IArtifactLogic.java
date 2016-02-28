package co.edu.uniandes.csw.mpfreelancer.api;

import co.edu.uniandes.csw.mpfreelancer.entities.ArtifactEntity;
import java.util.List;

/**
 * @author Alex Vicente Chacon (av.chacon10@uniandes.edu.co)
 */
public interface IArtifactLogic {
    public int countArtifacts(Long projectSprintId);
    
    public List<ArtifactEntity> getArtifacts(Long projectSprintId);
    public List<ArtifactEntity> getArtifacts(Long projectSprintId, Integer page, Integer maxRecords);
    
    public ArtifactEntity getArtifact(Long id);
    public ArtifactEntity createArtifact(ArtifactEntity entity);
    public ArtifactEntity updateArtifact(ArtifactEntity entity);
    public void deleteArtifact(Long id);
}
