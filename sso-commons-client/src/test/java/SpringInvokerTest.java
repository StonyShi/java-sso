import com.stony.sso.facade.service.PermissionService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * <p>Java-SSO
 * <p>PACKAGE_NAME
 *
 * @author stony
 * @version 下午6:25
 * @since 2017/6/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:client/test-spring-invoker.xml"})
public class SpringInvokerTest {

    @Resource
    PermissionService permissionService;


    @BeforeClass
    public static void beforeClass(){
        System.setProperty("catalina.home", "/Users/stony/logs");
    }

    @Test
    public void test_24(){

        System.out.println(permissionService.getResources());
    }
}
