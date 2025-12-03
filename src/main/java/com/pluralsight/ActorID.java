package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

public class ActorID {
    public static void main(String[] args) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup");

        SakilaMoviesDataManager dataManager = new SakilaMoviesDataManager(dataSource);

        List<ActorID> actorId = dataManager.getActorID();

        actorId.forEach(System.out::println);
    }
    private int film_id;
    private String title;
    private String description;
    private int release_year;
    private int movie_length;

    @Override
    public String toString() {
        return "Film{" +
                "film_id=" + film_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", release_year=" + release_year +
                ", movie_length=" + movie_length +
                '}';
    }
    public ActorID(int filmId, String title, String description, int releaseYear, int movieLength) {
        this.film_id = filmId;
        this.title = title;
        this.description = description;
        this.release_year = releaseYear;
        this.movie_length = movieLength;
    }

    public void add(List<ActorID> actorId) {
        actorId.add(this);
    }
}
