package com.car.sqoil.test;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import com.zhuanche.car.commons.db.proxy.DataSourceProxy;
import com.zhuanche.car.commons.util.WorkbookUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/7/11 </p>
 * <p>Time: 16:00 </p>
 * <p>Version: 1.0 </p>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-test.xml")
@TransactionConfiguration(transactionManager = "authorityCenterTransactionManager")
public class WorkbookUtilTest {

    @javax.annotation.Resource
    ApplicationContext applicationContext;

    @javax.annotation.Resource
    DataSourceProxy authorityCenterDataSourceProxy;
    //2016/6/2 8:52
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    @Test
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = Exception.class)
    public void test() throws Exception {
        ClassPathResource resource = new ClassPathResource("work/201606.xls");
        System.out.println(resource.getFile().getPath());
        System.out.println(resource.getPath());
        System.out.println(resource.getURI());
        System.out.println(resource.getURL());
//        String path = WorkbookUtilTest.class.getResource("./").getPath();
        Workbook wb = WorkbookUtil.getWorkBook(resource.getFile());
        System.out.println("###############################################################################");
        Sheet sheet = WorkbookUtil.getSheets(wb).get(0);
        //考勤号码|自定义编号|姓名|出勤时间|出勤状态|更正状态|异常情况|操作
        //1=上班签到，2=下班签退
        List<String> records = WorkbookUtil.getRecordBySheet(sheet);
        System.out.println();
        String sql = "INSERT INTO work_record(month,year,number,number_cus," +
                "username,work_time,work_status,work_status_number," +
                "work_status_fix,exception," +
                "operation,insert_time)" +
                "VALUES (?,?,?,?" +
                ",?,?,?,?" +
                ",?,?" +
                ",?,now())";
        Connection connection = authorityCenterDataSourceProxy.getConnection();
        PreparedStatement ps =  connection.prepareStatement(sql);
        System.out.println(records);
        System.out.println("###############################################################################");
        for(int i = 0, len = records.size(); i < len; i++){
            if (i == 0) {
                continue;
            }
            String record = records.get(i);
            Iterable<String> recordIt = Splitter.on("|").trimResults().split(record);
            List<String> ll = Lists.newArrayList(recordIt);
            String work_time_str = ll.get(3);
            Date workTime = sdf.parse(work_time_str);
            DateTime dateTime = new DateTime(workTime);
            int index = 1;
            ps.setInt(index++, dateTime.getMonthOfYear());
            ps.setInt(index++, dateTime.getYear());
            ps.setString(index++, ll.get(0)); //考勤号码
            ps.setString(index++, ll.get(1)); //自定义编号
            ps.setString(index++, ll.get(2)); //姓名
            ps.setTimestamp(index++, new Timestamp(workTime.getTime())); //3 出勤时间
            ps.setString(index++, ll.get(4)); //出勤状态
            ps.setInt(index++, WorkStatus.valueOfStatus(ll.get(4)).CODE);
            ps.setString(index++, ll.get(5)); //更正状态
            ps.setString(index++, ll.get(6)); //异常情况
            ps.setString(index++, ll.get(7)); //操作
            ps.addBatch();
        }
        System.out.println("batch = " + Arrays.toString(ps.executeBatch()));
        ps.close();
        connection.close();
    }

    enum WorkStatus{
        WORK_NONE(0,"上班"),WORK_ON(1,"上班签到"),WORK_OFF(2,"下班签退"),WORK_OVER(3,"加班签退");
        public int CODE;
        public String STATUS;
        WorkStatus(int code, String status) {
            this.CODE = code;
            this.STATUS = status;
        }
        public static WorkStatus valueOfCode(int code){
            for(WorkStatus ws : WorkStatus.values()){
                if(code == ws.CODE){
                    return ws;
                }
            }
            return WorkStatus.WORK_NONE;
        }
        public static WorkStatus valueOfStatus(String status){
            for(WorkStatus ws : WorkStatus.values()){
                if(status.equals(ws.STATUS)){
                    return ws;
                }
            }
            return WorkStatus.WORK_NONE;
        }
    }
}
