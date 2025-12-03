package com.pluralsight;

import java.util.List;

public class ActorID {
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
