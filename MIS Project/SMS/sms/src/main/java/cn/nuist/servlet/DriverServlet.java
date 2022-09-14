package cn.nuist.servlet;

import cn.nuist.service.DriverService;
import cn.nuist.service.DriverServiceImpl;
import cn.nuist.util.Constant;
import cn.nuist.util.Message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Gabriel
 */

@WebServlet("/driver")
public class DriverServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String driverId = request.getParameter("driverId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String nationality = request.getParameter("nationality");
        String gender = request.getParameter("gender");
        String action = request.getParameter("action");

        String strIds = request.getParameter("ids");
        List<String> driverIds = null;
        if(strIds != null){
            driverIds = new ArrayList<>(Arrays.asList(strIds.split(",")));
        }

        PrintWriter pw = response.getWriter();
        Message message = null;
        DriverService driverService = new DriverServiceImpl();
        if(action.equals(Constant.Operation.INSERT)){
            message = driverService.add(driverId, firstName, lastName, nationality, gender);
        }
        else if(action.equals(Constant.Operation.UPDATE)){
            message = driverService.modify(driverId, firstName, lastName, nationality, gender);
        }
        else if(action.equals(Constant.Operation.QUERY)){
            message = driverService.query(driverId, firstName, lastName, nationality, gender);
        }
        else if(action.equals(Constant.Operation.DELETE)){
            message = driverService.deleteAll(driverIds);
        }

        String json = message.getJSON();
        pw.write(json);
        pw.flush();
        pw.close();
    }
}