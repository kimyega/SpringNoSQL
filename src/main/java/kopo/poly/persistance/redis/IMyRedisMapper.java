package kopo.poly.persistance.redis;

import kopo.poly.dto.RedisDTO;

import java.util.List;
import java.util.Set;

public interface IMyRedisMapper {

    int saveString(String redisKey, RedisDTO pDTO) throws Exception;

    RedisDTO getString(String redisKey) throws Exception;

    int saveStringJSON(String redisKey, RedisDTO pDTO) throws Exception;

    RedisDTO getStringJSON(String redisKey) throws Exception;

    int saveList(String redisKey, List<RedisDTO> pList) throws Exception;

    List<String> getList(String redisKey) throws Exception;

    int saveHash(String redisKey, RedisDTO pDTO) throws Exception;

    RedisDTO getHash(String redisKey) throws Exception;

    int saveSetJSON(String redisKey, List<RedisDTO> pList) throws Exception;

    Set<RedisDTO> getSetJSON(String redisKey) throws Exception;

    int saveZSetJSON(String redisKey, List<RedisDTO> pList) throws Exception;

    Set<RedisDTO> getZSetJSON(String redisKey) throws Exception;
}
