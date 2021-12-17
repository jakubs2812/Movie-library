package movie.library.domain.files;

import org.springframework.core.io.InputStreamResource;
import movie.library.domain.dto.FileDto;

import java.io.IOException;

public interface FileGenerator {
    InputStreamResource toTxt(FileDto fileDto) throws IOException;
}
