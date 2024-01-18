package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static user.User.login;

public class UseConnect {
    // 初始化mysql链接
    public static void init() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }

    // 建立mysql链接
    public static Connection createConnection() throws SQLException {
        return (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/homework?characterEncoding=utf8", "root", "preview");
    }

    public static boolean verify(Connection conn) throws SQLException, IOException {
        boolean status = login(conn);
        if (!status){
            conn.close();
            return false;
        }
        return true;
    }
}
