package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

public class Actor {
    public static void main(String[] args) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup");

        SakilaMoviesDataManager dataManager = new SakilaMoviesDataManager(dataSource);

        List<Actor> actor = dataManager.getActor();

        actor.forEach(System.out::println);
    }
    private int actor_id;
    private String first_name;
    private String last_name;

    @Override
    public String toString() {
        return "Actor{" +
                "actor_id=" + actor_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }

    public Actor(int actorId, String firstName, String lastName) {
        this.actor_id = actorId;
        this.first_name = firstName;
        this.last_name = lastName;
    }
}
