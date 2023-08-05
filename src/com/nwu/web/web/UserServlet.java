package com.nwu.web.web;

import com.google.gson.Gson;
import com.nwu.web.pojo.User;
import com.nwu.web.service.UserService;
import com.nwu.web.service.impl.UserServiceImpl;
import com.nwu.web.utils.WebUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        User login = userService.login(new User(null, user.getUsername(), user.getPassword(), null));

        if(login == null) {
            req.setAttribute("msg", "用户名或密码错误！");
            req.setAttribute("username", user.getUsername());
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", login);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String code = req.getParameter("code");
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.removeAttribute(KAPTCHA_SESSION_KEY);
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());


        if(token != null && token.equalsIgnoreCase(code)) {
            if(userService.existsUsername(user.getUsername())) {
                req.setAttribute("msg", "用户名不可用！！！");
                req.setAttribute("username", user.getUsername());
                req.setAttribute("email", user.getEmail());
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                userService.registUser(new User(null, user.getUsername(), user.getPassword(), user.getEmail()));
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }

        } else {
            req.setAttribute("msg", "验证码错误！！！");
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String username = req.getParameter("username");
        boolean existsUsername = userService.existsUsername(username);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existsUsername);
        Gson gson =  new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
}
