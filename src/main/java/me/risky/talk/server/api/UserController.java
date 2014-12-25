package me.risky.talk.server.api;

import me.risky.talk.common.BaseController;
import me.risky.talk.common.util.BaseUtil;
import me.risky.talk.common.util.JsonUtil;
import me.risky.talk.common.util.MapHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author <a href="mailto:chenupt@gmail.com">jfchen</a>
 * @since 12/24/14
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @RequestMapping("/index")
    public void equipment(String param, HttpServletRequest req, HttpServletResponse res) {
        System.out.println("equipment");
        Map<String, Object> paramMap = JsonUtil.fromJsonToObject(param, Map.class);
        //String action=(req.getParameter("action") == null ? "404" : req.getParameter("action"));

        String action = MapHelper.s(paramMap, "action");

        if (action == null) {
            action = "404";
        }
//        if (action.equals("404")) {
//            setResponseObject(null);
//            setResponseCode(404);
//            setResponseMessage("ACTION IS NULL");
//        }  else if (action.equals("create/equipment")) {
//            createEquipment(paramMap, req, res);
//        } else if (action.equals("query/equipment")) {
//            queryEquipment(paramMap, req, res);
//        } else if (action.equals("delete/equipment")) {
//            deleteEquipment(paramMap, req, res);
//        }

        int retCode = getResponseCode();

        if (retCode != 200) {
            BaseUtil.writeResponse(action, retCode, null, getResponseMessage(), res);
        } else {
            BaseUtil.writeResponse(action, retCode, getResponseObject(), getResponseMessage(), getThePage(), res);
        }


        setThePage(null);
        setResponseMessage(null);
        setResponseCode(200);
        setResponseObject(null);
    }

}
