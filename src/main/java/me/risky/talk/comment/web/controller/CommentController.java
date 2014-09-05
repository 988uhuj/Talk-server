package me.risky.talk.comment.web.controller;

import me.risky.talk.comment.domain.TComment;
import me.risky.talk.comment.domain.TCommentExample;
import me.risky.talk.comment.persistence.TCommentMapper;
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

    @Autowired
    private TCommentMapper tCommentMapper;

    @RequestMapping("test")
    public void test(){
        System.out.println("test");

        TComment tComment = new TComment();
        tComment.setUrlKey("sddfsdf");

        TCommentExample example = new TCommentExample();
        TCommentExample.Criteria c = example.createCriteria();
        tCommentMapper.insertSelective(tComment);
//        tCommentMapper.selectByExample(example);

        System.out.println("test2");
    }

}
