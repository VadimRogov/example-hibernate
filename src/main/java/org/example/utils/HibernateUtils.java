package org.example.utils;

import org.example.entity.Car;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {

    // Выполняет работу по конфигурации Hibernate;
    private static final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder().configure().build();

    // Предоставляет доступ до получения и открытия сессий
    private static final SessionFactory sessionFactory = buildSessionFactory();


    private static SessionFactory buildSessionFactory() {
        // Класс из библиотеки SessionFactory для создания SessionFactor
        return new MetadataSources(REGISTRY)
                // Добавляем персистентную сущность
                .addAnnotatedClass(Car.class)
                // Построение метаданных
                .buildMetadata()
                // Построение SessionFactory
                .buildSessionFactory();
    }
    // Геттер для получения SessionFactory, для того чтобы все потребители нашего класса могли получить доступ
    // к закрытию и открытию сессий
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // После использования базы данных необходимо закрыть сессию и фабрикку SessionFactory,
    // чтобы больше никто не смог получить доступ
    public static void shutdown() {
        getSessionFactory().close();
    }
}
