package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

public class Actor {
    public static void main(String[] args) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup");

        SakilaMoviesDataManager dataManager = new SakilaMoviesDataManager(dataSource);

        String actor = dataManager.getActor();

        System.out.println(actor);
    }
}
