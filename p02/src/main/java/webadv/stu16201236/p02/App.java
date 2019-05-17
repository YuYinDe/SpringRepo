package webadv.stu16201236.p02;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String pathName = "AccountFile";
        //获取用户输入账号、密码
        Scanner input = new Scanner(System.in);
        System.out.println("输入账号:");
        String username = input.next();
        System.out.println("输入密码:");
        String password = input.next();
        //如果没有输入内容，就返回出错误，并显示信息
        if (username.length() < 1 || password.length() < 1) {
            System.err.println("Please provide an input!");
            System.exit(0);
        }
        String encryptPassword = sha256hex(password);  //将用户输入的密码加密

//        System.out.println(sha256hex("123456"));
        String correctUsername,correctPassword;
        try (FileReader reader = new FileReader(pathName);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                correctUsername = line.substring(0,line.indexOf(":"));
                correctPassword = line.substring(line.indexOf(":") + 1);
                if(username.equals(correctUsername)){
                    if (encryptPassword.equals(correctPassword)) {
                        System.out.println("登录成功！");
                        System.exit(0);
                    }else {
                        System.out.println("密码错误！");
                        System.exit(0);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("输入账号不存在！");


    }
    public static String sha256hex(String input) {
        return DigestUtils.sha256Hex(input);
    }
}

