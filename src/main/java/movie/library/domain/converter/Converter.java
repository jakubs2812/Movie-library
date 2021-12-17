package movie.library.domain.converter;

@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}
