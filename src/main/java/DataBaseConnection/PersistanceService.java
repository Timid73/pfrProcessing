package DataBaseConnection;

import entity.BaseEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Service("persistanceService")
public class PersistanceService {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public <T> T getEntityByQuery(Class<T> type, String queryName, Map<String, Object> params) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery(queryName);

        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
        T entity = (T) query.uniqueResult();
        return entity;
    }

    @Transactional(readOnly = true)
    public <T> List<T> getAllByQuery(String queryName, Map<String, Object> params) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery(queryName);

        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
        List<T> entities = query.list();
        return entities;
    }

    @Transactional(readOnly = true)
    public <T> T makeNamedQuery(String queryName, Map<String, Object> params) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery(queryName);

        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
        T entity = (T) query.list();
        return entity;
    }

    @Transactional(readOnly = true)
    public <T> T makeNamedQuery(String queryName) {
        return makeNamedQuery(queryName, Collections.emptyMap());
    }

    @Transactional(readOnly = true)
    public <T extends BaseEntity> T get(Class<T> type, Long id) {
        T entity = (T) sessionFactory.getCurrentSession().get(type, id);
        return entity;
    }

    @Transactional
    public <T extends BaseEntity> T create(T entity) {
        Long id = (Long) sessionFactory.getCurrentSession().save(entity);
        entity.setId(id);
        return entity;
    }

    public <T extends BaseEntity> T createOrUpdate(T entity) {
        if (entity.getId() == null || get(entity.getClass(), entity.getId()) == null) {
            return create(entity);
        } else {
            return update(entity);
        }
    }

    @Transactional
    public <T extends BaseEntity> T update(T entity) {
        return (T) sessionFactory.getCurrentSession().merge(entity);
    }

    @Transactional
    public <T extends BaseEntity> List<T> update(List<T> entities) {
        List<T> newEntities = new ArrayList<>(entities.size());
        Session session = sessionFactory.getCurrentSession();
        for (T entity : entities) {
            newEntities.add((T) session.merge(entity));
        }
        return newEntities;
    }

    @Transactional
    public <T> void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Transactional
    public <T extends BaseEntity> boolean delete(Class<T> type, Long id) {
        T entity = get(type, id);
        if (entity != null) {
            sessionFactory.getCurrentSession().delete(entity);
            return true;
        } else {
            return false;
        }
    }
}
