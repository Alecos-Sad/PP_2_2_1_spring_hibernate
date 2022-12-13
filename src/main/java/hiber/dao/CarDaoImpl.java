package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addCar(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car ");
        return query.getResultList();
    }


    @SuppressWarnings("unchecked")
    public Car findCar(String car_name, String car_series) {
        TypedQuery<Car> findCarQuery = sessionFactory.getCurrentSession().createQuery("from Car where name = :car_name and series = :car_series")
                .setParameter("car_name", car_name)
                .setParameter("car_series", car_series);
        List<Car> findCarList = findCarQuery.getResultList();
        return findCarList.get(0);
    }
}
