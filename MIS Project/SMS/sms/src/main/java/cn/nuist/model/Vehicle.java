package cn.nuist.model;

import java.io.Serializable;

/**
 * @author Gabriel
 */

public class Vehicle implements Serializable {
    private int id;
    private String vehicleId;
    private String firstName;
    private String lastName;
    private String nationality;
    private String gender;
    private int classId;

    public Vehicle(){ }


    public Vehicle(int id, String studentId, String firstName, String lastName, String nationality, String gender, int classId) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.gender = gender;
        this.classId = classId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setStudentId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }


	public void setVehicleId(String string) {
		// TODO Auto-generated method stub
		
	}
}

