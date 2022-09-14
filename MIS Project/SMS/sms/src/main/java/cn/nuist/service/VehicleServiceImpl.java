package cn.nuist.service;

import cn.nuist.dao.VehicleDAO;
import cn.nuist.dao.VehicleDAOImpl;
import cn.nuist.exception.SmsException;
import cn.nuist.model.Vehicle;
import cn.nuist.util.Constant;
import cn.nuist.util.Message;

import java.util.List;
import static cn.nuist.util.Constant.State.*;

/**
 * @author Gabriel
 */
public class VehicleServiceImpl implements VehicleService{

    @Override
    public Message add(String vehicleId, String firstName, String lastName, String nationality, String gender) {
        String detail = "";
        String state = "";
        VehicleDAO dao = new VehicleDAOImpl();
        boolean isSuccess = false;
        try {
            isSuccess = dao.addVehicle(vehicleId, firstName, lastName, nationality, gender);
            state = isSuccess ? SUCCESS : FAILED;
            detail = Constant.Operation.INSERT + state;
        } catch (SmsException smse) {
            state = FAILED;
            detail = smse.getMessage();
        }catch (Exception e) {
            e.printStackTrace();
        }
        Message message = new Message();
        message.setState(state);
        message.setDetail(detail);

        return message;
    }

    @Override
    public Message modify(String vehicleId, String firstName, String lastName, String nationality, String gender) {
        String detail;
        String state;
        boolean isSuccess = false;
        VehicleDAO dao = new VehicleDAOImpl();
        try{
            isSuccess = dao.updateVehicle(vehicleId,firstName, lastName, nationality, gender);
        } catch (Exception exception){
            exception.printStackTrace();
        }

        state = isSuccess ? SUCCESS : FAILED;
        detail = Constant.Operation.UPDATE + state;
        Message message = new Message();
        message.setState(state);
        message.setDetail(detail);
        return message;
    }

    @Override
    public Message query(String vehicleId, String firstName, String lastName, String nationality, String gender) {
        String result;
        String state;
        VehicleDAO dao = new VehicleDAOImpl();
        List<Vehicle> students = null;
        try{
            students = dao.findAllVehicles(vehicleId, firstName, lastName,nationality, gender);
        } catch(Exception exception){
            exception.printStackTrace();
        }
        state = SUCCESS;
        Message message = new Message();
        message.setState(state);
        message.setDetail("");
        message.setData(students);

        return message;
    }

    @Override
    public Message deleteAll(List<String> vehicleIds) {
        String detail;
        String state;
        boolean isSuccess = false;
        VehicleDAO dao = new VehicleDAOImpl();
        try{
            isSuccess = dao.deleteVehicles(vehicleIds);
        } catch(Exception exception){
            exception.printStackTrace();
        }
        state = isSuccess ? SUCCESS : FAILED;
        detail = Constant.Operation.UPDATE + state;
        Message message = new Message();
        message.setState(state);
        message.setDetail(detail);

        return message;
    }
}
