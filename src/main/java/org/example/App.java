package org.example;


import org.example.entity.Car;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;

public class App {
    public static void main( String[] args ) {
        // Открываем ссесию для работы с базами данных
        Session session = HibernateUtils.getSessionFactory().openSession();
        // Начинаем транзакцию
        session.beginTransaction();

        Car car = new Car();
        car.setProducer("Tesla");
        car.setModel("Model 3");

        //Сохраняем нашу сессию
        session.save(car);
        // Записываем данные
        session.getTransaction().commit();
        // Закрываем сессию
        session.close();


        //Чтение данных, для это нужно:
        // Открываем сессию
        session = HibernateUtils.getSessionFactory().openSession();
        // Начинаем транзакцию
        session.beginTransaction();
        // Serializable - уникальный индетификатор по которому Hibernate будет искать и индетификатор должен быть
        // сериализуемым у нас это Long отмеченная переменная аннотацией Id
        // Мы хотим получить Car, Id которой равен 1
        Car existedCar = session.get(Car.class, 1L);
        // Проверка на неравенство null (объект существует)
        System.out.println(existedCar != null);
        // Записываем данные
        session.getTransaction().commit();
        // Закрываем сессию
        session.close();

        // Закрываем доступ для получения сессий
        HibernateUtils.shutdown();
    }
}
