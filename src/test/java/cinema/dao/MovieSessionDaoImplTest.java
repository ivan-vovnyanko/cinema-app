package cinema.dao;

import cinema.dao.impl.CinemaHallDaoImpl;
import cinema.dao.impl.MovieDaoImpl;
import cinema.dao.impl.MovieSessionDaoImpl;
import cinema.model.CinemaHall;
import cinema.model.Movie;
import cinema.model.MovieSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieSessionDaoImplTest extends AbstractTest {
    private static final LocalDateTime DATE_TIME =
            LocalDateTime.of(2030, 1, 1, 12, 0);
    private static final LocalDate DATE =
            LocalDate.of(2030, 1, 1);
    private MovieSessionDao movieSessionDao;
    private MovieDao movieDao;
    private CinemaHallDao cinemaHallDao;
    private Movie movie;
    private CinemaHall cinemaHall;

    @BeforeEach
    void setUp() {
        movieSessionDao = new MovieSessionDaoImpl(getSessionFactory());
        movieDao = new MovieDaoImpl(getSessionFactory());
        cinemaHallDao = new CinemaHallDaoImpl(getSessionFactory());
        movie = new Movie();
        movie.setTitle("Demo Title");
        movie.setDescription("Demo Description");
        movieDao.add(movie);
        cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("Demo Description");
        cinemaHallDao.add(cinemaHall);
    }

    @Test
    void addSession_Ok() {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setShowTime(DATE_TIME);
        Assertions.assertEquals(movieSession, movieSessionDao.add(movieSession));
    }

    @Override
    protected Class<?>[] entities() {
        return new Class[] {MovieSession.class, Movie.class, CinemaHall.class};
    }
}