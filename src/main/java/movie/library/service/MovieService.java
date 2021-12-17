package movie.library.service;

import movie.library.domain.dto.CreateMovieDto;
import movie.library.domain.dto.MovieCounterDto;
import movie.library.domain.dto.MovieDto;

import java.util.List;

public interface MovieService {

    List<MovieDto> findAll();

    List<MovieDto> findAllByYearDesc();

    void createMovie(CreateMovieDto createMovieDto);

    void deleteMovie(Long movieId);

    void deleteMoviesById(String movieIds);

    MovieCounterDto countMovies();
}
