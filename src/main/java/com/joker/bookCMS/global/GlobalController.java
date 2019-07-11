package com.joker.bookCMS.global;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "GlobalController", urlPatterns = {"*.do"})
public class GlobalController extends GenericServlet {
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        /**
         * .do
         * /login.do                DefaultController       login
         * /Cake/detail.do          CakeController          detail
         * /admin/Cake/add.do       CakeController          add
         */
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //  获得路径
        String path = request.getServletPath();
        if (path.indexOf("admin") != -1) {
            path = path.substring(7);
        } else {
            path = path.substring(1);
        }

        //  获得类名和方法名
        int index = path.indexOf("/");
        String className = null;
        String methodName = null;
        if (index != -1) {
            className = "com.joker.bookCMS.controller." + path.substring(0, index) + "Controller";
            methodName = path.substring(index + 1, path.indexOf(".do"));
        } else {
            className = "com.joker.bookCMS.controller.DefaultController";
            methodName = path.substring(0, path.indexOf(".do"));
        }

        //  通过反射执行该方法
        try {
            Class cla = Class.forName(className);
            Object obj = cla.newInstance();
            Method method = cla.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(obj, request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
