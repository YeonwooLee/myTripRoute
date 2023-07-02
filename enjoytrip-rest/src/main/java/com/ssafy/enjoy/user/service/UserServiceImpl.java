package com.ssafy.enjoy.user.service;

import com.ssafy.enjoy.user.dao.UserDao;
import com.ssafy.enjoy.user.dto.User;
import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public User login(User user) throws Exception {
        if (user.getId() == null || user.getPw() == null) {
            return null;
        }

        if (!checkPw(user.getId(), user.getPw())) {
            return null;
        }

        return this.userInfo(user.getId());
    }

    @Override
    public User userInfo(String userid) throws Exception {
        return sqlSession.getMapper(UserDao.class).userInfo(userid);
    }

    @Override
    public boolean removeUser(String id, String pw) throws Exception {
        if (checkPw(id, pw)) {
            sqlSession.getMapper(UserDao.class).deleteUser(id);
            return true;
        }
        return false;
    }

    @Override
    public void saveRefreshToken(String userid, String refreshToken) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userid", userid);
        map.put("token", refreshToken);
        sqlSession.getMapper(UserDao.class).saveRefreshToken(map);
    }

    @Override
    public Object getRefreshToken(String userid) throws Exception {
        return sqlSession.getMapper(UserDao.class).getRefreshToken(userid);
    }

    @Override
    public void deleRefreshToken(String userid) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userid", userid);
        map.put("token", null);
        sqlSession.getMapper(UserDao.class).deleteRefreshToken(map);
    }

    @Override
    public int registUser(User user) throws Exception {
        user.setPw(hashPw(user.getPw()));
        return sqlSession.getMapper(UserDao.class).insertUser(user);
    }

    @Override
    public User modifyUser(User user) throws Exception {
        user.setPw(hashPw(user.getPw()));
        if (sqlSession.getMapper(UserDao.class).updateUser(user) == 1) {
            return this.userInfo(user.getId());
        }
        return null;
    }

    @Override
    public String hashPw(String pw) {
        return BCrypt.hashpw(pw, BCrypt.gensalt(10));
    }

    @Override
    public boolean checkPw(String id, String pw) throws Exception {
        String correct = sqlSession.getMapper(UserDao.class).getPw(id);
        return correct != null && BCrypt.checkpw(pw, correct);
    }
}
