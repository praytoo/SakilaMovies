package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

public class Film {

    public static <ActorID> void main(String[] args) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup");

        SakilaMoviesDataManager dataManager = new SakilaMoviesDataManager(dataSource);

        List<ActorID> actorId = (List<ActorID>) dataManager.getActorID();

        actorId.forEach(System.out::println);
    }
}
