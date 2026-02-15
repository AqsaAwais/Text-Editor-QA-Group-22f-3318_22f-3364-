package data;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dal.DatabaseConnection;

public class DatabaseConnectionTest {

    @Test
    void testSingletonInstance() {
        DatabaseConnection instance1 = DatabaseConnection.getInstance();
        DatabaseConnection instance2 = DatabaseConnection.getInstance();

        // Both references must point to the same object
        assertSame(instance1, instance2, "DatabaseConnection instances are not the same!");
    }


    
    @Test
    void testCloseConnection() {
        DatabaseConnection db = DatabaseConnection.getInstance();
        db.closeConnection();
        
        try {
            assertTrue(db.getConnection() == null || db.getConnection().isClosed(),
                "Connection should be closed or null after closeConnection()");
        } catch (SQLException e) {
            fail("SQLException thrown while checking if connection is closed: " + e.getMessage());
        }
    }

}
