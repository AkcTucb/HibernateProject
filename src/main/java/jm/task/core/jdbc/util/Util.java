package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(User.class)
                    .setProperty("hibernate.connection.url",
                            "jdbc:mysql://localhost:3306/testdb?useSSL=false&allowPublicKeyRetrieval=true")
                    .setProperty("hibernate.connection.driver_class",
                            "com.mysql.cj.jdbc.Driver")
                    .setProperty("hibernate.connection.username", "root")
                    .setProperty("hibernate.connection.password", "123451")
                    .setProperty("hibernate.dialect",
                            "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.format_sql", "true")
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Failed to create SessionFactory object.");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}