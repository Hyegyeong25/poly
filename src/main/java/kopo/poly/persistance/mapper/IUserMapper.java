package kopo.poly.persistance.mapper;

import kopo.poly.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMapper {
    int InsertUserInfo(UserInfoDTO uDTO) throws Exception;
}
