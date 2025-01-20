package Factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseJPA {

    private static final String PERSISTENCE_UNIT_NAME = "myJpaUnit";  // Certifique-se de que corresponde ao nome do persistence-unit no persistence.xml

    private static EntityManagerFactory entityManagerFactory;

    private DatabaseJPA() {
        // Construtor privado para garantir que a classe é singleton
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return entityManagerFactory;
    }

    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    public static void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
