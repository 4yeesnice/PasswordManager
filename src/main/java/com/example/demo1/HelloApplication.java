package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.password4j.Password;

import java.io.*;
import java.sql.*;
import java.util.Scanner;
import java.util.SortedMap;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    public static int id;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        launch(args);








//        String host = "jdbc:postgresql://localhost:5432/PasswordManager";
//        String username = "postgres";
//        String password = "admin";
//        try {
//
//            Connection conn = DriverManager.getConnection(host, username, password);
//            System.out.println("Connected to PostgreSQL database");
//
//            Statement statement = conn.createStatement();
//            System.out.println("WELCOME TO THE PASSWORD MANAGER");
//            String selectQuery = "SELECT * FROM password";
//            ResultSet resultSet = statement.executeQuery(selectQuery);
//            while (resultSet.next()) {
//                id = resultSet.getInt("id");
//            }
//
//
//
//            System.out.println(id);
//
//            String enterPassword;
//            String inputFile = "hashedpassword.txt";
//            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
//            String line = reader.readLine();
//
//            if (line == null || line.trim().is    Empty()){
//                System.out.print("Enter new password: ");
//                enterPassword = sc.nextLine();
//                String hashedPassword = Password.hash(enterPassword).withBcrypt().getResult();
//
//                try (FileWriter fw = new FileWriter(inputFile)){
//                    fw.write(hashedPassword);
//                }
//
//                System.out.println("Password saved successfully.");
//
//
//                while (true){
//                    System.out.println("Choose commands:\n 1. Show Passwords, 2. Add Password, 3. Delete Row, 4. Update Password");
//                    int command = sc.nextInt();
//                    sc.nextLine();
//                    switch (command){
//                        case 1:
//                            // Show password
//                            getResult(statement);
//                            break;
//                        case 2:
//                            // Add password
//                            insertPassword(conn);
//                            break;
//                        case 3:
//                            // Delete row
//                            deleteRow(conn);
//                            break;
//                        case 4:
//                            // Update row
//                            updateRow(conn);
//                            break;
//                    }
//                }
//
//
//
//            }else {
//                System.out.print("Enter password: ");
//                enterPassword = sc.nextLine();
//                boolean isMatch = Password.check(enterPassword, line).withBcrypt();
//                if (isMatch){
//                    System.out.println("You're good to go");
//
//                    while (true){
//                        System.out.println("Choose commands:\n 1. Show Passwords, 2. Add Password, 3. Delete Row, 4. Update Password");
//                        int command = sc.nextInt();
//                        sc.nextLine();
//                        switch (command){
//                            case 1:
//                                // Show password
//                                getResult(statement);
//                                break;
//                            case 2:
//                                // Add password
//                                insertPassword(conn);
//                                break;
//                            case 3:
//                                // Delete row
//                                deleteRow(conn);
//                                break;
//                            case 4:
//                                // Update row
//                                updateRow(conn);
//                                break;
//                        }
//                    }
//                }else {
//                    System.out.println("Wrong password");
//                }
//            }
//
//        } catch (SQLException | IOException e) {
//            System.out.println("Failed to connect to PostgreSQL database");
//            e.printStackTrace();
//        }
    }
    public static void getResult(Statement statement) throws SQLException {
        String selectQuery = "SELECT * FROM password";
        ResultSet resultSet = statement.executeQuery(selectQuery);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String passw = resultSet.getString("password");
            String login = resultSet.getString("login");
            System.out.println("ID: " + id + ", Name: " + name + ", Password: " + passw + ", Login: " + login);
        }
    }
    public static void insertPassword(Connection connection) throws SQLException {

        System.out.println("Enter name: ");
        String name = sc.nextLine();
        System.out.println("Enter password: ");
        String password = sc.nextLine();
        System.out.println("Enter login: ");
        String login = sc.nextLine();

        String insertQuery = "INSERT INTO password (id ,name, password, login) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        id++;
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, password);
        preparedStatement.setString(4, login);
        preparedStatement.executeUpdate();

    }


    public static void deleteRow(Connection connection) throws SQLException {
        String sql = "DELETE FROM password WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.print("Enter id of the row you want to delete: ");
        int id_to_delete = sc.nextInt();
        preparedStatement.setInt(1, id_to_delete);

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Row " + id_to_delete + "is succesfully deleted");
        } else {
            System.out.println("No rows were deleted.");
        }

    }

    public static void updateRow(Connection connection) throws SQLException{
        String sql = "UPDATE password SET password = ? WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.print("Enter id of the row you want to update: ");
        int id_to_update = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new password: ");
        String new_pasword = sc.nextLine();
        preparedStatement.setString(1, new_pasword);
        preparedStatement.setInt(2, id_to_update);

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("A row was updated successfully.");
        } else {
            System.out.println("No rows were updated.");
        }

    }
}