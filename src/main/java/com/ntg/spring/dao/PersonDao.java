package com.ntg.spring.dao;

import com.ntg.spring.models.Person;

import java.util.List;

/**
 * @author Nurbolot Gulamidinov
 */

public interface PersonDao {
    List<Person> index();

    Person show(long id);

    void save(Person person);

    void update(long id, Person person);

    void delete(long id);
}
