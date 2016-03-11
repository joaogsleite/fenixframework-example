package pt.tecnico.mydrive.domain;

public class PlainFile extends PlainFile_Base {
    
    public PlainFile(MyDrive mydrive, Dir parent, User owner, String name, String mask) {
       super();
       init(mydrive, parent, owner, name, mask);
    }
  
    protected String read(){
        return getContent();
    }

    protected void write(String content){
        setContent(content);
    }

    protected void execute(){
        String[] lines = getContent().split("\n");
        //Implement Logic
    }

    @Override
    protected boolean isDir(){
        return false;
    }

    @Override
    protected String type(){
        return "PlainFile";
    }
}
