package com.wa.net.dao;


import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wa.net.domain.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface UserDao extends AutoMapper<User> {

    public List<User> selectListForPage(RowBounds pagination,User user);

    public List<User> selectListRow(RowBounds pagination,String name);

}
