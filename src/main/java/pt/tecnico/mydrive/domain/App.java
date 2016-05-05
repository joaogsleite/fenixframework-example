package pt.tecnico.mydrive.domain;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import pt.tecnico.mydrive.exception.ImportDocException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class App extends App_Base {
    
    public App(){
        super();
    }

    protected App(Dir parent, User owner, String name) {
        super();
        init(parent, owner, name);
    }
    
    protected Object execute(String[] input){
        String[] str = getContent().split(" ");
        String pkgAndCls = str[0].substring(0, str[0].lastIndexOf(".")-1);
        String mtd = str[0].substring(str[0].lastIndexOf(".")+1,str[0].length()-1);
        String[] args = Arrays.copyOfRange(str, 1, str.length - 1);

        if(input != null) args = input;

        try{
            Class cls = Class.forName(pkgAndCls);
            Object obj = cls.newInstance();
            Method method; 
            if(args.length>0)
                method = cls.getDeclaredMethod(mtd, String[].class);
            else
                method = cls.getDeclaredMethod("main", String[].class);
        
            Object result = method.invoke(obj, args);
            return result;
        }catch(Exception e){
            e.printStackTrace();    
        }
        return null;
    }

    public Element xmlExport(Element xmlmydrive){
        Element app = new Element("app");
        app = super.xmlExportAttributes(app);
        app.addContent(new Element("method").setText(getContent()));
        xmlmydrive.addContent(app);
        return xmlmydrive;
    }

    @Override
    public void xmlImport(Element fileElement) throws ImportDocException, DataConversionException{
        String content = new String(fileElement.getChildText("method"));
        setContent(content);
        super.xmlImport(fileElement);
    }
}
