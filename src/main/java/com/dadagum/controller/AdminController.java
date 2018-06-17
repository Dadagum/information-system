package com.dadagum.controller;

import com.dadagum.dto.ReturnJson;
import com.dadagum.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/administration")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 查看所有的活动
     * @return
     */
    @RequestMapping(value = "/activity/list", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> getAllActivityInfo(){
        return new ReturnJson<>(adminService.getAllActivityInfo(), "成功", true);
    }



    /**
     * 删除一个活动
     * @param info_id 活动的id
     * @return
     */
    @RequestMapping(value = "/activity/{info_id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> deleteActivityInfo(@PathVariable int info_id){
        System.out.println(info_id);
        return info_id > 0 && adminService.deleteActivity(info_id) ? new ReturnJson<>(null, "成功删除活动", true) : new ReturnJson<>(null, "活动不存在，删除失败", false);
    }

    /**
     * 审核通过一个活动
     * @param info_id 活动id
     * @return
     */
    @RequestMapping(value = "/activity/acceptance", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> passActivityRequest(@RequestParam int info_id){
        return adminService.passActivity(info_id) ? new ReturnJson<>(null, "活动成功通过审核", true) : new ReturnJson<>(null, "活动不存在，审核失败", false);
    }

    /**
     * 拒绝通过一个活动
     * @param info_id 活动id
     * @return
     */
    @RequestMapping(value = "/activity/rejection", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> denyActivityRequest(@RequestParam int info_id){
        return adminService.denyActivity(info_id) ? new ReturnJson<>(null, "活动已被成功拒绝", true) : new ReturnJson<>(null, "活动不存在，拒绝失败", false);
    }

    /**
     * 删除一个评论
     * @param comment_id 评论id
     * @return
     */
    @RequestMapping(value = "/comment/{comment_id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ReturnJson<?> deleteComment(@PathVariable int comment_id){
        return adminService.deleteComment(comment_id) ? new ReturnJson<>(null, "成功删除评论", true) : new ReturnJson<>(null, "删除评论失败", false);
    }
}
