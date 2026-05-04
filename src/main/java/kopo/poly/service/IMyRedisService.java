package kopo.poly.service;

import kopo.poly.dto.RedisDTO;

import java.util.List;
import java.util.Set;

public interface IMyRedisService {

    RedisDTO saveString(RedisDTO pDTO) throws Exception;

    RedisDTO saveStringJSON(RedisDTO pDTO) throws Exception;

    List<String> saveList(List<RedisDTO> pList) throws Exception;

    RedisDTO saveHash(RedisDTO pDTO) throws Exception;

    Set<RedisDTO> saveSetJSON(List<RedisDTO> pList) throws Exception;

    Set<RedisDTO> saveZSetJSON(List<RedisDTO> pList) throws Exception;
}
