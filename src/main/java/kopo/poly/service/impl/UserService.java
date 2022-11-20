package kopo.poly.service.impl;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.persistance.mapper.IUserMapper;
import kopo.poly.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("UserService")
public class UserService implements IUserService {

    private final IUserMapper userMapper;

    @Autowired
    public UserService(IUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int InsertUserInfo(UserInfoDTO uDTO) throws Exception {
        log.info(this.getClass().getName()+".InsertUserInfo start!");
        int res = userMapper.InsertUserInfo(uDTO);
        log.info(this.getClass().getName()+".InsertUserInfo end!");
        return res;
    }
}
