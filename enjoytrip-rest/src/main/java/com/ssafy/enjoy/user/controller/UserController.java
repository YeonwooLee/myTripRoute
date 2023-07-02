package com.ssafy.enjoy.user.controller;

import com.ssafy.enjoy.jwt.JwtServiceImpl;
import com.ssafy.enjoy.user.dto.User;
import com.ssafy.enjoy.user.service.UserService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        log.debug("=================LOGIN========================");
        log.debug(user.toString());
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            User loginUser = userService.login(user);
            log.debug("=================LOGIN USER========================");
//            log.debug(loginUser.toString());
            if (loginUser != null) {
                String accessToken = jwtService.createAccessToken("userid", loginUser.getId());// key, data
                String refreshToken = jwtService.createRefreshToken("userid", loginUser.getId());// key, data
                userService.saveRefreshToken(user.getId(), refreshToken);
                log.debug("로그인 accessToken 정보 : {}", accessToken);
                log.debug("로그인 refreshToken 정보 : {}", refreshToken);
                resultMap.put("access-token", accessToken);
                resultMap.put("refresh-token", refreshToken);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } else {
                resultMap.put("message", FAIL);
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            log.error("로그인 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @GetMapping("/info/{userid}")
    public ResponseEntity<Map<String, Object>> getInfo(
            @PathVariable("userid") String userid, HttpServletRequest request
    ) {
//		logger.debug("userid : {} ", userid);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        if (jwtService.checkToken(request.getHeader("access-token"))) {
            log.info("사용 가능한 토큰!!!");
            try {
//				로그인 사용자 정보.
                User user = userService.userInfo(userid);
                log.debug("=================================================");
                log.debug(user.toString());
                log.debug("=================================================");
                resultMap.put("userInfo", user);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                log.error("정보조회 실패 : {}", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            log.error("사용 불가능 토큰!!!");
            resultMap.put("message", FAIL);
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @GetMapping("/logout/{userid}")
    public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            userService.deleRefreshToken(userid);
            resultMap.put("message", SUCCESS);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            log.error("로그아웃 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);

    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody User user, HttpServletRequest request)
            throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        String token = request.getHeader("refresh-token");
        log.debug("token : {}, user : {}", token, user);
        if (jwtService.checkToken(token)) {
            if (token.equals(userService.getRefreshToken(user.getId()))) {
                String accessToken = jwtService.createAccessToken("userid", user.getId());
                log.debug("token : {}", accessToken);
                log.debug("정상적으로 액세스토큰 재발급!!!");
                resultMap.put("access-token", accessToken);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            }
        } else {
            log.debug("리프레쉬토큰도 사용불!!!!!!!");
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @DeleteMapping
    public ResponseEntity<?> withdraw(User user, HttpServletRequest request) {
        log.debug("=========================WITHDRAW");
        log.debug(user.toString());

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        if (jwtService.checkToken(request.getHeader("access-token"))) {
            log.info("사용 가능한 토큰!!!");
            try {
                boolean removable = userService.removeUser(user.getId(), user.getPw());
                if (removable) {
                    log.debug("========================================탈퇴 성공");
                    userService.deleRefreshToken(user.getId());
                    resultMap.put("message", SUCCESS);
                    status = HttpStatus.ACCEPTED;

                } else {
                    resultMap.put("message", FAIL);
                    status = HttpStatus.FORBIDDEN;
                }
            } catch (Exception e) {
                log.error("정보조회 실패 : {}", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            log.error("사용 불가능 토큰!!!");
            resultMap.put("message", FAIL);
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @PostMapping("/regist")
    public ResponseEntity<?> regist(@RequestBody User user, HttpServletRequest request) throws Exception {
        log.debug("=========================REGIST");
        log.debug(user.toString());

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        if (userService.registUser(user) == 1) {
            resultMap.put("message", SUCCESS);
            status = HttpStatus.CREATED;
        } else {
            resultMap.put("message", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(resultMap, status);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> modify(@RequestBody User user, HttpServletRequest request) throws Exception {
        log.debug("=========================UPDATE");
        log.debug(user.toString());

        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        User modifyUser = userService.modifyUser(user);
        if (modifyUser != null) {
//            String accessToken = jwtService.createAccessToken("userid", modifyUser.getId());// key, data
//            String refreshToken = jwtService.createRefreshToken("userid", modifyUser.getId());// key, data
//            userService.saveRefreshToken(user.getId(), refreshToken);
//            log.debug("로그인 accessToken 정보 : {}", accessToken);
//            log.debug("로그인 refreshToken 정보 : {}", refreshToken);
//            resultMap.put("access-token", accessToken);
//            resultMap.put("refresh-token", refreshToken);
            resultMap.put("message", SUCCESS);
            status = HttpStatus.CREATED;
        } else {
            resultMap.put("message", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(resultMap, status);
    }
}
