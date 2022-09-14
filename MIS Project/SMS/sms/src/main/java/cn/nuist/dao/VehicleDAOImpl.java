package cn.nuist.dao;

import cn.nuist.dbc.DataBaseConnection;
import cn.nuist.exception.SmsException;
import cn.nuist.model.Vehicle;
import cn.nuist.util.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gabriel
 */

public class VehicleDAOImpl implements VehicleDAO {

    private Connection conn;

    public VehicleDAOImpl() {
        this.conn = new DataBaseConnection().createConnection();
    }

    @Override
    public Vehicle findVehicle(String vehicleId) throws Exception {
        Vehicle vehicle = null;
        String sql = "SELECT * FROM vehicle WHERE vehicle_id = ?";

        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, vehicleId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            vehicle = new Vehicle();
            vehicle.setId(rs.getInt("id"));
            vehicle.setVehicleId(rs.getString("vehicle_id"));
            vehicle.setFirstName(rs.getString("first_name"));
            vehicle.setLastName(rs.getString("last_name"));
            vehicle.setNationality(rs.getString("nationality"));
            vehicle.setGender(rs.getString("gender"));
        }
        rs.close();
        ps.close();
        return vehicle;
    }

    @Override
    public boolean addVehicle(String vehicleId, String firstName, String lastName, String nationality, String gender) throws Exception {
        if (this.findVehicle(vehicleId) != null){
            throw new SmsException(Constant.Item.STUDENT + Constant.State.DUPLICATED);
        }
        String sql = "INSERT INTO vehicle(vehicle_id, first_name, last_name, nationality, gender) VALUES(?,?,?,?,?)";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, vehicleId);
        ps.setString(2, firstName);
        ps.setString(3, lastName);
        ps.setString(4, nationality);
        ps.setString(5, gender);

        int lines = ps.executeUpdate();
        ps.close();

        return lines > 0;
    }

    @Override
    public boolean updateVehicle(String vehicleId, String firstName, String lastName, String nationality, String gender) throws Exception {
        String sql = "UPDATE vehicle " +
                "SET first_name = ?, last_name = ?, nationality = ?, gender = ? " +
                "WHERE vehicle_id = ?";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setString(3, nationality);
        ps.setString(4, gender);
        ps.setString(5, vehicleId);

        int lines = ps.executeUpdate();
        ps.close();

        return lines > 0;
    }

    /**
     * Generate the substring such as " AND first_name LIKE '%Tommy%'"
     * @param key
     * @param value
     * @return
     */
    public String generateSearchStr(String key, String value) {
        return !value.equals("") ?
                " AND " + key + " LIKE '%" + value + "%'" : "";
    }

    @Override
    public List<Vehicle> findAllVehicles(String vehicleId, String firstName, String lastName, String nationality, String gender) throws Exception {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicle WHERE 1 = 1";
        sql += generateSearchStr("vehicle_id", vehicleId);
        sql += generateSearchStr("first_name", firstName);
        sql += generateSearchStr("last_name", lastName);
        sql += generateSearchStr("nationality", nationality);
        sql += generateSearchStr("gender", gender);


        PreparedStatement ps = this.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(rs.getInt("id"));
            vehicle.setVehicleId(rs.getString("vehicle_id"));
            vehicle.setFirstName(rs.getString("first_name"));
            vehicle.setLastName(rs.getString("last_name"));
            vehicle.setNationality(rs.getString("nationality"));
            vehicle.setGender(rs.getString("gender"));
            vehicles.add(vehicle);
        }
        rs.close();
        ps.close();

        return vehicles;
    }


    @Override
    public boolean deleteVehicles(List<String> vehicleIds) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM vehicle WHERE ");
        for (String vehicleId : vehicleIds) {
            sql.append(" id = ? or");
        }
        String strSql = sql.toString().substring(0, sql.length() - 2) + ";";
        PreparedStatement ps = this.conn.prepareStatement(strSql);

        for (int i = 0; i < vehicleIds.size(); i++) {
            ps.setInt(1 + i, Integer.parseInt(vehicleIds.get(i)));
        }
        int lines = ps.executeUpdate();
        ps.close();

        return lines > 0;
    }
}
