package com.qrs.auoth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.qrs.annotation.PassToken;
import com.qrs.annotation.UserLoginToken;
import com.qrs.pojo.User;
import com.qrs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zyx 2134208960@qq.com
 * @version 0.1.0
 * @create 2021-03-16 11:21
 * @since 0.1.0
 **/

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        System.out.println(token);
        System.out.println("进入了拦截器");
        System.out.println(request.getRequestURI());
        /*if (request.getRequestURI().contains("end")){
            System.out.println(request.getSession().getAttribute("isError"));
            if ((boolean)request.getSession().getAttribute("isError")==true){
                return false;
            }
            System.out.println(request.getSession().getAttribute("user"));


            if (request.getSession().getAttribute("token")==""){
                request.getRequestDispatcher("/tologin").forward(request,response);
                return false;
            }

            return true;
        }*/
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if(method.isAnnotationPresent(PassToken.class))
        {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if(passToken.required()){
                return true;
            }

        }
        String userId = null;
        if(method.isAnnotationPresent(UserLoginToken.class))
        {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if(userLoginToken.required()){
                if(token == null){
                    throw new RuntimeException("无token，请重新登录");
                }
                //获取token的userid
                try{
                    userId = JWT.decode(token).getAudience().get(0);
                }
                catch (JWTDecodeException e){
                    throw new RuntimeException("401");
                }
                QueryWrapper<User> wrapper = new QueryWrapper();
                wrapper.eq("user_id",userId);
               List<User> list=userService.list(wrapper);
                User user=list.get(0);
                System.out.println(user+"bbbbbb");

                if(user==null){
                    throw new RuntimeException("用户不存在");

                }
                //验证token

                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUser_password())).build();
                try{
                    jwtVerifier.verify(token);
                }catch (JWTVerificationException e){
                    throw new RuntimeException("token认证密码错误或登录已过期，请重新登录");
                }

                return true;
            }
        }
        if (request.getRequestURI().contains("login")){
            return true;
        }
        if (request.getRequestURI().contains("index")){
            return true;
        }



        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
