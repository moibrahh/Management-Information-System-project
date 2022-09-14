package cn.nuist.service;

import cn.nuist.util.Message;

import java.util.List;

/**
 * @author Gabriel
 */

public interface VehicleService {
    Message add(String studentId, String firstName, String lastName, String nationality, String gender);

    Message modify(String studentId, String firstName, String lastName, String nationality, String gender);

    Message query(String studentId, String firstName, String lastName, String nationality, String gender);

    Message deleteAll(List<String> studentIds);
}
