package pt.tecnico.mydrive.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.ist.fenixframework.Atomic;
import pt.tecnico.mydrive.domain.*;
import pt.tecnico.mydrive.exception.*;

public class ChangeDirectoryService extends MyDriveService{
	MyDrive md = MyDrive.getInstance();

    private Dir _workingDir;
    private Login _login;
    private String _path;


    public ChangeDirectoryService(token, path){
    	super();

    	_login = Login.getLoginByToken(token);
    	_token = token;
    	_path = path;

    }

    private File dispatch() throws FileIsAPlainFileException, FileDoesNotExistException, InsufficientPermissionsException{
        String[] _path = path.split("/");

        Dir actual;
        int i;

        if(path.charAt(0) == '/'){

            actual = md.getRootDir();
            i = 1;
        }
        else{
            actual = _login.getWorkingDir();
            i = 0;
        }

        for(; i<_path.length-1; i++){
            actual = actual.getDir(_path[i]);
        }

        if(actual.exists(_path[i++])){
        	if (actual.getDir(_path[i]).isDir){
        		_workingDir = actual.getDir(_path[i]);

                if(!(_workingDir.getOwner().equals(login.getUser()) && _workingDir.getUmask().charAt(0) == 'r')
                && !(!_workingDir.getOwner().equals(login.getUser()) && _workingDir.getUmask().charAt(4) == 'r')
                && !(login.getUser().getUsername().equals("root")))
                throw new InsufficientPermissionsException();

        		_login.setWorkingDir(_workingDir);
        		return _workingDir.getPath();
        	}
        	else
            	throw new FileIsAPlainFileException(_path[i]);
        }

        throw new FileDoesNotExistException(_path[i]);
    }
}
