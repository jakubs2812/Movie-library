package movie.library.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import movie.library.domain.dto.CreateMovieDto;
import movie.library.domain.dto.MovieCounterDto;
import movie.library.domain.dto.MovieDto;
import movie.library.service.MovieService;

import java.util.List;

@Controller
@RequestMapping(value="/api")
public class MovieApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieApiController.class);

    private final MovieService movieService;

    @Autowired
    public MovieApiController(MovieService movieService) {
        this.movieService = movieService;
    }

    @CrossOrigin
    @GetMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<MovieDto>> getMovies() {
        LOGGER.info("find all movies");

        List<MovieDto> moviesDto = movieService.findAll();
        return new ResponseEntity<>(moviesDto, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> createMovie(@RequestBody CreateMovieDto createMovieDto) {
        LOGGER.info("create movie: {}", createMovieDto);

        movieService.createMovie(createMovieDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @DeleteMapping(value = "/movies/{movieId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteMovie(@PathVariable Long movieId) {
        LOGGER.info("delete movie: {}", movieId);

        movieService.deleteMovie(movieId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/movies/counter", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<MovieCounterDto> countMovies() {
        LOGGER.info("count movies");

        return new ResponseEntity<>(movieService.countMovies(), HttpStatus.OK);
    }
    //
    @CrossOrigin
    @DeleteMapping(value = "movies/deleteMovies/{movieIds}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteMovies(@PathVariable String movieIds) {
        LOGGER.info("delete movie: {}", movieIds);

        movieService.deleteMoviesById(movieIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
