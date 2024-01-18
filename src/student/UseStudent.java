package student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

import static utils.UseHelper.skip;
import static utils.UseHelper.tip;

/**
 * 增删改查
 * */
public class UseStudent {
    // 增加一名student
    public static void addStudent(Connection conn) throws IOException, SQLException {
        // 读取 => 数据库插入 => 返回
        tip("添加");

        System.out.println("请输入 “班级-学号-姓名-学院-专业-性别”：");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        Student student = new Student();
        str = reader.readLine();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM user");
        String maxId = "";
        if (resultSet.next()) {
            maxId = resultSet.getString(1);
        }
        String stu[] = str.split("-");
        student.setId(Integer.parseInt(maxId) + 1 + "");
        System.out.println(student.getId());
        student.setClassname(stu[0]);
        student.setClassnum(stu[1]);
        student.setName(stu[2]);
        student.setSchoolname(stu[3]);
        student.setMajor(stu[4]);
        student.setGender(stu[5]);

        String sql = "insert into user(id,classname,classnum,name,schoolname,major,gender) value(?,?,?,?,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, student.getId());
        pstm.setString(2, student.getClassName());
        pstm.setString(3, student.getClassnum());
        pstm.setString(4, student.getName());
        pstm.setString(5, student.getSchoolName());
        pstm.setString(6, student.getMajor());
        pstm.setString(7, student.getGender());
        pstm.executeUpdate();
        student.getAll();
        System.out.println("OK");
        pstm.close();
        skip();
    }
    // 根据id删除一名student
    public static void delStudent(Connection conn) throws SQLException {
        tip("删除");
        System.out.println("请输入 “id”：");
        try (Scanner scanner = new Scanner(System.in)) {
          String id = scanner.next();
          String sql = "delete from user where id=?";
          PreparedStatement pstm = conn.prepareStatement(sql);
          pstm.setString(1, id);
          pstm.execute();
          pstm.close();
        }
        tip("删除成功");
        skip();
    }

    // 根据id以及相关信息修改一名student的信息
    public static void putStudent(Connection conn) throws IOException, SQLException {
        tip("修改");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入修改内容（格式为 xxx:xxx xxx:xxx）");
        String str = reader.readLine();
        System.out.println("请输入id：");
        String id = reader.readLine();

        StringBuilder baseSql = new StringBuilder("update user set ");

        String[] attrs = str.split(" ");
        int queryLength = attrs.length;
        int counter = 0;
        String[] queryList = new String[queryLength];
        for(String item:attrs) {
            System.out.println(item);
            String[] res = item.split(":");
            String subStr = res[0] + "='" + res[1] + "', ";
            queryList[counter++] = subStr;
        }
        for (String item:queryList){
            baseSql.append(item);
        }
        String sql = String.valueOf(baseSql).substring(0, baseSql.length() - 2) + " where id=" + id;
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.executeUpdate();
        pstm.close();
        tip("修改成功");
        skip();
    }

    // 获取所有student的信息
    public static void getAll(Connection conn) throws SQLException {
        tip("获取全部");
        String sql = "select * from user";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
            System.out.println(
                    "id:" + result.getString(1) + "\t"
                            + "classname:" + result.getString(2) + "\t"
                            + "classnum:" + result.getString(3) + "\t"
                            + "name:" + result.getString(4) + "\t"
                            + "schoolname:" + result.getString(5) + "\t"
                            + "major:" + result.getString(6) + "\t"
                            + "gender:" + result.getString(7)
            );
        }
        pstm.close();
        tip("查询完毕");
        skip();

    }

    // 根据id查询单条student信息
    public static void querySingle(Connection conn) throws SQLException, IOException {
        tip("根据id查找单条");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入查询id");
        String str = reader.readLine();
        String sql = "select * from user where id=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1,str);
        ResultSet result = pstm.executeQuery();
        while (result.next()) {
            System.out.println(
                    "id:" + result.getString(1) + "\t"
                            + "classname:" + result.getString(2) + "\t"
                            + "classnum:" + result.getString(3) + "\t"
                            + "name:" + result.getString(4) + "\t"
                            + "schoolname:" + result.getString(5) + "\t"
                            + "major:" + result.getString(6) + "\t"
                            + "gender:" + result.getString(7)
            );
        }
        pstm.close();
        tip("查询完毕");
        skip();

    }
}
