package event;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static student.UseStudent.*;
import static utils.UseConnect.*;
import static utils.UseHelper.notFound;
import static utils.UseHelper.skip;

public class EventLoop {
  public static void run() throws ClassNotFoundException, SQLException, IOException {
    System.out.println("初始化...");
    init();
    System.out.println("建立数据库链接...");
    Connection conn = createConnection();
    skip();
    loop(conn);
  }

  public static void loop(Connection conn) throws IOException, SQLException {
    try (Scanner scanner = new Scanner(System.in)) {
      boolean running = true;
      System.out.println("\n🙂欢迎来到ZISU学生管理系统( 2021级计算机1班 )\n");
      boolean status = verify(conn);
      if (!status) {
        System.out.println("用户名或密码输入错误，请重试");
      } else {
        while (running) {
          System.out.print("请输入指令\n\t增(add)\n\t删(del)\n\t改(put)\n\t查(query/getAll)\n\t退出(exit)\n>>>");
          String command = scanner.nextLine();
          switch (command) {
            case "add" -> addStudent(conn);
            case "del" -> delStudent(conn);
            case "put" -> putStudent(conn);
            case "query" -> querySingle(conn);
            case "getAll" -> getAll(conn);
            case "exit" -> {
              running = false;
              conn.close();
              System.out.println("Bye");
            }
            default -> notFound();
          }
        }
      }
    }
  }
}
