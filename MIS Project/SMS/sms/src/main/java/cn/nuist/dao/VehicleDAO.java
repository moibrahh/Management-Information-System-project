package cn.nuist.dao;

import cn.nuist.model.Vehicle;

import java.util.List;


/**
 * @author Gabriel
 */
public interface VehicleDAO {

    Vehicle findVehicle(String vehicleId) throws Exception;

    boolean addVehicle(String vehicleId, String firstName, String lastName, String nationality, String gender) throws Exception;

    boolean updateVehicle(String vehicleId, String firstName, String lastName, String nationality, String gender) throws Exception;

    List<Vehicle> findAllVehicles(String vehicleId, String firstName, String lastName, String nationality, String gender) throws Exception;

    boolean deleteVehicles(List<String> vehicleIds) throws Exception;

}
