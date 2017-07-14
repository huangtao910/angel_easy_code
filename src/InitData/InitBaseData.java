package InitData;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Administrator on 2017/7/13.
 */
public class InitBaseData {

    public static String driverName = "";
    public static String dbUrl = "";
    public static String dbName = "";
    public static String dbUsername = "";
    public static String dbPassword = "";
    public static String projectName = "";
    public static String projectComment = "";
    public static String packageName = "";
    public static String path_1 = "";
    public static String path_2 = "";
    public static String authorName = "";
    public static String templetPath = "";
    public static String tablleTempletPath = "";
    public static String columnTempletPath = "";
    public static String codePath = "";


    static {

        try {
            Properties properties = new Properties();
            InputStream in = InitBaseData.class.getResourceAsStream("/jdbc.properties");
            properties.load(in);
            driverName = (String)properties.get("jdbc.driverClass");
            dbUrl = (String)properties.get("jdbc.url");
            dbName = (String)properties.get("jdbc.dbName");
            dbUsername = (String)properties.get("jdbc.username");
            dbPassword = (String)properties.get("jdbc.password");
            projectName = (String)properties.get("projectName");
            projectComment = (String)properties.get("projectComment");
            packageName = (String)properties.get("packageName");
            path_1 = (String)properties.get("path_1");
            path_2 = (String)properties.get("path_2");
            authorName = (String)properties.get("author");

            String property = System.getProperty("user.dir");

            //变量定义
            templetPath = property + "\\template\\SSH2模板\\框架模板";//框架模板所在目录
            tablleTempletPath = property + "\\template\\SSH2模板\\表级模板";//表级模板所在目录
            columnTempletPath = property + "\\template\\SSH2模板\\列级模板";//列级模板所在目录
            codePath = property + "\\templateCode";//代码输出目录
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Connection getConnection()
    {
        try {
            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }


    public static void closeAll(ResultSet rs,Statement stmt,Connection conn)
    {
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(InitBaseData.dbName);
    }
}
