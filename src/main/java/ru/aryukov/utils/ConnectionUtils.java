package ru.aryukov.utils;

import ru.aryukov.domain.Tarif;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectionUtils {

    private static Connection connection;
    private static ConnectionUtils instance;
    private static DataSource dataSource;

    public ConnectionUtils() {
    }

    public static synchronized ConnectionUtils getInstance() {
        if (instance == null) {
            try {
                instance = new ConnectionUtils();
                Context ctx = new InitialContext();
                instance.dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/telecom");
                connection = dataSource.getConnection();
            } catch (NamingException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    public List getTarifs() throws SQLException {
        List tarifs = new ArrayList();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM \"TARIF_E\";");
        while (rs.next()) {
            Tarif tarif = new Tarif();
            tarif.setId(rs.getInt(1));
            tarif.setName(rs.getString(2));
            tarif.setPrice(rs.getInt(3));
            tarifs.add(tarif);
        }
        rs.close();
        stmt.close();
        return tarifs;
    }
}
