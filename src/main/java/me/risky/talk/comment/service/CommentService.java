package me.risky.talk.comment.service;

import me.risky.talk.comment.domain.TComment;
import me.risky.talk.comment.domain.TCommentExample;
import me.risky.talk.comment.persistence.TCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenupt@gmail.com on 14-3-22.
 * Description TODO
 */
@Service
public class CommentService {

    @Autowired
    private TCommentMapper tCommentMapper;

    public void createNewComment(TComment comment){
        tCommentMapper.insert(comment);
    }

    public void deleteComment(int id){
        // TODO 暂不完成
}

    public List<TComment> queryCommentList(int type, String key){
        TCommentExample example = new TCommentExample();
        TCommentExample.Criteria c = example.createCriteria();
        c.andTypeEqualTo(type);
        c.andKeyEqualTo(key);
        List<TComment> list =  tCommentMapper.selectByExample(example);
        return list;
    }

}
