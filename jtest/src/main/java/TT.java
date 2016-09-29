import java.sql.*;

/**
 * Created by YANLL on 2016/08/31.
 */
public class TT {
    public static void main(String[] args) throws Exception {
//        XmlMapper xmlMapper = new XmlMapper();
//        Object n = xmlMapper.readTree(new FileInputStream("D://ss.txt"));
//        System.out.print("");


        Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://10.47.136.156:8066/db_bao?user=mysql&password=aN#hA28VIEb_";
        //myDB为数据库名
        Connection conn = DriverManager.getConnection(url);


        ResultSet result = null;//表示接受数据库查询到的结果


        Statement stmt = conn.createStatement();//tatement接口需要通过connection接口进行实例化操作
        result = stmt.executeQuery("select * from b_customer");//执行sql语句，结果集放在result中


        PreparedStatement ps = conn.prepareStatement("select * from b_customer");//tatement接口需要通过connection接口进行实例化操作
        result = ps.executeQuery();//执行sql语句，结果集放在result中


        System.out.print("");
    }
}
