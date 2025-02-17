package Factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class DatabaseJPA {

    private static final String PERSISTENCE_UNIT_NAME = "safesoft"; // Certifique-se de que corresponde ao nome do persistence-unit no persistence.xml
    private static EntityManagerFactory entityManagerFactory;
    private static DatabaseJPA instance;

    private DatabaseJPA() {
        // Construtor privado para garantir que a classe seja singleton
    }

    // Método para obter a instância singleton da classe
    public static DatabaseJPA getInstance() {
        if (instance == null) {
            synchronized (DatabaseJPA.class) {  // Usando sincronização para evitar problemas em ambientes multi-threaded
                if (instance == null) {
                    instance = new DatabaseJPA();
                }
            }
        }
        return instance;
    }

    // Método para obter a instância do EntityManagerFactory
    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            try {
                entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            } catch (PersistenceException e) {
                System.err.println("Erro ao criar o EntityManagerFactory: " + e.getMessage());
                e.printStackTrace();  // Exibe o erro completo no console
                throw e;  // Re-lança a exceção
            }
        }
        return entityManagerFactory;
    }



    // Método para obter o EntityManager
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    // Método para fechar o EntityManagerFactory quando não for mais necessário
    public static void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
