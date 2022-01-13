package repositories;

import entities.Userelement;

import java.util.*;
import javax.persistence.*;

public class UserRepository {
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private final EntityTransaction transaction;

    public UserRepository(String pu) {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(pu);
        this.entityManager = entityManagerFactory.createEntityManager();
        this.transaction = entityManager.getTransaction();
    }

    public Userelement findUserById(int id){
        return entityManager.find(Userelement.class, id);
    }

    public void createUser(Userelement user){
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
    }

    public void deleteUser(int id){
        Userelement user = this.findUserById(id);
        transaction.begin();
        entityManager.remove(user);
        transaction.commit();

    }

    public void updateUser(Userelement user){
        Userelement userToUpdate = this.findUserById(user.getId());
        transaction.begin();
        userToUpdate.setName(user.getName());
        userToUpdate.setHashedpassword(user.getHashedpassword());
        transaction.commit();
    }

    public List<Userelement> getAll(){
        return entityManager.createQuery("select u from Userelement u").getResultList();
    }

    public Userelement getByUserName(String userName){
        TypedQuery<Userelement> query = entityManager.createQuery(
                "select u from Userelement u WHERE u.name like :userName" , Userelement.class);

        Userelement user = query
                .setParameter("userName",userName)
                .getResultList().get(0);

        return user;
    }
}
