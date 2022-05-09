package tech.krauwarrior.silverhelper.data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


// DAL层逻辑
public class DataAccessLayer {
    private static HashMap<String, String> DataBase = new HashMap<String, String>();

    public boolean add(String key, String value){
        DataBase.put(key, value);
        return true;
    }

    public boolean del(){
        return true;
    }

    public boolean update(){
        return true;
    }

    public boolean find(){
        return true;
    }
}
