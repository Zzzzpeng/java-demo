import org.junit.Test;

import java.sql.*;

public class jdbcUtil {
    @Test
    public void jdbcTest() throws SQLException {
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            //1.注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            //2.建立连接
            //方法一  参数一：协议+访问数据库，参数二：用户名，参数三：密码
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hsh_car?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC", "root", "root");

            //方法二
//            DriverManager.getConnection("jdbc:msql://localhost/student?user=root&password=password");

            //3.创建statement，跟数据库打交道一定需要这个对象
            st = connection.createStatement();

            //4.执行查询
            String sql = "select * from stu";
            rs = st.executeQuery(sql);

            //5.遍历查询每一条记录
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                System.out.println("id = " + id + "; name a= " + name + "; age = " + age);
            }
            //进行资源释放
            rs.close();
            st.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
