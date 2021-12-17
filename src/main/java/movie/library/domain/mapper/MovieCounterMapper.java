package movie.library.domain.mapper;

import org.springframework.stereotype.Component;
import movie.library.domain.converter.Converter;
import movie.library.domain.dto.MovieCounterDto;

@Component
public class MovieCounterMapper implements Converter<Long, MovieCounterDto> {

    @Override
    public MovieCounterDto convert(Long counter) {
        return new MovieCounterDto.Builder()
                .counter(counter)
                .build();
    }
}
