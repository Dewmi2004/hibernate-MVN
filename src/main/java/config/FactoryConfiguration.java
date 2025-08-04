package config;

import entity.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

private FactoryConfiguration() throws  HibernateException {
    Configuration configuration = new Configuration().configure();
    configuration.addAnnotatedClass(Customer.class);
    sessionFactory = configuration.buildSessionFactory();
}
public static FactoryConfiguration getInstance() throws HibernateException {
    return  factoryConfiguration == null ? factoryConfiguration =new FactoryConfiguration():factoryConfiguration;

}
public Session getSession() throws HibernateException {
    return sessionFactory.openSession();
}
}
