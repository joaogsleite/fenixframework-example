package pt.tecnico.mydrive.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.ist.fenixframework.Atomic;
import pt.tecnico.mydrive.domain.*;
import pt.tecnico.mydrive.exception.*;

public class LoginService extends MyDriveService{
    private final String user;
    private final String pass;
    public LoginService(String username, String password){
        user= username;
        pass=password;
    }
    private void dispatch() throws MyDriveException, LoginFailedException{
        Login login = Login.sigIn(user,pass);
        return login.getToken();
    }
}



