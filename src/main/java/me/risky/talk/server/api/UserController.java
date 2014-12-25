package me.risky.talk.server.api;

import me.risky.talk.common.BaseController;
import me.risky.talk.common.util.BaseUtil;
import me.risky.talk.common.util.JsonUtil;
import me.risky.talk.common.util.MapHelper;
import me.risky.talk.server.domain.TUser;
import me.risky.talk.server.domain.TUserExample;
import me.risky.talk.server.persistence.STUserMapper;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author <a href="mailto:chenupt@gmail.com">jfchen</a>
 * @since 12/24/14
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    STUserMapper userMapper;

    @RequestMapping("index")
    public void equipment(String param, HttpServletRequest req, HttpServletResponse res) {
        Map<String, Object> paramMap = JsonUtil.fromJsonToObject(param, Map.class);
        //String action=(req.getParameter("action") == null ? "404" : req.getParameter("action"));

        String action = MapHelper.s(paramMap, "action");

        if (action == null) {
            action = "404";
        }
        if (action.equals("404")) {
            setResponseObject(null);
            setResponseCode(404);
            setResponseMessage("ACTION IS NULL");
        } else if (action.equals("create")) {
            createUser(paramMap, req, res);
        } else if (action.equals("query/account")){
            queryUserByAccount(paramMap);
        }

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


    public void createUser(Map paramMap, HttpServletRequest request, HttpServletResponse response) {
        paramMap.remove("action");
        ObjectMapper mapper = new ObjectMapper();
        TUser user = mapper.convertValue(paramMap, TUser.class);

        TUserExample example = new TUserExample();
        TUserExample.Criteria c = example.createCriteria();
        c.andAccountEqualTo(user.getAccount());
        c.andNameEqualTo(user.getName());

        List<TUser> tUsers = userMapper.selectByExample(example);
        if (tUsers.size() > 0) {
            setResponseObject(null);
            setResponseCode(404);
            setResponseMessage("ADD USER FAIL");
            return;
        }

        user.setId(UUID.randomUUID().toString());
        user.setCreateTime(new Date());
        userMapper.insert(user);

        setResponseObject(null);
        setResponseCode(200);
        setResponseMessage("ADD USER OK");
    }

    public void queryUserByAccount(Map paramMap) {
        TUserExample example = new TUserExample();
        TUserExample.Criteria c = example.createCriteria();
        if(MapHelper.s(paramMap, "account") != null){
            c.andAccountLike("%" + MapHelper.s(paramMap, "account") + "%");
        }

        List<TUser> tUsers = userMapper.selectByExample(example);

        setResponseObject(tUsers);
        setResponseCode(200);
        setResponseMessage("OK");
    }

}
