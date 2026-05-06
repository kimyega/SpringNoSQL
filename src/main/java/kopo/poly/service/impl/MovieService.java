package kopo.poly.service.impl;

import kopo.poly.dto.MovieDTO;
import kopo.poly.persistance.redis.IMovieMapper;
import kopo.poly.service.IMovieService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class MovieService implements IMovieService {

    private final IMovieMapper movieMapper;

    private int collectMovie(String redisKey) throws Exception {

        log.info("{}.getMovieList Start!", this.getClass().getName());

        int res = 0;

        String url = "http://www.cgv.co.kr/movies/";

        Document doc;

        doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/120.0.0.0 Safari/537.36")
                .header("Accept-Language", "ko-KR,ko;q=0.9")
                .header("Connection", "keep-alive")
                .header("Accept", "text/html,application/xhtml+xml")
                .timeout(10000)
                .get();

        Elements element = doc.select("div.sect-movie-chart");

        Iterator<Element> movie_rank = element.select("strong.rank").iterator();
        Iterator<Element> movie_name = element.select("strong.title").iterator();
        Iterator<Element> movie_reserve = element.select("strong.percent span").iterator();
        Iterator<Element> score = element.select("strong.percent").iterator();
        Iterator<Element> open_day = element.select("strong.txt-info").iterator();


        while (movie_rank.hasNext()) {
            MovieDTO pDTO = MovieDTO.builder()
                    .collectTime(DateUtil.getDateTime("yyyyMMdd"))
                    .rank(CmmUtil.nvl(movie_rank.next().text().trim()))
                    .name(CmmUtil.nvl(movie_name.next().text().trim()))
                    .reserve(CmmUtil.nvl(movie_reserve.next().text().trim()))
                    .score(CmmUtil.nvl(score.next().text().trim()))
                    .openDay(CmmUtil.nvl(open_day.next().text().trim().substring(0, 10)))
                    .build();

            res += movieMapper.insertMovie(pDTO, redisKey);
        }

        log.info("{}.getMovieList End!", this.getClass().getName());

        return res;
    }

    @Override
    public List<MovieDTO> getMovieRank() throws Exception {

        log.info("{}.getMovieRank Start!", this.getClass().getName());

        String redisKey = "csv_" + DateUtil.getDateTime("yyyyMMdd");

        if (!movieMapper.getExistKey(redisKey)) {
            int res = this.collectMovie(redisKey);

            log.info("수집된 영화 수 : {}", res);
        }

        List<MovieDTO> rList = movieMapper.getMovieList(redisKey);

        log.info("{}.getMovieRank End!", this.getClass().getName());

        return rList;
    }
}
