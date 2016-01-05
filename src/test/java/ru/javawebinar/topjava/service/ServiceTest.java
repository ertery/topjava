package ru.javawebinar.topjava.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by NotePad on 05.01.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(Profiles.POSTGRES)
public abstract class ServiceTest {
    protected abstract void testDelete() throws Exception;
    protected abstract void testNotFoundDelete() throws Exception;
    protected abstract void testSave() throws Exception;
    protected abstract void testGet() throws Exception;
    protected abstract void testGetNotFound() throws Exception;
    protected abstract void testUpdate() throws Exception;
    protected abstract void testGetAll() throws Exception;


}
