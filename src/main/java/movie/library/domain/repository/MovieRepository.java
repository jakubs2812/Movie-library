package movie.library.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import movie.library.domain.entity.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, CrudRepository<Movie, Long> {

    @Modifying
    @Query("DELETE FROM Movie m WHERE m.movieId = :movieId")
    void deleteById(@Param("movieId") Long movieId);

    List<Movie> findByOrderByYearDesc();

    @Modifying
    @Query("DELETE FROM Movie m WHERE m.movieId IN ?1")
    void deleteInBatch(List<Long> movieIds);
}
