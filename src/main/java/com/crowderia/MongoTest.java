package com.crowderia;

import com.crowderia.model.Tree;
import com.crowderia.repository.Repository;
import com.crowderia.repository.impl.NatureRepositoryImpl;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class MongoTest {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:/applicationContext.xml");

        Repository repository = context.getBean(NatureRepositoryImpl.class);

        // cleanup collection before insertion
        repository.dropCollection();

        // create collection
        repository.createCollection();

        repository.saveObject(new Tree("1", "Apple com.crowderia.model.Tree", 10));

        System.out.println("1. " + repository.getAllObjects());

        repository.saveObject(new Tree("2", "Orange com.crowderia.model.Tree", 3));

        System.out.println("2. " + repository.getAllObjects());

        System.out.println("com.crowderia.model.Tree with id 1" + repository.getObject("1"));

        repository.updateObject("1", "Peach com.crowderia.model.Tree");

        System.out.println("3. " + repository.getAllObjects());

        repository.deleteObject("2");

        System.out.println("4. " + repository.getAllObjects());
    }
}