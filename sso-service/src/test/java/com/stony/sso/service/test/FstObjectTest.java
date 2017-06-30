package com.stony.sso.service.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.stony.sso.facade.entity.*;
import org.apache.shiro.session.mgt.SimpleSession;
import org.junit.*;
import org.junit.runner.RunWith;
//import org.nustaq.serialization.FSTConfiguration;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/1 </p>
 * <p>Time: 11:32 </p>
 * <p>Version: 1.0 </p>
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-test.xml")
public class FstObjectTest {

    @Ignore
    @Test
    public void test2(){
//        FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
//        SimpleSession session = new SimpleSession();
////        FstObjectInput input = new FstObjectInput();
//        conf.registerClass(SimpleSession.class);
//        byte[] bytes = conf.asByteArray(session);
//        System.out.println(bytes);
//
//        System.out.println(conf.asObject(bytes));
//
//        System.out.println(App.class.getClassLoader());
//        System.out.println(List.class.getClassLoader());
    }

    static List<Long> fstTimes = new LinkedList<>();
    static List<Long> fstTimesDefault = new LinkedList<>();
    static List<Long> jdkTimes = new LinkedList<>();

    @Ignore
    @Repeat(30)
    @Test
    public void fstTestDefault(){
//        System.out.println("--------------------------------------");
//        int size = 0;
//        long beginTime = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            Resource resource = getResource(50);
//            User user = new User();
//            user.setId(1100L);
//            user.setPassword("dr234242342342");
//            user.setUsername("1231231");
//            user.setLocked(0);
//            user.setPhone("15534645646");
//            user.setEmail("bule@130.com");
//            byte[] bytes = fstSerizlize(resource);
//            //System.out.println("bytes = " + bytes);
//            size += bytes.length;
//            Resource unR = (Resource) fstUnSerizlize(bytes);
//            //System.out.println("unUser = " + unR);
//        }
//        Long overTime = (System.currentTimeMillis() - beginTime);
//        System.out.println("fstDefault序列化方案[序列化10000次]耗时：" + overTime + "ms ,size : " + size + " 字节");
//        fstTimesDefault.add(overTime);
    }

    @Repeat(30)
    @Test
    public void fstTest(){
//        FSTConfiguration conf = FstConfigInitEvent.conf;
////        System.out.println("--------------------------------------");
//        int size = 0;
//        long beginTime = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            Resource resource = getResource(50);
//            User user = new User();
//            user.setId(1100L);
//            user.setPassword("1sdf46dfgdfg");
//            user.setUsername("qqqqqqqsdsf");
//            user.setLocked(0);
//            user.setPhone("13124y554fgd");
//            user.setEmail("21sfsxe@120.com");
//            byte[] bytes = conf.asByteArray(resource);
//            //System.out.println("bytes = " + bytes);
//            size += bytes.length;
//            Resource unR = (Resource) conf.asObject(bytes);
//            //System.out.println("unUser = " + unR);
//        }
//        Long overTime = (System.currentTimeMillis() - beginTime);
//        System.out.println("fst序列化方案[序列化10000次]耗时：" + overTime + "ms ,size : " + size + " 字节");
//        fstTimes.add(overTime);
    }
    @Ignore
    @Repeat(30)
    @Test
    public void jdkTest(){
//        System.out.println("--------------------------------------");
        int size = 0;
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Resource resource = getResource(50);
            User user = new User();
            user.setId(1100L);
            user.setPassword("t23sfsd");
            user.setUsername("kaFleuasd");
            user.setLocked(0);
            user.setPhone("1343242342");
            user.setEmail("bule@120.com");
            byte[] bytes = jdkserialize(resource);
            //System.out.println("bytes = " + bytes);
            size += bytes.length;
            Resource unR = (Resource) jdkdeserialize(bytes);
            //System.out.println("unUser = " + unR);
        }
        Long overTime = (System.currentTimeMillis() - beginTime);
        System.out.println("jdk序列化方案[序列化10000次]耗时：" + overTime + "ms ,size : " + size + " 字节");
        jdkTimes.add(overTime);
    }



    @AfterClass
    public static void afterClass(){
        System.out.println("--------------------------------------------");
        System.out.println("jdkTimes  = " + jdkTimes);
        System.out.println("fstTimesD = " + fstTimesDefault);
        System.out.println("fstTimes  = " + fstTimes);
    }

    static ObjectMapper mapper = new ObjectMapper();
    static{
        //设置序列化配置，为null的属性不加入到json中
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    // jdk原生序列换方案
    public static byte[] jdkserialize(Object obj) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos);) {
            oos.writeObject(obj);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object jdkdeserialize(byte[] bits) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bits);
             ObjectInputStream ois = new ObjectInputStream(bais);

        ) {
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//    static FSTConfiguration configuration = FSTConfiguration.createDefaultConfiguration();

//    public static byte[] fstSerizlize(Serializable obj){
//        return configuration.asByteArray(obj);
//    }
//    public static Object fstUnSerizlize(byte[] src){
//        return configuration.asObject(src);
//    }

    static Resource getResource(int size){
        Resource resource = new Resource();
        resource.setId(10L);
        resource.setName("Ka");
        resource.setUpdateDate(new Date());
        resource.setInsertDate(new Date());
        resource.setIcon("lock");
        resource.setPermission("admin:*");
        List<Resource> children = new ArrayList<>();
        for(int i = 100; i < (100 + size); i++){
            Resource child = new Resource((long) i, 1, new Date());
            child.setPermission("add:id:"+i);
            child.setIcon("face");
            child.setParentId(10L);
            child.setParentName("Ka");
            child.setName("Cev_"+i);
            child.setInsertDate(new Date());
            children.add(child);
        }
        resource.setChildren(children);
        return resource;
    }



}
