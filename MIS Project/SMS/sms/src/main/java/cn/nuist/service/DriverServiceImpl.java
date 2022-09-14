package cn.nuist.service;

import cn.nuist.dao.DriverDAO;
import cn.nuist.dao.DriverDAOImpl;
import cn.nuist.exception.SmsException;
import cn.nuist.model.Driver;
import cn.nuist.util.Constant;
import cn.nuist.util.Message;

import java.util.List;
import static cn.nuist.util.Constant.State.*;

/**
 * @author Gabriel
 */
public class DriverServiceImpl implements DriverService{

    @Override
    public Message add(String driverId, String firstName, String lastName, String nationality, String gender) {
        String detail = "";
        String state = "";
        DriverDAO dao = new DriverDAOImpl();
        boolean isSuccess = false;
        try {
            isSuccess = dao.addDriver(driverId, firstName, lastName, nationality, gender);
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
    public Message modify(String driverId, String firstName, String lastName, String nationality, String gender) {
        String detail;
        String state;
        boolean isSuccess = false;
        DriverDAO dao = new DriverDAOImpl();
        try{
            isSuccess = dao.updateDriver(driverId,firstName, lastName, nationality, gender);
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
    public Message query(String driverId, String firstName, String lastName, String nationality, String gender) {
        String result;
        String state;
        DriverDAO dao = new DriverDAOImpl();
        List<Driver> students = null;
        try{
            students = dao.findAllDrivers(driverId, firstName, lastName,nationality, gender);
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
    public Message deleteAll(List<String> driverIds) {
        String detail;
        String state;
        boolean isSuccess = false;
        DriverDAO dao = new DriverDAOImpl();
        try{
            isSuccess = dao.deleteDrivers(driverIds);
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
