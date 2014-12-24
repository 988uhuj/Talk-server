package me.risky.talk.web.controller;

import me.risky.talk.server.domain.TContent;
import me.risky.talk.server.persistence.STContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by chenupt@gmail.com on 14-3-22.
 * Description TODO
 */

@Controller
@RequestMapping("web")
public class WebController {

    @RequestMapping("main")
    public ModelAndView toMain(ModelAndView mav){
        System.out.println("toMain");
        //已配置视图路径和后缀，只需填写文件名
        String view = "user_manage";
        mav.setViewName(view);
        return mav;
    }

}
