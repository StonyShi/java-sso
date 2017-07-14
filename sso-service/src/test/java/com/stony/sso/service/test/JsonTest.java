package com.stony.sso.service.test;


import com.alibaba.fastjson.JSON;
import com.stony.sso.facade.context.PermissionContext;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/7/12 </p>
 * <p>Time: 12:01 </p>
 * <p>Version: 1.0 </p>
 */
public class JsonTest {
    public static void main(String[] args){
        String xx = "{\"username\":\"admin\",\"resources\":[{\"id\":86,\"name\":\"运营管理\",\"type\":\"MENU\",\"url\":\"\",\"parentId\":0,\"permission\":\"boss:*\",\"available\":1,\"icon\":\"\",\"insertDate\":\"2016-06-22 11:35:19\",\"updateDate\":\"2016-06-23 17:37:44\",\"hasRole\":false,\"availabled\":true,\"button\":false,\"menu\":true},{\"id\":112,\"name\":\"运营-审核设置-修改\",\"type\":\"BUTTON\",\"url\":\"\",\"parentId\":87,\"permission\":\"boss:admin:flow:update\",\"available\":1,\"icon\":\"\",\"insertDate\":\"2016-06-22 16:07:53\",\"updateDate\":\"2016-06-23 17:40:32\",\"hasRole\":false,\"availabled\":true,\"button\":true,\"menu\":false},{\"id\":87,\"name\":\"运营-字典管理\",\"type\":\"MENU\",\"url\":\"/systype/list\",\"parentId\":124,\"permission\":\"boss:admin:systype:*\",\"available\":1,\"icon\":\"\",\"insertDate\":\"2016-06-22 11:36:03\",\"updateDate\":\"2016-06-23 17:42:47\",\"hasRole\":false,\"availabled\":true,\"button\":false,\"menu\":true},{\"id\":89,\"name\":\"审批中心\",\"type\":\"MENU\",\"url\":\"\",\"parentId\":0,\"permission\":\"flow:*\",\"available\":1,\"icon\":\"\",\"insertDate\":\"2016-06-22 11:39:10\",\"updateDate\":\"2016-07-01 10:18:32\",\"hasRole\":false,\"availabled\":true,\"button\":false,\"menu\":true},{\"id\":92,\"name\":\"历史存档审批单\",\"type\":\"MENU\",\"url\":\"{support_url}/flow/audity/history\",\"parentId\":89,\"permission\":\"flow:history:*\",\"available\":1,\"icon\":\"\",\"insertDate\":\"2016-06-22 11:41:13\",\"updateDate\":\"2016-07-01 10:20:13\",\"hasRole\":false,\"availabled\":true,\"button\":false,\"menu\":true},{\"id\":91,\"name\":\"未结束审批单\",\"type\":\"MENU\",\"url\":\"{support_url}/flow/audity/noFinish\",\"parentId\":89,\"permission\":\"flow:noFinish:*\",\"available\":1,\"icon\":\"\",\"insertDate\":\"2016-06-22 11:40:32\",\"updateDate\":\"2016-07-01 10:20:06\",\"hasRole\":false,\"availabled\":true,\"button\":false,\"menu\":true},{\"id\":90,\"name\":\"待审核、待完成审批\",\"type\":\"MENU\",\"url\":\"{support_url}/flow/audity/wait\",\"parentId\":89,\"permission\":\"flow:wait:*\",\"available\":1,\"icon\":\"\",\"insertDate\":\"2016-06-22 11:39:59\",\"updateDate\":\"2016-07-01 10:19:59\",\"hasRole\":false,\"availabled\":true,\"button\":false,\"menu\":true},{\"id\":100,\"name\":\"客服\",\"type\":\"MENU\",\"url\":\"\",\"parentId\":0,\"permission\":\"support:*\",\"available\":1,\"icon\":\"\",\"insertDate\":\"2016-06-22 14:52:17\",\"hasRole\":false,\"availabled\":true,\"button\":false,\"menu\":true}],\"roles\":[{\"id\":43,\"role\":\"support-test\",\"description\":\"\",\"available\":1,\"insertDate\":\"2016-06-23 12:06:11\"}],\"responseTime\":\"2016-07-12 11:57:27\"}";
        PermissionContext context = JSON.parseObject(xx, PermissionContext.class);
        System.out.println(context);

        System.out.println(JSON.toJSON(context));


//        PermissionContext entity = (PermissionContext) JacksonUtil.json2Object(xx, PermissionContext.class);
//        System.out.println(entity);
    }
}
