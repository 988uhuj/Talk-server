package me.risky.talk.common.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenupt@gmail.com on 14-2-24.
 * Description : TODO
 */
public class ResponseUtil {
    /**
     * HTTP响应输出流中写入字符
     * @param response HTTP响应
     * @param s 字符
     */
    public static void writeResponse(HttpServletResponse response,String s ){
        PrintWriter write = null;
        try {
//			response.setContentType("text/plain");
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            write = response.getWriter();
            write.write(s);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            if(null != write){
                write.close();
            }
        }
    }

    /**
     * HTTP响应输出流中写入字符
     */
    public static void writeResponse(HttpServletResponse response, Object obj ){
        PrintWriter write = null;
        try {
            String str = JsonUtil.fromObjectHasDateToJson(obj);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            write = response.getWriter();
            write.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(null != write){
                write.close();
            }
        }
    }


    /**
     * HTTP响应输出流中写入字符
     */
    public static void writeResponse(String action,int code ,Object body,String msg,HttpServletResponse response){
        Map m = new HashMap();
        m.put("status", code);
        m.put("body", body);
        m.put("msg", msg);
        m.put("action",action);
        writeResponse(response, JsonUtil.fromObjectToJson(m));
    }


}
