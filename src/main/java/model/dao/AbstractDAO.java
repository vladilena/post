package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static model.dao.GlobalConstants.DataBaseConstants.*;

public class AbstractDAO implements InterfaceDAO {

    @Override
    public Connection getConnection() throws SQLException {
        System.out.println("getConnection");
        return DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }
}
