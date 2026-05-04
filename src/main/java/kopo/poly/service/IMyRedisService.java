package kopo.poly.service;

import kopo.poly.dto.RedisDTO;

import java.util.List;

public interface IMyRedisService {

    RedisDTO saveString(RedisDTO pDTO) throws Exception;

    RedisDTO saveStringJSON(RedisDTO pDTO) throws Exception;

    List<String> saveList(List<RedisDTO> pList) throws Exception;

    RedisDTO saveHash(RedisDTO pDTO) throws Exception;
}
