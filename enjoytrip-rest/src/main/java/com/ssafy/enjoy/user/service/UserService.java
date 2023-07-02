package com.ssafy.enjoy.user.service;

public interface UserService {

    User login(User user) throws Exception;

    User userInfo(String id) throws Exception;

    void saveRefreshToken(String id, String refreshToken) throws Exception;

    Object getRefreshToken(String id) throws Exception;

    void deleRefreshToken(String id) throws Exception;

    boolean removeUser(String id, String pw) throws Exception;

    int registUser(User user) throws Exception;

    User modifyUser(User user) throws Exception;

    String hashPw(String pw);

    boolean checkPw(String id, String pw) throws Exception;
}
