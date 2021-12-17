package movie.library.domain.mapper;

import org.springframework.stereotype.Component;
import movie.library.domain.converter.Converter;
import movie.library.domain.dto.FileDto;
import movie.library.domain.dto.MovieDto;

import java.util.Date;
import java.util.List;

@Component
public class FileMapper implements Converter<List<MovieDto>, FileDto> {

    @Override
    public FileDto convert(List<MovieDto> from) {

        String name = "movies_" + new Date().getTime();
        String extension = "txt";

        String content = from.stream()
                .map(movie -> movie.getYear() + " " + movie.getTitle() + "\n")
                .reduce("", String::concat);

        return new FileDto.Builder()
                .name(name)
                .content(content)
                .extension(extension)
                .build();
    }
}
