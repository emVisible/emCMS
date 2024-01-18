package user;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static utils.UseHelper.skip;
import static utils.UseHelper.tip;

public class User {
    // 执行后在terminal中输入name和password字段, 查询相应用户, 并核对的name和password是否正确
    public static boolean login(Connection conn) throws SQLException, IOException {
        tip("登录");
        // 获取输入
        ArrayList<String> msg = input();
        // 获取用户信息
        String name = msg.get(0);
        String password = msg.get(1);
        String sqlStr = "select * from admin where name=? and password=?"; // 创建SQL语句

        PreparedStatement pst = conn.prepareStatement(sqlStr); // 创建pst对象
        pst.setString(1, name); // 设置参数
        pst.setString(2, password);
        ResultSet result = pst.executeQuery(); //执行SQL语句

        getMsg(result);
        pst.close();
        skip();
        return true;
    }

    // 封装的子节流输入
    public static ArrayList<String> input() throws IOException {
        ArrayList<String> user = new ArrayList<>();
        System.out.println("请输入用户名与密码:");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();

        int pose = str.indexOf(' ');
        String username = str.substring(0, pose);
        String password = str.substring(pose + 1);

        user.add(username);
        user.add(password);

        System.out.println("\tusername:" + username + "\n" + "\tpassword:" + password);
        return user;
    }

    // 获取信息
    public static void getMsg(ResultSet result) throws SQLException {
        ArrayList<String> arr = new ArrayList<>();
        while (result.next()){
            arr.add(result.getString(1));
            arr.add(result.getString(2));
        }
        result.close();
        tip(arr.get(0));
    }
}
