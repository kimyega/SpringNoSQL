package kopo.poly.service;

import kopo.poly.dto.MovieDTO;

import java.util.List;

public interface IMovieService {

    List<MovieDTO> getMovieRank() throws Exception;
}
