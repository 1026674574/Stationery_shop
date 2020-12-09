package com.wzl.servlet;
import com.wzl.dao.TypeDao;
import com.wzl.dao.impl.TypeDaoIml;
import com.wzl.model.Shop;
import com.wzl.model.ShoppingCart;
import com.wzl.model.Type;
import com.wzl.model.User;
import com.wzl.service.ShopService;
import com.wzl.web.EStoreWebUtils;
import com.wzl.web.Page;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ShopServlet" ,urlPatterns = "/shopServlet")
public class ShopServlet extends HttpServlet {
    ShopService shopService = new ShopService();
    TypeDaoIml typeDaoIml = new TypeDao();
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
    protected void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        int id = -1;
        boolean flag = false;
        try {
            id = Integer.parseInt(idStr);
        } catch (Exception ignored) {}

        ShoppingCart sc = EStoreWebUtils.getShoppingCart(request);
        shopService.removeItemFromShoppingCart(sc, id);

        if(sc.isEmpty()){
            request.getRequestDispatcher("/WEB-INF/pages/emptycart.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);

    }
    protected void updateItemQuantity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //4. 在 updateItemQuantity 方法中, 获取 quanity, id, 再获取购物车对象, 调用 service 的方法做修改
        String idStr = request.getParameter("id");
        String quantityStr = request.getParameter("quantity");

        ShoppingCart sc = EStoreWebUtils.getShoppingCart(request);

        int id = -1;
        int quantity = -1;

        try {
            id = Integer.parseInt(idStr);
            quantity = Integer.parseInt(quantityStr);
        } catch (Exception ignored) {}

        if(id > 0 && quantity > 0)
            shopService.updateItemQuantity(sc, id, quantity);

        //5. 传回 JSON 数据: computerNumber:xx, totalMoney
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("shopNumber", sc.getComputerNumber());
        result.put("totalMoney", sc.getTotalMoney());

        Gson gson = new Gson();
        String jsonStr = gson.toJson(result);
        response.setContentType("text/javascript");
        response.getWriter().print(jsonStr);
    }
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ShoppingCart sCart = EStoreWebUtils.getShoppingCart(request);
        shopService.clearShoppingCart(sCart);
        request.getRequestDispatcher("/WEB-INF/pages/emptycart.jsp").forward(request, response);
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
        String typestr = request.getParameter("type");
        String text="";
        if (request.getParameter("text" )!= null)
        {
           text= new String(request.getParameter("text").getBytes("ISO-8859-1"),"UTF-8");
        }

        System.out.println("text:"+text);
        int pageNo = 1;
        try {
            pageNo = Integer.parseInt(pageNoStr);
        } catch (NumberFormatException ignored) {}

        int type = 0;
        try {
            type= Integer.parseInt(typestr);
        } catch (NumberFormatException ignored) {}
        Page<Shop> page = shopService.getPage(pageNo,text,type);
        ArrayList<Type> types = typeDaoIml.getTypes();
        request.setAttribute("shoppage",page);
        request.setAttribute("text",text);
        request.setAttribute("types",types);
        request.setAttribute("type",type);
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
            getPage(request, response);
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

    //结账功能
    protected void cash(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        User user = getUser(request, response);
        shopService.cash(EStoreWebUtils.getShoppingCart(request),user.getUs_truename());
        response.sendRedirect(request.getContextPath() + "/success.jsp");



    }
    public User getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = EStoreWebUtils.getUser(request);
        if (user==null)
        {
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request,response);
            return null;
        }
        return user;

    }


}
