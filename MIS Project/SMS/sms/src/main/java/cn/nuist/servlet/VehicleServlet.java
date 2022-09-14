package cn.nuist.servlet;

import cn.nuist.service.VehicleService;
import cn.nuist.service.VehicleServiceImpl;
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

@WebServlet("/vehicle")
public class VehicleServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String vehicleId = request.getParameter("vehicleId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String nationality = request.getParameter("nationality");
        String gender = request.getParameter("gender");
        String action = request.getParameter("action");

        String strIds = request.getParameter("ids");
        List<String> vehicleIds = null;
        if(strIds != null){
            vehicleIds = new ArrayList<>(Arrays.asList(strIds.split(",")));
        }

        PrintWriter pw = response.getWriter();
        Message message = null;
        VehicleService vehicleService = new VehicleServiceImpl();
        if(action.equals(Constant.Operation.INSERT)){
            message = vehicleService.add(vehicleId, firstName, lastName, nationality, gender);
        }
        else if(action.equals(Constant.Operation.UPDATE)){
            message = vehicleService.modify(vehicleId, firstName, lastName, nationality, gender);
        }
        else if(action.equals(Constant.Operation.QUERY)){
            message = vehicleService.query(vehicleId, firstName, lastName, nationality, gender);
        }
        else if(action.equals(Constant.Operation.DELETE)){
            message = vehicleService.deleteAll(vehicleIds);
        }

        String json = message.getJSON();
        pw.write(json);
        pw.flush();
        pw.close();
    }
}
