package hibernate_wcd.util;

import com.mysql.cj.Session;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    static {
        try{
            sessionFactory = new Configuration().configure("hibernate.org.xml").buildSessionFactory();
        }catch (HibernateException e){
            throw new ExceptionInInitializerError(e.getMessage());
        }
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
