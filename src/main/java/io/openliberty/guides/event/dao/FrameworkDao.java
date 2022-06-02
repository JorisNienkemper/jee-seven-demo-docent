package io.openliberty.guides.event.dao;


import io.openliberty.guides.event.models.Framework;
import io.openliberty.guides.event.resources.FrameworkResource;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class FrameworkDao {

    private static Logger logger = LogManager.getLogger(FrameworkResource.class);
    @PersistenceContext(name = "jpa-unit")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void save(Framework framework){
        logger.debug("start save(framework) " + framework);
        em.persist(framework);
        logger.debug("end save()");
    }
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public Framework getById(int id){
       return   em.find(Framework.class,id);
    }

    public void update(Framework updatedFramework){
        em.merge(updatedFramework);
    }

    public void delete(Framework framework){
        Framework framework1 = em.merge(framework);
        em.remove(framework1);
    }

    public List<Framework> getFrameworkByName(String name){

        String jpqlString ="SELECT f FROM Framework f WHERE f.name=:name";
        return em.createQuery(jpqlString,Framework.class)
                .setParameter("name",name)
                .getResultList();
    }

    public List<Framework> getFrameworks() {
        return em.createQuery("SELECT f FROM Framework f").getResultList();
    }

}
