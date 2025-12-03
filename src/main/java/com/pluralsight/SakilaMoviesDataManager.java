package com.pluralsight;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SakilaMoviesDataManager {
        private static Scanner scanner = new Scanner(System.in);

        private DataSource dataSource;

        public SakilaMoviesDataManager(DataSource dataSource){
        this.dataSource = dataSource;
        }
        public List<Actor> getActor() {
            List<Actor> actor = new ArrayList<>();
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
                            int actor_id = resultSet.getInt("actor_id");
                            String first_name = resultSet.getString("first_name");
                            String last_name = resultSet.getString("last_name");

                            Actor actor1 = new Actor(actor_id, first_name, last_name);
                            actor.add(actor1);
                        }if (!results){
                        System.out.println("No results were found");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return actor;
        }
        public List<ActorID> getActorID() {
            List<ActorID> actorId = new ArrayList<>();
            String input2 = ("What is the actor ID of the actor you would like to see the movies of?");
            System.out.println(input2);
            int actorId2 = Integer.parseInt(scanner.nextLine());

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                         "FROM actor AS a " +
                         "JOIN film_actor AS fa " +
                         "ON a.actor_Id = fa.actor_id " +
                         "JOIN film AS f " +
                         "ON fa.film_id = f.film_id " +
                         "WHERE a.actor_id = ? ;");) {

                preparedStatement.setInt(1, actorId2);

                try (ResultSet resultSet = preparedStatement.executeQuery();) {

                    boolean results = false;
                        while (resultSet.next()) {
                            results = true;
                            int film_Id = resultSet.getInt("film_id");
                            String title = resultSet.getString("title");
                            String description = resultSet.getString("description");
                            int release_year = resultSet.getInt("release_year");
                            int movie_length = resultSet.getInt("length");

                            ActorID actorID = new ActorID(film_Id, title, description, release_year, movie_length);
                            actorID.add(actorId);
                        }
                    if (!results){
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
         */
}

