package pt.tecnico.mydrive.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.ist.fenixframework.Atomic;
import pt.tecnico.mydrive.domain.*;
import pt.tecnico.mydrive.exception.*;

public class ExportMyDriveService extends MyDriveService{
    private Document doc;
    
    public ExportMyDriveService(){}

    @Override
    protected void dispatch() throws ExportDocumentException{
	    doc = getPhoneBook().xmlExport();
    }

    public final Document result() {
        return doc;
    }
}