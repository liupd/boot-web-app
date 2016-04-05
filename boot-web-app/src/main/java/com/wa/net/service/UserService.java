package com.wa.net.service;


import com.wa.net.vo.PageResultVo;
import com.wa.net.vo.UserVo;

import java.util.Map;

public interface UserService {

    public UserVo findUserById(String userId);

    public int addUser(UserVo vo);

    public int updateUser(UserVo vo);

    public PageResultVo findUserByPage(int rowNum, int currentPage, Map<String, Object> queryMap);

    public PageResultVo findUserValue(UserVo vo);

    int deleteUser(Integer id,String status);

}