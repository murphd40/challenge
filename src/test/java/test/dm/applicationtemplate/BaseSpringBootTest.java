package test.dm.applicationtemplate;

import dm.applicationtemplate.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by David on 04/07/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public abstract class BaseSpringBootTest {
}
