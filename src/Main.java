import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String userName = "root";
        String password = "Guitarist1205";
        String connectionURL = "jdbc:mysql://localhost:3306/sn";
        Class.forName("com.mysql.jdbc.Driver");

        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(connectionURL, userName, password);
             Statement statement = connection.createStatement()) {
            System.out.println("Connection to database successful");
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");

                System.out.print(id);
                System.out.print(") " + name);
                System.out.println(" " + surname);
            }
            resultSet.close();

            int idRequest = scanner.nextInt();

            String sqlRequest = "select name, surname, age from users where id = " + idRequest;
            resultSet = statement.executeQuery(sqlRequest);

            resultSet.next();
            System.out.print(resultSet.getString("name"));
            System.out.print(" " + resultSet.getString("surname"));
            System.out.println(" " + resultSet.getInt("age"));
        }
    }
}
