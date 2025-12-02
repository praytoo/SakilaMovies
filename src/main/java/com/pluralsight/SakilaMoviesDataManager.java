package com.pluralsight;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SakilaMoviesDataManager {
        private static Scanner scanner = new Scanner(System.in);

        private DataSource dataSource;

        public SakilaMoviesDataManager(DataSource dataSource){
        this.dataSource = dataSource;
        }
        public String getActor() {
            String input3 = ("What is the first name of the actor you would like to see the movies of?");
            System.out.println(input3);
            String firstName = scanner.nextLine();
            String input = ("What is the last name of the actor you would like to see the movies of?");
            System.out.println(input);
            String lastName = scanner.nextLine();

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM actor WHERE first_name = ? AND last_name = ?;");) {

                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);

                try (ResultSet resultSet = preparedStatement.executeQuery();) {

                    boolean results = false;
                        while (resultSet.next()) {
                            results = true;
                            System.out.println(" Actor ID: " + resultSet.getInt("actor_id") + "\n " + "First Name: " + resultSet.getString("first_name") + "\n " + "Last Name: " + resultSet.getString("last_name") + "\n-----------");
                        }if (!results){
                        System.out.println("No results were found");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return firstName + " " + lastName;
        }
        public String getActorID() {
            String input2 = ("What is the actor ID of the actor you would like to see the movies of?");
            System.out.println(input2);
            String actorId = scanner.nextLine();

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                         "FROM actor AS a " +
                         "JOIN film_actor AS fa " +
                         "ON a.actor_Id = fa.actor_id " +
                         "JOIN film AS f " +
                         "ON fa.film_id = f.film_id " +
                         "WHERE a.actor_id = ? ;");) {

                preparedStatement.setString(1, actorId);

                try (ResultSet resultSet = preparedStatement.executeQuery();) {

                    if (resultSet.next()) {
                        System.out.println("Your matches are: \n");

                        while (resultSet.next()) {
                            System.out.println(" Film ID: " + resultSet.getInt("film_id") + "\n " + "Title: " + resultSet.getString("title") + "\n " + "Description: " + resultSet.getString("description") + "\n " + "Release Year: " + resultSet.getInt("release_year") + "\n " + "Movie Length: " + resultSet.getInt("length") + "\n-----------");
                        }
                    } else {
                        System.out.println("No matches!");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return actorId;
        }

        /*public Actor actor(){
            Actor actor = new Actor();
            return actor;
        }

        public Film actorId(){
            Film actorId = new Film();
            return actorId;
        }
         */
}

