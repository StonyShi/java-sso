

import com.stony.sso.facade.service.PermissionService;
import org.apache.shiro.session.mgt.SimpleSession;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/31 </p>
 * <p>Time: 14:35 </p>
 * <p>Version: 1.0 </p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:client/test-spring-client.xml"})
public class PermissTest {


    @javax.annotation.Resource
    private PermissionService permissionService;

    public static final String remoteServiceUrl = "http://127.0.0.1:8099/invoker/permission";
    public static final String appKey = "5efaf7c8-8315-35c7-ab67-ac3bde4963bd";


    @BeforeClass
    public static void beforeClass(){
        System.setProperty("catalina.home", "/Users/stony/logs");
    }
    @Test
    public void test(){
        MultiValueMap<String ,Object> urlVariables = new LinkedMultiValueMap<String ,Object>();
        SimpleSession session = new SimpleSession();
        urlVariables.add("session", session);
        urlVariables.add("appKey", appKey);

//        Serializable sessionId = restTemplate.postForObject(getUrl(PermissionContext.CREATE_SESSION), null, Serializable.class, urlVariables);
        System.out.println("---------------------------------------------------");
        System.out.println(permissionService.getResources(appKey));
    }

    private String getUrl(String suffix){
        return remoteServiceUrl + suffix;
    }
}
