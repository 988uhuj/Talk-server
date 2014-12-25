package me.risky.talk.common.util;

import java.util.Map;

/**
 * Created by chenupt@gmail.com on 14-2-24.
 * Description : TODO
 */
public class MapHelper {
    public static String s(Map map,String key){
        Object obj = map.get(key);
        if(obj==null) return null;
        if(obj instanceof String){
            if(((String) obj).trim().equals("")) return null;
            return (String)obj;
        }

        return null;
    }
    public static Long l(Map map,String key){
        Object obj = map.get(key);
        if(obj==null) return null;
        if(obj instanceof Long){
            return (Long)obj;
        }
        if(obj instanceof String){
            return Long.valueOf((String) obj);
        }
        return null;
    }

    public static Integer i(Map map,Integer key){
        Object obj = map.get(key);
        if(obj==null) return null;
        if(obj instanceof Integer){
            return (Integer)obj;
        }
        if(obj instanceof String){
            return Integer.valueOf((String) obj);
        }
        return null;
    }
    public static Integer i(Map map, String key) {
        Object obj = map.get(key);
        if (obj == null || obj.equals("")) return null;
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (obj instanceof String) {
            return Integer.valueOf((String) obj);
        }
        return null;
    }

}
