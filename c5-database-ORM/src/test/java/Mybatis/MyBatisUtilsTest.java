package Mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.nightschool.demo.MybatisUtils;

/**
 * Created by Thoughtworks on 1/10/15.
 */
public class MyBatisUtilsTest {
    @Test
    public void shouldGetFactory() {
        SqlSessionFactory factory= MybatisUtils.getFactory();


    }
}
