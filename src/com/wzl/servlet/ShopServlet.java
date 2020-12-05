package com.wzl.servlet;

import com.wzl.model.Shop;
import com.wzl.model.ShoppingCart;
import com.wzl.service.ShopService;
import com.wzl.web.EStoreWebUtils;
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


    //跳转页面
    protected void forwardPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        request.getRequestDispatcher("/WEB-INF/pages/" + page + ".jsp").forward(request, response);
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

    //添加到购物车
    protected void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取商品的 id
        String idStr = request.getParameter("id");
        int id = -1;
        boolean flag = false;

        try {
            id = Integer.parseInt(idStr);
        } catch (Exception ignored) {}

        if(id > 0){
            //2. 获取购物车对象
            ShoppingCart sc = EStoreWebUtils.getShoppingCart(request);

            //3. 调用 ComputerService 的 addToCart() 方法把商品放到购物车中
            flag = shopService.addToCart(id, sc);
        }

        if(flag){
            //4. 直接调用 getComputers() 方法.
            getShop(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/error-1.jsp");
    }
    //添加到购物车（商品详情页添加）
    protected void addToCartInside(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取商品的 id
        String idStr = request.getParameter("id");
        int id = -1;
        boolean flag = false;

        try {
            id = Integer.parseInt(idStr);
        } catch (Exception ignored) {}

        if(id > 0){
            //2. 获取购物车对象
            ShoppingCart sc = EStoreWebUtils.getShoppingCart(request);

            //3. 调用 ComputerService 的 addToCart() 方法把商品放到购物车中
            flag = shopService.addToCart(id, sc);
        }

        if(flag){
            //4. 直接调用 getComputer() 方法.
            getShop(request, response);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/error-1.jsp");
    }

    protected void cash(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


        shopService.cash(EStoreWebUtils.getShoppingCart(request),"王鑫");

    }
}
