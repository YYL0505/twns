package integration;

import org.junit.Test;
import org.nightschool.dao.JDBCService;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JDBCServiceIntegrationTest
{

    @Test
    public void shouldGetDataFromDatabase() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
    {
        JDBCService jdbcService = new JDBCService();
        ResultSet result = jdbcService.select("select * from item");
        result.next();
        assertThat(result.getString("name"), is("ttt"));
    }

    @Test
    public void shouldInsertDataToDatabase() throws Exception {
        JDBCService jdbcService = new JDBCService();
        Boolean result = jdbcService.insert("insert into item(name,img_url,description,price,count) values('ttt','####','&&&',12,10)");

        ResultSet select = jdbcService.select("select * from item where name='ttt'");
        select.next();

        assertThat(select.getString("name"), is("ttt"));

    }
}
