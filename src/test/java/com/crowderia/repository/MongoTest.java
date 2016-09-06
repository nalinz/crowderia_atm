package com.crowderia.repository;

import com.crowderia.model.Tree;
import com.crowderia.repository.impl.NatureRepositoryImpl;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class MongoTest {

    ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
            "classpath:/applicationContext.xml");
    Repository repository = context.getBean(NatureRepositoryImpl.class);

    @BeforeMethod
    public void setUp() throws Exception {
        // cleanup collection before insertion
        repository.dropCollection();
        // create collection
        repository.createCollection();

    }

    @AfterMethod
    public void tearDown() throws Exception {
        // cleanup collection before insertion
        repository.dropCollection();
    }

    @Test
    public void testCreateUpdateDeleteGetAll(){


        repository.saveObject(new Tree("1", "Apple com.crowderia.model.Tree", 10));
        Tree tree = (Tree) repository.getObject("1");
        Assert.assertEquals(tree.getId(), "1");

        repository.saveObject(new Tree("2", "Orange com.crowderia.model.Tree", 3));
        Tree tree2 = (Tree) repository.getObject("2");
        Assert.assertEquals(tree2.getId(), "2");

        List<Tree> allObjects = repository.getAllObjects();
        Assert.assertEquals(allObjects.size(), 2);

        String updatedName = "Peach com.crowderia.model.Tree";
        repository.updateObject("1", updatedName);
        Tree treeUpdated = (Tree) repository.getObject("1");
        Assert.assertEquals(treeUpdated.getName(), updatedName);


        repository.deleteObject("2");
        Tree deletedTree = (Tree) repository.getObject("2");
        Assert.assertNull(deletedTree);

    }
}