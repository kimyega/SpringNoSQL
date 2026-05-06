package kopo.poly.persistance.redis.impl;

import kopo.poly.dto.MovieDTO;
import kopo.poly.persistance.redis.IMovieMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Component
public class MovieMapper implements IMovieMapper {

    private final RedisTemplate<String, Object> redisDB;


    private void setTimeOutHour(String redisKey) throws Exception {
        log.info("{}.setTimeOutHour Start!", this.getClass().getName());
        redisDB.expire(redisKey, 1, TimeUnit.HOURS);
    }

    @Override
    public int insertMovie(MovieDTO pDTO, String redisKey) throws Exception {
        log.info("{}.insertMovie Start!", this.getClass().getName());

        int res;

        redisDB.setKeySerializer(new StringRedisSerializer());

        redisDB.setValueSerializer(new JacksonJsonRedisSerializer<>(MovieDTO.class));

        redisDB.opsForList().rightPush(redisKey, pDTO);

        this.setTimeOutHour(redisKey);

        res = 1;

        log.info("{}.insertMovie End!", this.getClass().getName());

        return res;
    }

    @Override
    public boolean getExistKey(String redisKey) throws Exception {
        log.info("{}.getExistKey Start!", this.getClass().getName());
        return Optional.ofNullable(redisDB.hasKey(redisKey)).orElseThrow(Exception::new);
    }

    @Override
    public List<MovieDTO> getMovieList(String redisKey) throws Exception {
        log.info("{}.getMovieList Start!", this.getClass().getName());

        List<MovieDTO> rList = null;

        redisDB.setKeySerializer(new  StringRedisSerializer());

        redisDB.setValueSerializer(new  JacksonJsonRedisSerializer<>(MovieDTO.class));

        if (Optional.ofNullable(redisDB.hasKey(redisKey)).orElseThrow(Exception::new)) {
            rList = (List) redisDB.opsForList().range(redisKey, 0, -1);

            this.setTimeOutHour(redisKey);
        }

        log.info("{}.getMovieList End!", this.getClass().getName());

        return rList;
    }
}
