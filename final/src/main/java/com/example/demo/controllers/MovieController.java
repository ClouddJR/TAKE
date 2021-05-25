package com.example.demo.controllers;

import com.example.demo.models.Cart;
import com.example.demo.models.Movie;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MovieController {

    private final Cart cart;
    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;

    public MovieController(Cart cart, MovieRepository movieRepository, CategoryRepository categoryRepository) {
        this.cart = cart;
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public String index(@RequestParam(required = false) String category, Model model) {
        List<Movie> movies = movieRepository.findAll();
        if (category != null && !category.isEmpty() && !category.equals("ALL")) {
            movies = movies.stream()
                    .filter(movie -> movie.getCategory().getName().equals(category))
                    .collect(Collectors.toList());
        }
        model.addAttribute("movies", movies);
        model.addAttribute("cart", cart);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("currentCategory", category);
        return "index";
    }

    @PostMapping("/cart/{id}/delete")
    public String removeFromCart(@PathVariable("id") long id) {
        movieRepository.findById(id).ifPresent(movie -> {
            if (cart.getMovies().stream().anyMatch(movieInCart -> movieInCart.getId().equals(movie.getId()))) {
                cart.setMovies(cart.getMovies().stream().filter(movieInCart -> !movieInCart.getId().equals(movie.getId()))
                        .collect(Collectors.toSet()));
            }
        });
        return "redirect:/";
    }

    @PostMapping("/cart/{id}")
    public String addToCart(@PathVariable("id") long id, RedirectAttributes redirectAttrs) {
        movieRepository.findById(id).ifPresent(movie -> {
            if (cart.getMovies().stream().noneMatch(movieInCart -> movieInCart.getId().equals(movie.getId()))) {
                cart.getMovies().add(movie);
            } else {
                redirectAttrs.addFlashAttribute("message", "Item already in cart");
            }
        });
        return "redirect:/";
    }

    @RequestMapping("/movie/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        Movie movie = movieRepository.findById(id).orElse(null);
        model.addAttribute("movie", movie);
        return "show";
    }
}
