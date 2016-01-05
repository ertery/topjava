package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by NotePad on 05.01.2016.
 */
@ActiveProfiles(Profiles.JDBC)
public class JdbcUserMealServiceTest extends UserMealServiceTest {
}
