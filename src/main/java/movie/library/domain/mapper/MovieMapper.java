package movie.library.domain.mapper;

import movie.library.domain.converter.Converter;
import movie.library.domain.entity.Movie;
import org.springframework.stereotype.Component;
import movie.library.domain.dto.CreateMovieDto;

@Component
public class MovieMapper implements Converter<CreateMovieDto, Movie> {

    @Override
    public Movie convert(CreateMovieDto createMovieDto) {

        return new Movie.Builder()
                .image(createMovieDto.getImage())
                .title(createMovieDto.getTitle())
                .year(createMovieDto.getYear())
                .build();
    }
}
