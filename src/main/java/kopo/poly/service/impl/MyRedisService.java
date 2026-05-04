package kopo.poly.service.impl;

import kopo.poly.dto.RedisDTO;
import kopo.poly.persistance.redis.IMyRedisMapper;
import kopo.poly.service.IMyRedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class MyRedisService implements IMyRedisService {

    private final IMyRedisMapper myRedisMapper;

    @Override
    public RedisDTO saveString(RedisDTO pDTO) throws Exception {

        log.info("{}.saveString Start!", this.getClass().getName());

        String redisKey = "myRedis_String";

        RedisDTO rDTO;

        int res = myRedisMapper.saveString(redisKey, pDTO);

        if (res == 1) {
            rDTO = myRedisMapper.getString(redisKey);
        } else {
            log.info("Redis 저장 실패!!");
            throw new Exception("Redis 저장 실패!");
        }

        log.info("{}.saveString End!", this.getClass().getName());

        return rDTO;
    }

    @Override
    public RedisDTO saveStringJSON(RedisDTO pDTO) throws Exception {

        log.info("{}.saveStringJSON Start!", this.getClass().getName());

        String redisKey = "myRedis_StringJSON";

        RedisDTO rDTO;

        int res = myRedisMapper.saveStringJSON(redisKey, pDTO);

        if (res == 1) {
            rDTO = myRedisMapper.getStringJSON(redisKey);
        } else {
            log.info("Redis 저장 실패!");
            throw new Exception("Redis 저장 실패!!");
        }

        log.info("{}.saveStringJSON End!", this.getClass().getName());

        return rDTO;
    }

    @Override
    public List<String> saveList(List<RedisDTO> pList) throws Exception {

        log.info("{}.saveList Start!", this.getClass().getName());

        String redisKey = "myRedis_List";

        List<String> rList;

        int res = myRedisMapper.saveList(redisKey, pList);

        if (res == 1) {
            rList = myRedisMapper.getList(redisKey);
        } else {
            log.info("Redis 저장 실패!");
            throw new Exception("Redis 저장 실패!!");
        }

        log.info("{}.saveList End!", this.getClass().getName());

        return rList;
    }

    @Override
    public RedisDTO saveHash(RedisDTO pDTO) throws Exception {

        log.info("{}.saveHash Start!", this.getClass().getName());

        String redisKey = "myRedis_Hash";

        RedisDTO rDTO;

        int res = myRedisMapper.saveHash(redisKey, pDTO);

        if (res == 1) {
            rDTO = myRedisMapper.getHash(redisKey);
        } else {
            log.info("Redis 저장 실패!!");
            throw new Exception("Redis 저장 실패!!");
        }

        log.info("{}.saveHash End!", this.getClass().getName());

        return rDTO;
    }

    @Override
    public Set<RedisDTO> saveSetJSON(List<RedisDTO> pList) throws Exception {

        log.info("{}.saveSetJSON Start!", this.getClass().getName());

        String redisKey = "myRedis_Set_JSON";

        Set<RedisDTO> rSet;

        int res = myRedisMapper.saveSetJSON(redisKey, pList);

        if (res == 1) {
            rSet = myRedisMapper.getSetJSON(redisKey);
        } else {
            log.info("Redis 저장 실패!!");
            throw new Exception("Redis 저장 실패!!");
        }

        log.info("{}.saveSetJSON End!", this.getClass().getName());

        return rSet;
    }

    @Override
    public Set<RedisDTO> saveZSetJSON(List<RedisDTO> pList) throws Exception {

        log.info("{}.saveZSetJSON Start!", this.getClass().getName());

        String redisKey =  "myRedis_ZSet_JSON";

        Set<RedisDTO> rSet;

        int res = myRedisMapper.saveZSetJSON(redisKey, pList);

        if (res == 1) {
            rSet = myRedisMapper.getZSetJSON(redisKey);
        } else {
            log.info("Redis 저장 실패!!");
            throw new Exception("Redis 저장 실패!!");
        }

        log.info("{}.saveZSetJSON End!", this.getClass().getName());

        return rSet;
    }
}
