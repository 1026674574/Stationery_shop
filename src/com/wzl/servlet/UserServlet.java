package com.wzl.servlet;

import com.wzl.model.User;
import com.wzl.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet(name = "UserServlet" ,urlPatterns = "/userServlet")
public class UserServlet extends HttpServlet {
    UserService userService = new UserService ();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName = request.getParameter("method");
        try {
            // 利用反射获取方法
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
            // 执行相应的方法
            method.setAccessible(true);
            method.invoke(this, request,response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void login(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user = new User();
        user.setUs_name(name);
        user.setUs_password(password);
        user=userService.login(user);
        if(user == null){
            response.sendRedirect("error-1.jsp");
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        response.sendRedirect("index.jsp");
    }
    protected void registered(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String truename = request.getParameter("truename");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        User user = new User();
        user.setUs_name(name);
        user.setUs_password(password);
        user.setUs_truename(truename);
        user.setUs_address(address);
        user.setUs_phone(phone);
        user.setUs_money(100);
        userService.registered(user);
        response.sendRedirect("success.jsp");
    }
    //跳转页面
    protected void forwardPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        request.getRequestDispatcher("/WEB-INF/pages/" + page + ".jsp").forward(request, response);
    }
}
