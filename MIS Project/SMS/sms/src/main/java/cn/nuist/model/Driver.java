package cn.nuist.model;

import java.io.Serializable;

/**
 * @author Gabriel
 */

public class Driver implements Serializable {
    private int id;
    private String driverId;
    private String firstName;
    private String lastName;
    private String nationality;
    private String gender;
    private int classId;

    public Driver(){ }


    public Driver(int id, String studentId, String firstName, String lastName, String nationality, String gender, int classId) {
        this.id = id;
        this.driverId = driverId;
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

    public String getDriverId() {
        return driverId;
    }

    public void setStudentId(String driverId) {
        this.driverId = driverId;
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


	public void setDriverId(String string) {
		// TODO Auto-generated method stub
		
	}
}

