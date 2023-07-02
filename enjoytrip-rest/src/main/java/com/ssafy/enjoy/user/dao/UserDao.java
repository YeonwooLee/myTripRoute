package com.ssafy.enjoy.user.dao;

import com.ssafy.enjoy.user.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface UserDao {

    User userInfo(String id) throws SQLException;

    void saveRefreshToken(Map<String, String> map) throws SQLException;

    Object getRefreshToken(String id) throws SQLException;

    String getPw(String id) throws SQLException;

    int deleteUser(String id) throws SQLException;

    void deleteRefreshToken(Map<String, String> map) throws SQLException;

    int insertUser(User user) throws SQLException;

    int updateUser(User user) throws SQLException;
}
