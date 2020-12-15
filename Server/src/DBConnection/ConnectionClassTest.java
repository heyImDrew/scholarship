package DBConnection;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class ConnectionClassTest {

    @Test
    public void is_connection() throws ClassNotFoundException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        boolean actual = ConnectionClass.is_connection(connection);
        Assert.assertEquals(true, actual);
    }
}