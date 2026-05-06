package kopo.poly.persistance.redis;

import kopo.poly.dto.MovieDTO;

import java.util.List;

public interface IMovieMapper {

    int insertMovie(MovieDTO pDTO, String redisKey) throws Exception;

    boolean getExistKey(String redisKey) throws Exception;

    List<MovieDTO> getMovieList(String redisKey) throws Exception;
}
