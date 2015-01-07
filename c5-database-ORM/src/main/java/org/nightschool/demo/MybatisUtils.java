package org.nightschool.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Thoughtworks on 12/30/14.
 */
public class MybatisUtils {

    private static SqlSessionFactory factory;

    public static SqlSessionFactory getFactory() {
        String resource = "config/database.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(factory == null)
        {
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        }

        return factory;
    }


    public static SqlSession getSession() {
        return getFactory().openSession();
    }
}
