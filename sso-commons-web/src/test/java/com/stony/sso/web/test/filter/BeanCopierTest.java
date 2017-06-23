package com.stony.sso.web.test.filter;

import net.sf.cglib.beans.BeanCopier;

import java.io.Serializable;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/10 </p>
 * <p>Time: 12:19 </p>
 * <p>Version: 1.0 </p>
 */
public class BeanCopierTest {

    public static void main(String[] args){
        XX xx = new XX();
        xx.x = 300;
        XX xx2 = new XX();
        BeanCopier.create(xx.getClass(),xx2.getClass(),false);
        xx2.x = 133;
        System.out.println(xx);
        System.out.println(xx2);
    }
    static class XX implements Serializable {
        public int x = 100;
        @Override
        public String toString() {
            return "xxxxxxxxx" + x;
        }
    }
}
