package co.edu.uniandes.csw.mpfreelancer.ejbs;

import co.edu.uniandes.csw.mp.ann.MPLoCAnn;
import co.edu.uniandes.csw.mpfreelancer.api.ICategoryLogic;
import co.edu.uniandes.csw.mpfreelancer.entities.CategoryEntity;
import co.edu.uniandes.csw.mpfreelancer.persistence.CategoryPersistence;
import co.edu.uniandes.csw.mpfreelancer.entities.ProjectEntity;
import co.edu.uniandes.csw.mpfreelancer.api.IProjectLogic;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class CategoryLogic implements ICategoryLogic {

    @Inject private CategoryPersistence persistence;

    @Inject private IProjectLogic projectLogic;

    /**
     * @generated
     */
    @Override
    public int countCategorys() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<CategoryEntity> getCategorys() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<CategoryEntity> getCategorys(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public CategoryEntity getCategory(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @MPLoCAnn(tier = "Backend", reqId = "R011")
    @Override
    public CategoryEntity createCategory(CategoryEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @MPLoCAnn(tier = "Backend", reqId = "R012")
    @Override
    public CategoryEntity updateCategory(CategoryEntity entity) {
        CategoryEntity newEntity = entity;
        CategoryEntity oldEntity = persistence.find(entity.getId());
        newEntity.setProjects(oldEntity.getProjects());
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @MPLoCAnn(tier = "Backend", reqId = "R013")
    @Override
    public void deleteCategory(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    @Override
    public List<ProjectEntity> listProjects(Long categoryId) {
        return persistence.find(categoryId).getProjects();
    }

    /**
     * @generated
     */
    @Override
    public ProjectEntity getProjects(Long categoryId, Long projectsId) {
        List<ProjectEntity> list = persistence.find(categoryId).getProjects();
        
        //ProjectEntity projectsEntity = new ProjectEntity();
        //projectsEntity.setId(projectsId);
        //int index = list.indexOf(projectsEntity);
        
        for (int i = 0 ; i < list.size(); i++)
        {
            ProjectEntity projectEntity = list.get(i);
            if (projectEntity.getId().equals(projectsId))
                return projectEntity;
        }
        /*
        if (index >= 0) {
            return list.get(index);
        }
        */
        return null;
    }

    /**
     * @generated
     */
    @Override
    public ProjectEntity addProjects(Long categoryId, Long projectsId) {
        CategoryEntity categoryEntity = persistence.find(categoryId);
        ProjectEntity projectsEntity = projectLogic.getProject(projectsId);
        projectsEntity.setCategory(categoryEntity);
        return projectsEntity;
    }

    /**
     * @generated
     */
    @Override
    public List<ProjectEntity> replaceProjects(Long categoryId, List<ProjectEntity> list) {
        CategoryEntity categoryEntity = persistence.find(categoryId);
        List<ProjectEntity> projectList = projectLogic.getProjects();
        for (ProjectEntity project : projectList) {
            if (list.contains(project)) {
                project.setCategory(categoryEntity);
            } else {
                if (project.getCategory() != null && project.getCategory().equals(categoryEntity)) {
                    project.setCategory(null);
                }
            }
        }
        categoryEntity.setProjects(list);
        return categoryEntity.getProjects();
    }

    /**
     * @generated
     */
    @Override
    public void removeProjects(Long categoryId, Long projectsId) {
        ProjectEntity entity = projectLogic.getProject(projectsId);
        entity.setCategory(null);
    }
}
