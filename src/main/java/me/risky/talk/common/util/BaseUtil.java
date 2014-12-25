/**
 * 
 */
package me.risky.talk.common.util;


import me.risky.talk.common.util.pagination.Page;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础工具类
 * @author lyl
 *
 */
public class BaseUtil {
	/**
	 * HTTP响应输出流中写入字符
	 * @param response HTTP响应
	 * @param s 字符
	 */
	public static void writeResponse(HttpServletResponse response,String s ){
		PrintWriter write = null;
		try {
			//response.setContentType("text/plain");
            response.setContentType("text/html,charset=utf-8");

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

	

	public static void writeResponse(String action,int code ,Object body,String msg,HttpServletResponse response){
		Map m = new HashMap();
		m.put("status", code);
		m.put("body", body);
		m.put("msg", msg);
        m.put("action",action);
		writeResponse(response, JsonUtil.fromObjectToJson(m));
	}


    public static String getResponse(String action,int code ,Object body,String msg,HttpServletResponse response){
        Map m = new HashMap();
        m.put("status", code);
        m.put("body", body);
        m.put("msg", msg);
        m.put("action",action);
        return JsonUtil.fromObjectToJson(m);
    }


    public static void writeResponse(String action,int code ,Object body,String msg,Page page,HttpServletResponse response){


        Map m = new HashMap();
        m.put("status", code);
        m.put("body", body);
        m.put("msg", msg);
        m.put("action",action);

        if(page!=null){
            m.put("Total", page.getTotalNum());
            m.put("page", page.getPageNo());
        }

        writeResponse(response, JsonUtil.fromObjectToJson(m));
    }

    public static void writeResponse(Object obj,boolean b,String msg,HttpServletResponse response){
        Map m = new HashMap();
        if(b)
            m.put("status", 1000);
        else
            m.put("status", 404);

        m.put("body", obj);
        m.put("msg", msg);
        writeResponse(response, JsonUtil.fromObjectToJson(m));
    }

    /**
     * 封装返回参数
     * @param action
     * @param status
     * @param body
     * @param msg
     * @return
     */
    public static String packetResponse(String action, int status, Object body, String msg){
        Map m = new HashMap();
        m.put("status", status);
        m.put("body", body);
        m.put("msg", msg);
        m.put("action", action);
        return JsonUtil.fromObjectToJson(m);
    }
}
