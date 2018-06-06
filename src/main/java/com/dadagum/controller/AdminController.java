package com.dadagum.controller;

import com.dadagum.dto.ReturnJson;
import com.dadagum.service.ActivityService;
import com.dadagum.service.AdminService;
import com.dadagum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/administration")
public class AdminController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private CommentService commentService;
    /**
     * delete an activity
     * @param info_id
     * @return
     */
    @RequestMapping(value = "/activity/{info_id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> deleteActivityInfo(@PathVariable int info_id){
        System.out.println(info_id);
        return info_id > 0 && activityService.deleteActivity(info_id) ? new ReturnJson<>(null, "成功删除活动", true) : new ReturnJson<>(null, "活动不存在，删除失败", false);
    }

    /**
     * accept an activity request
     * @param type_id
     * @param info_id
     * @return
     */
    @RequestMapping(value = "/activity/acceptance", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> passActivityRequest(@RequestParam int type_id, @RequestParam int info_id){
        return adminService.passActivity(type_id, info_id) ? new ReturnJson<>(null, "活动成功通过审核", true) : new ReturnJson<>(null, "活动不存在，审核失败", false);
    }

    /**
     * deny a activity request
     * @param type_id
     * @param info_id
     * @return
     */
    @RequestMapping(value = "/activity/rejection", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> denyActivityRequest(@RequestParam int type_id, @RequestParam int info_id){
        return adminService.denyActivity(type_id, info_id) ? new ReturnJson<>(null, "活动已被成功拒绝", true) : new ReturnJson<>(null, "活动不存在，拒绝失败", false);
    }

    /**
     * delete a comment
     * @param comment_id
     * @return
     */
    @RequestMapping(value = "/comment/{comment_id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> deleteComment(@PathVariable int comment_id){
        return commentService.deleteComment(comment_id) ? new ReturnJson<>(null, "成功删除评论", true) : new ReturnJson<>(null, "删除评论失败", false);
    }
}
