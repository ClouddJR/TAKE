package com.example.demo.models;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;

@Component
@SessionScope
public class Cart {
    private Set<Movie> movies = new HashSet<>();
    private Double price;

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public Double getPrice() {
        return movies.stream().mapToDouble(Movie::getPrice).sum();
    }
}
