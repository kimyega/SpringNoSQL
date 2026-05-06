package kopo.poly.controller;

import jakarta.validation.Valid;
import kopo.poly.controller.response.CommonResponse;
import kopo.poly.dto.MovieDTO;
import kopo.poly.service.IMovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/movie/v1")
@RestController
public class MovieController {

    private final IMovieService movieService;

    @PostMapping(value = "/speechcommand")
    public ResponseEntity<?> getMovie(@Valid @RequestBody MovieDTO pDTO, BindingResult bindingResult) throws Exception {

        log.info("{}.getMovie({})", this.getClass().getName(), pDTO);

        if (bindingResult.hasErrors()) {
            return CommonResponse.getErrors(bindingResult);
        }

        log.info("Received speech command: {}", pDTO.speechCommand());

        List<String> movieKeywords = List.of("영화", "영하", "연하", "연화");

        boolean containsKeyword = movieKeywords.stream()
                .anyMatch(keyword -> pDTO.speechCommand().contains(keyword));

        List<MovieDTO> rList = containsKeyword
                ? Optional.ofNullable(movieService.getMovieRank()).orElse(List.of())
                : List.of();

        log.info("{}.get Movie End!",  this.getClass().getName());

        return ResponseEntity.ok(CommonResponse.of(HttpStatus.OK, "SUCCESS", rList));
    }

}
