package co.edu.uniandes.csw.mpfreelancer.api;

import co.edu.uniandes.csw.mpfreelancer.entities.ArtifactEntity;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectSprintEntity;
import java.util.List;

/**
 * @author Alex Vicente Chacon (av.chacon10@uniandes.edu.co)
 */
public interface IProjectSprintLogic {
    public int countProjectSprints(Long projectId);
    public int countProjectSprints();
    
    public List<ProjectSprintEntity> getProjectSprints(Long projectId);
    public List<ProjectSprintEntity> getProjectSprints(Long projectId, Integer page, Integer maxRecords);
    public List<ProjectSprintEntity> getProjectSprints(Integer page, Integer maxRecords);
    public List<ProjectSprintEntity> getProjectSprints();
    
    public ProjectSprintEntity getProjectSprint(Long id);
    public ProjectSprintEntity createProjectSprint(ProjectSprintEntity entity);
    public ProjectSprintEntity updateProjectSprint(ProjectSprintEntity entity);
    public void deleteProjectSprint(Long id);
    
    public List<ArtifactEntity> listArtifacts(Long projectSprintId);
    public ArtifactEntity getArtifacts(Long projectSprintId, Long artifactsId);
    public ArtifactEntity addArtifacts(Long projectSprintId, Long artifactsId);
    public void removeArtifacts(Long projectSprintId, Long artifactsId);
}
