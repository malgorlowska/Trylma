package database;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class hibernateDao {
    @Autowired
    private SessionFactory sessionFactory;
}
