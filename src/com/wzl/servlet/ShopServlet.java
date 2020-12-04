package com.wzl.servlet;

import com.wzl.model.Shop;
import com.wzl.service.ShopService;
import com.wzl.web.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
@WebServlet(name = "ShopServlet" ,urlPatterns = "/shopServlet")
public class ShopServlet extends HttpServlet {
    ShopService shopService = new ShopService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName = request.getParameter("method");
        try {
            // 利用反射获取方法
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
            // 执行相应的方法
            method.setAccessible(true);
            method.invoke(this,request,response);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }

    protected void getPage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String pageNoStr = request.getParameter("pageNo");
        int pageNo = 1;
        try {
            pageNo = Integer.parseInt(pageNoStr);
        } catch (NumberFormatException ignored) {}

        Page<Shop> page = shopService.getPage(pageNo);
        request.setAttribute("shoppage",page);
        request.getRequestDispatcher("/WEB-INF/pages/shops.jsp").forward(request,response);
    }

    //获得商品详情
    protected void getShop(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int id = -1;
        Shop shop = null;

        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException ignored) {}
        if (id > 0) {
            shop = shopService.getShop(id);
        }

        if (shop == null) {
            response.sendRedirect(request.getContextPath()+"/error-1.jsp");
            return;
        }
        request.setAttribute("shop", shop);
        request.getRequestDispatcher("/WEB-INF/pages/particulars.jsp").forward(request, response);
    }
}
