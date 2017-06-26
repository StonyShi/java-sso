package com.zhuanche.web.security.test;

import com.stony.sso.facade.entity.Resource;
import com.stony.sso.commons.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/29 </p>
 * <p>Time: 16:01 </p>
 * <p>Version: 1.0 </p>
 */
public class MenusTest {
    @Ignore
    @Test
    public void test2(){
        List<Resource> resources = new ArrayList<>();
        for(int i = 0; i<13; i++){
            Resource resource = new Resource();
            if(i == 0 || i == 5 || i == 11){
                resource.setUrl("");
            }else{
                resource.setUrl("/user/blue/"+ i);
            }
            resource.setName("Name_"+(i+1));
            resources.add(resource);
        }
        System.out.println(resources);
        //?size > 0
        if(resources.size() > 0){
            System.out.println("<li class=\"\">");
        }
        int index = 0;
        int subIndex = 0;
        for(Resource resource : resources){
            if(StringUtils.isEmpty(resource.getUrl())){
                if(subIndex > 0){
                    System.out.println("  </ul>");

                }
                if(index == 2){
                    System.out.println("</li>");
                    index = 1;
                }
                subIndex = 0;
                if(index == 1){
                    System.out.println("<li class=\"\">");
                }
                System.out.println("  <a href=\"javascript:;\">\n" +
                        "       <i class=\"icon-cog\"></i>\n" +
                        "       <span class=\"title\">"+resource.getName()+"</span>\n" +
                        "       <span class=\"arrow \"></span>\n" +
                        "  </a>");
                index = 2;
            }else{
                if(subIndex == 0){
                    System.out.println("  <ul class=\"sub-menu\">");
                    subIndex++;
                }
                System.out.println("    <li >\n" +
                        "      <a href=\""+resource.getUrl()+"\">"+resource.getName()+"</a>\n" +
                        "    </li>");
            }
        }
        if(subIndex > 0){
            System.out.println("  </ul>");
        }
        if(resources.size() > 0){
            System.out.println("</li>");
        }
    }
    @Test
    public void test(){

    }
}
