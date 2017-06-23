import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.springframework.beans.factory.support.BeanDefinitionBuilder.*;

/**
 * @author Stony Created Date : 2016/4/25  21:25
 */
public class ClientTest {


    @Ignore
    @Test
    public void test2(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:client/test-spring-client.xml");


//        .registerBeanDefinition("testBean",
//                genericBeanDefinition(TestBean.class)
//                        .addPropertyValue("name", "${my.name}")
//                        .getBeanDefinition());

        System.out.println(ac.getBeanFactory());
        DefaultListableBeanFactory bf = (DefaultListableBeanFactory) ac.getBeanFactory();

//        bf.registerBeanDefinition("testBean",
//                genericBeanDefinition(TestBean.class)
//                        .addPropertyValue("name", "${client.app.key}")
//                        .addPropertyValue("filter","${client.filter.chain.definitions}")
//                        .getBeanDefinition());


        PropertySourcesPlaceholderConfigurer placeholderConfigurer = ac.getBean(PropertySourcesPlaceholderConfigurer.class);
        System.out.println(placeholderConfigurer);

        System.out.println(System.getenv("client.app.key"));
        System.out.println(System.getProperty("client.app.key"));

        TestBean testBean = bf.getBean(TestBean.class);
        System.out.println(testBean.getName());
        System.out.println(testBean.getFilter());
    }

    @Test
    public void test(){
        System.out.println(System.currentTimeMillis());
    }
}
