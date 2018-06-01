package com.dadagum.controller;

import com.dadagum.bean.Comment;
import com.dadagum.dto.CommentDto;
import com.dadagum.dto.ReturnJson;
import com.dadagum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * make a comment below specific info
     * @param comment
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/execution", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> makeComment(@Valid Comment comment, BindingResult bindingResult){
        System.out.println(comment);
        if (bindingResult.hasErrors()){
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list) System.out.println(fieldError.getField());
            return new ReturnJson<>(null, "fail to make comment, check the information please", false);
        }
        // use default user
        int user_id = 1; //TODO
        return commentService.makeComment(comment, user_id) ? new ReturnJson<>(comment, "comment successfully", true) : new ReturnJson<>(null, "fail to make comment", false);
    }

    /**
     * delete a comment
     * @param comment_id
     * @return
     */
    @RequestMapping(value = "/{comment_id}/deletion", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> deleteComment(@PathVariable int comment_id){
        System.out.println(comment_id);
        // use default user
        int session_id = 1; // TODO
        return commentService.deleteComment(comment_id, session_id) ? new ReturnJson<>(null, "delete successfully", true) : new ReturnJson<>(null, "fail to delete", false);
    }

    /**
     * view specific information
     * @param category
     * @param info_id
     * @return
     */
    @RequestMapping(value = "/{category}/{info_id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> viewSpecificComment(@PathVariable String category, @PathVariable int info_id){
        System.out.println(category + " " + info_id);
        List<CommentDto> result = commentService.getSpecificComment(category, info_id);
        return result == null ? new ReturnJson<>(null, "information does not exist", false) : new ReturnJson<>(result, "successfully", true);
    }
}
