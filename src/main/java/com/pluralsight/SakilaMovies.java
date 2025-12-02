package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SakilaMovies {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup");

        String input = ("What is the last name of the actor you would like to see the movies of?");
        System.out.println(input);
        String lastName = scanner.nextLine();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM actor WHERE last_name = ?;");) {

            preparedStatement.setString(1, lastName);

            try (ResultSet resultSet = preparedStatement.executeQuery();) {

                while (resultSet.next()) {
                    System.out.println(" Actor ID: " + resultSet.getInt("actor_id") + "\n " + "First Name: " + resultSet.getString("first_name") + "\n " + "Last Name: " + resultSet.getString("last_name") + "\n " + "Last Update: " + resultSet.getDate("last_update") + "\n-----------");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String input2 = ("What is the first name of the actor you would like to see the movies of?");
        System.out.println(input2);
        String firstName = scanner.nextLine();
        String input3 = ("What is the last name of the actor you would like to see the movies of?");
        System.out.println(input3);
        String lastName2 = scanner.nextLine();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                     "FROM actor AS a " +
                     "JOIN film_actor AS fa " +
                     "ON a.actor_Id = fa.actor_id " +
                     "JOIN film AS f " +
                     "ON fa.film_id = f.film_id " +
                     "WHERE first_name = ? AND last_name = ?;");) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName2);

            try (ResultSet resultSet = preparedStatement.executeQuery();) {

                if (resultSet.next()) {
                    System.out.println("Your matches are: \n");

                        while (resultSet.next())
                        {
                            System.out.println(" Actor ID: " + resultSet.getInt("actor_id") + "\n " + "First Name: " + resultSet.getString("first_name") + "\n " + "Last Name: " + resultSet.getString("last_name") + "\n " + "Movie Title: " + resultSet.getString("title") + "\n-----------");
                        }
                }else{
                    System.out.println("No matches!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}