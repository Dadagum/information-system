package com.dadagum.controller;

import com.dadagum.bean.Comment;
import com.dadagum.bean.User;
import com.dadagum.dto.CommentDto;
import com.dadagum.dto.ReturnJson;
import com.dadagum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 在某一个信息底下进行评论
     * @param comment
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/production", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> makeComment(@Valid Comment comment, BindingResult bindingResult, HttpSession session){
        System.out.println(comment);
        if (bindingResult.hasErrors()){
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list) System.out.println(fieldError.getField());
            return new ReturnJson<>(null, "传来的参数格式有错误", false);
        }
        User user = (User) session.getAttribute("user");
        if (user == null) return new ReturnJson<>(null, "请先登陆", false);
        comment.setUser_id(user.getUser_id());
        List<String> result = commentService.makeComment(comment);
        return result == null ? new ReturnJson<>(new CommentDto(comment), "评论成功", true) : new ReturnJson<>(null, "评论失败", false);
    }

    /**
     * 删除自己的评论
     * @param comment_id
     * @return
     */
    @RequestMapping(value = "/deletion/{comment_id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> deleteComment(@PathVariable int comment_id, HttpSession session){
        System.out.println(comment_id);
        User user = (User) session.getAttribute("user");
        int session_id = user.getUser_id();
        return commentService.deleteComment(comment_id, session_id) ? new ReturnJson<>(null, "成功删除评论", true) : new ReturnJson<>(null, "删除评论失败", false);
    }

    /**
     * 查看一个信息底下的评论
     * @param type_id
     * @param info_id
     * @return
     */
    @RequestMapping(value = "/type/{type_id}/info/{info_id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> viewSpecificComment(@PathVariable int type_id, @PathVariable int info_id){
        System.out.println(type_id + " " + info_id);
        List<CommentDto> result = commentService.getSpecificComment(type_id, info_id);
        return result == null ? new ReturnJson<>(null, "信息不存在，无法查看评论", false) : new ReturnJson<>(result, "查看评论成功", true);
    }
}
