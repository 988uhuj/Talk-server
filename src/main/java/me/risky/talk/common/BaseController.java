package me.risky.talk.common;


import me.risky.talk.common.util.ResponseUtil;
import me.risky.talk.common.util.StringUtil;
import me.risky.talk.common.util.pagination.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenupt@gmail.com on 14-2-24.
 * Description : TODO
 */
public class BaseController {

    private String responseMessage=null;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }


    private Object responseObject=null;

    public Object getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
    }

    private int responseCode = 200;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponse(int responseCode, Object responseObject,String responseMessage){
        setResponseCode(responseCode);
        setResponseObject(responseObject);
        setResponseMessage(responseMessage);
    }

    public void writeResponse(HttpServletResponse httpServletResponse){
        ResponseUtil.writeResponse(null, responseCode, responseObject, responseMessage, httpServletResponse);
        resetResponse();
    }

    public void writeResponse(String action, HttpServletResponse httpServletResponse){
        ResponseUtil.writeResponse(action, responseCode, responseObject, responseMessage, httpServletResponse);
        resetResponse();
    }

    public void resetResponse(){
        setResponseCode(200);
        setResponseMessage("");
        setResponseObject(null);
    }


    /**
     * 获取分页参数对象
     * @param req
     * @return
     */
    public Page getPage(HttpServletRequest req){
        if(req.getParameter("page") == null){
            return null;
        }
//        String pageIndex = (req.getParameter("page") == null ? "1" : req.getParameter("page"));
//        String pageSize = (req.getParameter("pageSize") == null ? "10" : req.getParameter("pageSize"));
        String pageIndex =  req.getParameter("page");
        String pageSize = req.getParameter("pageSize");

        // 获取前处理线程遗留变量
        Page prePage = Page.getPage();
        if(prePage != null){
            Page.removeContext();
        }

        Page page = Page.createPage();
        if(StringUtil.notEmpty(pageIndex)){
            page.setPageNo(Integer.valueOf(pageIndex));
        }
        if(StringUtil.notEmpty(pageSize)){
            page.setPageSize(Integer.valueOf(pageSize));
        }

        thePage = page;
        return page;
    }

    public Page thePage=null;

    public Page getThePage() {
        return thePage;
    }

    public void setThePage(Page thePage) {
        Page.removeContext();
        this.thePage = thePage;
    }


}
