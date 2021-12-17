package movie.library.service.serviceImpl;

import movie.library.domain.repository.MovieRepository;
import movie.library.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import movie.library.domain.converter.Converter;
import movie.library.domain.dto.CreateMovieDto;
import movie.library.domain.dto.MovieCounterDto;
import movie.library.domain.dto.MovieDto;
import movie.library.domain.entity.Movie;
import movie.library.domain.mapper.MovieListMapper;
import movie.library.domain.mapper.MovieMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

    private final MovieRepository movieRepository;
    private final Converter<List<Movie>, List<MovieDto>> movieListMapper;
    private final Converter<CreateMovieDto, Movie> movieMapper;
    private final Converter<Long, MovieCounterDto> movieCounterMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository,
                            MovieListMapper movieListMapper,
                            MovieMapper movieMapper,
                            Converter<Long, MovieCounterDto> movieCounterMapper) {

        this.movieRepository = movieRepository;
        this.movieListMapper = movieListMapper;
        this.movieMapper = movieMapper;
        this.movieCounterMapper = movieCounterMapper;
    }

    @Override
    public List<MovieDto> findAll() {
        List<Movie> movies = movieRepository.findAll();
        return movieListMapper.convert(movies);
    }

    @Override
    public List<MovieDto> findAllByYearDesc() {
        List<Movie> movies = movieRepository.findByOrderByYearDesc();
        return movieListMapper.convert(movies);
    }

    @Override
    public void createMovie(CreateMovieDto createMovieDto) {
        Movie movie = movieMapper.convert(createMovieDto);
        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long movieId) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        movieOptional.ifPresent(movieRepository::delete);
    }

    @Override
    @Transactional
    public void deleteMoviesById(String movieIds) {
        String[] splitedMovieIds = movieIds.split(",");
        List<Long> parsedMovieIds = Arrays.stream(splitedMovieIds)
                .map(Long::parseLong)
                .collect(Collectors.toList());
        movieRepository.deleteInBatch(parsedMovieIds);
    }

    @Override
    public MovieCounterDto countMovies() {
        return movieCounterMapper.convert(movieRepository.count());
    }
}
