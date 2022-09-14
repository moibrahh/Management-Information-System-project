package cn.nuist.util;

/**
 * @author whb
 */
public interface Constant {
    interface State{
        String SUCCESS = " successfully";
        String FAILED = " failed";
        String WRONG = " wrong";
        String NOT_EXIST = " does not exist";
        String DUPLICATED = " is duplicated";
    }

    interface Item{
        String USER = "User ";
        String STUDENT = "Student ";
        String PASSWORD = "Password ";
    }

    interface Operation{
        String LOGIN = "login";
        String REGISTER = "register";
        String QUERY = "query";
        String IS = "is";
        String UPDATE = "update";
        String DELETE = "delete";
        String INSERT = "insert";
    }

}
