package com.stony.sso.cache.test;


import java.lang.reflect.Array;
import java.util.*;

import com.stony.sso.commons.CollectionUtil;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.junit.Test;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/5 </p>
 * <p>Time: 19:22 </p>
 * <p>Version: 1.0 </p>
 * Spring el 表达式测试
 */
public class SpelExpTest {

    @Test
    public void test2(){
        SpelExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext ctx = new StandardEvaluationContext();


        Long[] roleIds = new Long[]{333L,444L,555L};
        ctx.setVariable("roleIds", roleIds);

        System.out.println("------------------------------");
        System.out.println(CollectionUtil.arrayLongConvertStr(roleIds));
        Expression exp = parser.parseExpression("T(com.zhuanche.car.commons.util.CollectionUtil).arrayLongConvertStr(333L,444L,555L)");
        System.out.println(exp.getValue());

        exp = parser.parseExpression("T(com.zhuanche.car.commons.util.CollectionUtil).arrayLongConvertStr(#roleIds)");
        System.out.println( (String) exp.getValue(ctx));

        System.out.println("------------------------------");
        exp = parser.parseExpression("'Blue'.concat('_Sky').concat('_CK')");
        System.out.println(exp.getValue());
        ctx.setVariable("A", "AA");
        ctx.setVariable("B", "BB");
        ctx.setVariable("C", "CC");
        exp = parser.parseExpression("#A + '_' + #B");
        System.out.println(exp.getValue(ctx));

        exp = parser.parseExpression("#A.concat('_').concat(#B)");
        System.out.println(exp.getValue(ctx));

        System.out.println("------------------------------");
        Set<String> permissions = new HashSet<>();
        permissions.add("le");
        permissions.add("bua");
        System.out.println(CollectionUtil.arrayStringConvertStr(permissions));
        ctx.setVariable("permissions", permissions);

        exp = parser.parseExpression("T(com.zhuanche.car.commons.util.CollectionUtil).arrayStringConvertStr(#permissions)");
        System.out.println( (String) exp.getValue(ctx));
        exp = parser.parseExpression("T(com.zhuanche.car.commons.util.CollectionUtil).arrayStringConvertStr(#permissions,'_')");
        System.out.println( (String) exp.getValue(ctx));



    }

   @Test
   public void test(){
       SpelExpressionParser parser = new SpelExpressionParser();

       Expression exp = parser.parseExpression("1 + 1");
       System.out.println(exp.getValue());

       StandardEvaluationContext ctx = new StandardEvaluationContext(new Reserver());
       parser = new SpelExpressionParser();
       String ex = "getReserver().NE";
       exp = parser.parseRaw(ex);
       String value = (String) exp.getValue(ctx);
       System.out.println(value);

       ex = "add(1,3)";
       exp = parser.parseRaw(ex);
       System.out.println(exp.getValue(ctx));
       ex = "add(getA(),getB())";
       exp = parser.parseRaw(ex);
       System.out.println(exp.getValue(ctx));


       SpelParserConfiguration configuration = new SpelParserConfiguration(false, false);
       parser = new SpelExpressionParser(configuration);

       StandardEvaluationContext context = new StandardEvaluationContext();
       Expression spel = parser.parseExpression("#enumType.values()");

       context.setVariable("enumType", ABC.class);
       Object result = spel.getValue(context);
       System.out.println(result);
       System.out.println(Array.get(result, 0));


       context.setVariable("enumType", XYZ.class);
       result = spel.getValue(context);
       System.out.println(result);
       System.out.println(Array.get(result, 0));

       context.setVariable("enumType", Color.class);
       result = spel.getValue(context);
       System.out.println(result);
       System.out.println(Array.get(result, 0));

       Map<String, Integer> map = new HashMap<String, Integer>();
       map.put("one", 100);
       map.put("two", 100);
       Expression expr = parser.parseExpression("['one'] == ['two']");
       System.out.println(expr.getValue(map, Boolean.class));


       exp = parser.parseExpression("T(java.util.Arrays).asList('a','b')");
       List<String> list = (List<String>) exp.getValue();
       System.out.println(list);

       System.out.println("--------------");
       SpelExpressionParser parser2 = new SpelExpressionParser();
       StandardEvaluationContext context2 = new StandardEvaluationContext();
       context2.setVariable("variable", "variable");
       context2.setVariable("variable", "variable2");
       String result1 = parser2.parseExpression("#variable").getValue(context2, String.class);
       System.out.println(result1);

       context2 = new StandardEvaluationContext("haha");
       String result2 = parser2.parseExpression("#root").getValue(context2, String.class);
       System.out.println(result2);


       context.setVariable("#variable", "variable");
       String result3 = parser2.parseExpression("#variable=#root").getValue(context2, String.class);
       System.out.println(result3);


       context2.setVariable("id", "10323");
       Object result4 = parser2.parseExpression("#variable.concat('_').concat(#id)").getValue(context2);
       System.out.println(result4);

       context2.setVariable("name", "lulu");
       Object result5 = parser2.parseExpression("'getUser_' + #name + '_' + #id").getValue(context2);
       System.out.println(result5);



   }

    private static enum Color {
        RED(255, 0, 0), BLUE(0, 0, 255), BLACK(0, 0, 0), YELLOW(255, 255, 0), GREEN(0, 255, 0);
        private Color(int rv, int gv, int bv) {
            this.redValue = rv;
            this.greenValue = gv;
            this.blueValue = bv;
        }
        @Override
        public String toString() {
            return super.toString() + "(" + redValue + "," + greenValue + "," + blueValue + ")";
        }
        private int redValue;
        private int greenValue;
        private int blueValue;
    }

    private static enum XYZ {
        FRANK("The given name of me"), LIU("The family name of me");
        private String context;

        private String getContext() {
            return this.context;
        }
        private XYZ(String context) {
            this.context = context;
        }
        @Override
        public String toString() {
            return this.context;
        }
    }
    private static enum ABC{
        A,B,C,D;
    }
    private static class Reserver {
        public Reserver getReserver() {
            return this;
        }
        public String NE = "abc";
        public String ne = "def";

        public int DIV = 1;
        public int div = 3;

        public Map<String, String> m = new HashMap<String, String>();
        public int add(int a,int b){
            return a + b;
        }
        public int getA(){
            return 99;
        }
        public int getB(){
            return 80;
        }
        Reserver() {
            m.put("NE", "xyz");
        }
    }

}
