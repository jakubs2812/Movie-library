package movie.library.domain.mapper;

import org.springframework.stereotype.Component;
import movie.library.domain.converter.Converter;
import movie.library.domain.dto.MovieDto;
import movie.library.domain.entity.Movie;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieListMapper implements Converter<List<Movie>, List<MovieDto>> {
    @Override
    public List<MovieDto> convert(List<Movie> from) {

        return from.stream()
                .map(movie -> new MovieDto.Builder()
                        .movieId(movie.getMovieId())
                        .title(movie.getTitle())
                        .image(movie.getImage())
                        .year(movie.getYear())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
