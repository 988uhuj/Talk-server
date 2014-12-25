package me.risky.talk.comment.web.controller;

import me.risky.talk.server.persistence.STContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenupt@gmail.com on 14-3-22.
 * Description TODO
 */

@Controller
@RequestMapping("comment")
public class CommentController {

//    @Autowired
//    private TCommentMapper tCommentMapper;

    @Autowired
    private STContentMapper stContentMapper;

//    @Autowired()
//    private TContentMapper tContentMapper;

    @RequestMapping("test")
    public void test(){
//        System.out.println("test");
//
//        TComment tComment = new TComment();
//        tComment.setUrlKey("sddfsdf");
//
//        TCommentExample example = new TCommentExample();
//        TCommentExample.Criteria c = example.createCriteria();
//        tCommentMapper.insertSelective(tComment);
////        tCommentMapper.selectByExample(example);
//
//        System.out.println("test2");
    }

    @RequestMapping("test2")
    public void test2(){
//        System.out.println("test");
//
//        TContent tContent = new TContent();
//        tContent.setId(12322L);
//        stContentMapper.insert(tContent);
//
//        System.out.println("test2");
    }

}
