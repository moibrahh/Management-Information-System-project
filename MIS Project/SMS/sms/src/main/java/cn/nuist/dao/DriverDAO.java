package cn.nuist.dao;

import cn.nuist.model.Driver;

import java.util.List;


/**
 * @author Gabriel
 */
public interface DriverDAO {

    Driver findDriver(String driverId) throws Exception;

    boolean addDriver(String driverId, String firstName, String lastName, String nationality, String gender) throws Exception;

    boolean updateDriver(String driverId, String firstName, String lastName, String nationality, String gender) throws Exception;

    List<Driver> findAllDrivers(String driverId, String firstName, String lastName, String nationality, String gender) throws Exception;

    boolean deleteDrivers(List<String> driverIds) throws Exception;

}
