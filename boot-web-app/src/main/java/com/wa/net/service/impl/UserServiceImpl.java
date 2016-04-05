package com.wa.net.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.wa.net.dao.UserDao;
import com.wa.net.domain.User;
import com.wa.net.service.UserService;
import com.wa.net.utils.EntityUtils;
import com.wa.net.utils.StringUtil;
import com.wa.net.vo.PageResultVo;
import com.wa.net.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public UserVo findUserById(String userId) {
        if (userId == null || userId.trim().equals("")) {
            return null;
        }
        User user=userDao.selectById(Integer.valueOf(userId));
        return EntityUtils.copyProperties(UserVo.class, user);
    }

    @Override
    public int addUser(UserVo vo) {
        User user=EntityUtils.copyProperties(User.class, vo);
        user.setCreateTime(new Date());
        return userDao.insert(user);
    }

    @Override
    public int updateUser(UserVo vo) {
        User user=EntityUtils.copyProperties(User.class, vo);
        return userDao.updateById(user);
    }

    @Override
    public PageResultVo findUserByPage(int rowNum, int currentPage, Map<String, Object> queryMap) {
        PageResultVo pageResultVo = new PageResultVo();
        Page<UserVo> page = new Page<>(currentPage, rowNum);
        User user=new User();
        if(null!=queryMap.get("name") && ""!=queryMap.get("name")){
            user.setName(queryMap.get("name").toString());
        }
        List<User> userList = userDao.selectListForPage(page,user);
        List<UserVo> vos = new ArrayList<>();
        for(User s:userList){
            UserVo vo = new UserVo();
            vo=EntityUtils.copyProperties(vo.getClass(),s);
            vo.setCreateTime(StringUtil.getStringDate(s.getCreateTime()));
            vos.add(vo);
        }
        pageResultVo.setResult(vos);
        pageResultVo.setCurrentPage(currentPage);
        pageResultVo.setRowNum(rowNum);
        pageResultVo.setTotal(page.getTotal());
        return pageResultVo;
    }

    @Override
    public PageResultVo findUserValue(UserVo vo) {
        PageResultVo pageResultVo = new PageResultVo();
        User user=EntityUtils.copyProperties(User.class, vo);
        List<User> list = userDao.selectList(RowBounds.DEFAULT,user);
        List<UserVo> vos = new ArrayList<>();
        for(User a:list){
            UserVo userVo = new UserVo();

            EntityUtils.copyProperties(userVo.getClass(),a);
            vos.add(userVo);
        }
        pageResultVo.setResult(vos);
        pageResultVo.setTotal(vos.size());
        return pageResultVo;
    }

    @Override
    public int deleteUser(Integer id, String status) {
        return userDao.deleteById(id);
    }


}
