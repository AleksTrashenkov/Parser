import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Path;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

public class Mysql {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Mysqli mysqli = new Mysqli();
        mysqli.putPhoto("Картинка", "C:\\Users\\Aleks\\OneDrive\\Рабочий стол\\Имя в космос.PNG");
    }

    public static class Mysqli {
        private static String url = "jdbc:sqlserver://miniserver\\SQLHOMESERVER;" + "databaseName=AdventureWorks;" + //подключаемся к нужной БД
                "encrypt=false;" + "trustServerCertificate=false;" +
                "hostNameInCertificate=localhost;";
        public Mysqli() throws ClassNotFoundException, SQLException {
        }
        public static void putPhoto(String name, String path){
            try {
                File file = new File(path);
                int size = (int) file.length();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                Connection con = DriverManager.getConnection(url, "sa", "20091991Av!@");

                String sql = "Insert into dbo.pictures (name, photo) Values (?, ?)";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, name);
                preparedStatement.setBinaryStream(2, bufferedInputStream, size);
                ResultSet res = preparedStatement.executeQuery(); //выполняем запрос
                while (res.next()) {
                    System.out.println(res.getString(1) + res.getString(2));
                }
                con.commit();
            }catch (Exception x) {
                System.out.println(x);
            }
        }
    }














/*    public static class Mysql1 {

        public Mysql1() throws IOException, SQLException, ClassNotFoundException {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://miniserver\\SQLHOMESERVER;" + "databaseName=AdventureWorks;" + //подключаемся к нужной БД
                    "encrypt=false;" + "trustServerCertificate=false;" +
                    "hostNameInCertificate=localhost;";
            try (
                    Connection con = DriverManager.getConnection(url, "sa", "20091991Av!@");
                    Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    //параметры в скобках указаны для возможности редактирования данных в БД
                    CallableStatement callableStatement = con.prepareCall("{call dbo.uspGetEmployeeManagers(?)}"); //соединение для вызова хранимо процедуры
            ) {
                System.out.println(con);
                System.out.println(con.getCatalog());
                DatabaseMetaData metaData = con.getMetaData();
                ResultSet rs = metaData.getFunctions(null, null, "usp%");
                System.out.println(metaData.getDatabaseProductName() + metaData.getDatabaseProductVersion());

                while (rs.next()) {
                    System.out.println(rs.getString(3));
                } //просмотр списка хранимых процедур

                while (rs.next()) {
                    System.out.println(rs.getString("TABLE_CAT")); //список БД на сервере
                }

                                DatabaseMetaData metaData = con.getMetaData();
                ResultSet rs = metaData.getTables(null, null, null, null);
                while (rs.next()) {
                    System.out.println(rs.getString(1) + " " + rs.getString(3)); //список Таблиц в БД на сервере
                }


                ResultSet res = metaData.getTables(null, "dbo", null, null);

                while (res.next()) {
                    System.out.printf("%s %s %s %s\n",
                            res.getString(1),
                            res.getString(2),
                            res.getString(3),
                            res.getString(4)); //список таблиц в БД AdventureWorks
                }

                ResultSet res2 = statement.executeQuery("select top 18 * from Person.Contact");//запрос данных из указанной таблицы
                //statement.setMaxRows(5);//установка количества выборки можно ограничить в самом запросе как указано ниже
                //res2.first(); //переходим на первую строку
                res2.absolute(5); //переходим на 5 строку
                res2.updateString(4, "Алексей"); //меняем 4 столбец 5 строки на значение в кавычках
                res2.updateRow(); //сохраняем изменения в базу
                res2.beforeFirst(); //возвращаемся на 0 строку чтобы прочитать все данные в цикле ниже

*//*            res2.moveToInsertRow();
            res2.updateString(1, "1");
            res2.updateString(4, "Lesha");
            res2.insertRow();*//* //пример вставки строк в таблицу

                while (res2.next()) {
                    System.out.printf("%s %s %s %s %s\n",
                            res2.getString(1),
                            res2.getString(3),
                            res2.getString(4),
                            res2.getString(6),
                            res2.getString(8));
                }

                System.out.println("вызов хранимой процедуры для получения данных");
                callableStatement.setInt(1, 42); //вызов хранимой процедуры
                ResultSet resultSet = callableStatement.executeQuery();
                while (resultSet.next()) {
                    System.out.printf("%s %s %s %s %s %s\n",
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(7));
                }
            }
        }
    }*/
}