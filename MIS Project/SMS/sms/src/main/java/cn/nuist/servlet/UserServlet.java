package cn.nuist.servlet;

import java.io.*;
import java.util.*;

import cn.nuist.exception.SmsException;
import cn.nuist.model.User;
import cn.nuist.service.UserService;
import cn.nuist.service.UserServiceImpl;
import cn.nuist.util.Constant;
import cn.nuist.util.Message;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");//returns the value given in the request as a string
        String password = request.getParameter("password");
        String action = request.getParameter("action");

        String strIds = request.getParameter("ids");
        List<String> ids = null;
        if (strIds != null){
            ids = new ArrayList<>(Arrays.asList(
                    strIds.split(",")));
        }
        Message message = null;
        try {
            PrintWriter writer = response.getWriter();// this method is used to send the character data in response

            UserService userService = new UserServiceImpl();
            if (action.equals(Constant.Operation.LOGIN)) {
                message = userService.login(username, password);
            } else if (action.equals(Constant.Operation.REGISTER) || action.equals(Constant.Operation.INSERT)) {
                message = userService.register(username, password);
            } else if (action.equals(Constant.Operation.UPDATE)) {
                message = userService.modify(username, password);
            } else if (action.equals(Constant.Operation.QUERY)) {
                message = userService.query(username, password);
            } else if (action.equals(Constant.Operation.DELETE)) {
                message = userService.deleteAll(ids);
            }
            String json = message.getJSON();
            writer.write(json);
            writer.flush();
            writer.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

}
