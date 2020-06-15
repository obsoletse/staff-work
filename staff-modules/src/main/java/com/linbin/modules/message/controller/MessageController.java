package com.linbin.modules.message.controller;

import com.linbin.base.entity.User;
import com.linbin.base.service.UserService;
import com.linbin.modules.message.entity.Message;
import com.linbin.modules.message.entity.MessageUser;
import com.linbin.modules.message.service.MessageService;
import com.linbin.modules.message.service.MessageUserService;
import com.linbin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: LinBin
 * @Date: 2020/5/18 17:30
 * @Description:
 */
@RestController
@RequestMapping("/msg")
@Transactional
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageUserService messageUserService;

    @PostMapping("/addMsg")
    public Result<Object> add(@RequestBody Message message){
        messageService.add(message,null);
        return Result.ok();
    }

    @GetMapping("/getMsgList")
    public Result<HashMap<String, Object>> getMsgList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize ,
                                                      @RequestParam( value = "queryTitle" ,required = false) String queryTitle,
                                                      @RequestParam( value = "queryStatus" ,required = false) Integer queryStatus ,
                                                      @RequestParam( value = "queryStartDate" ,required = false) Long queryStartDate ,
                                                      @RequestParam( value = "queryEndDate" ,required = false) Long queryEndDate){
        Result<HashMap<String, Object>> res = new Result<HashMap<String, Object>>();
        List<Message> msgList = messageService.getMsgList(page,pageSize,queryTitle,queryStatus,queryStartDate,queryEndDate);
        Integer total = messageService.countTotal(queryTitle,queryStatus,queryStartDate,queryEndDate);
        HashMap<String, Object> map = new HashMap<>();
        map.put("msgList",msgList);
        map.put("total",total);
        res.setResult(map);
        return res.success("消息列表获取成功！");
    }

    @GetMapping("/getOneMsg")
    public Result<Object> getOneMsg(@RequestParam Integer id){
        Message message = messageService.queryOneById(id);
        return Result.ok(message);
    }

    @PostMapping("/updateMsg")
    public Result<Object> updateMsg(@RequestBody Message message){
        message.setSubmitTime(System.currentTimeMillis());
        messageService.update(message);
        return Result.ok();
    }

    @PostMapping("/delSelectMsg")
    public Result delSelectMsg(@RequestBody List<Message> delMsgList){
        for (Message msg : delMsgList){
            messageService.delete(msg.getId());
        }
        return Result.ok();
    }

    @DeleteMapping("/delOneMsg")
    public Result delOneMsg(@RequestParam Integer msgId){
        messageService.delete(msgId);
        return Result.ok();
    }

    @PostMapping("/sendMsg")
    public Result sendMsg(@RequestBody Message message){
        message.setSendTime(System.currentTimeMillis());
        message.setStatus(1);
        messageService.update(message);
        List<User> userList = userService.queryAll();
        for (User user : userList) {
            Integer isEnabled = user.getIsEnabled();
            if (isEnabled == 0){
                continue;//跳过本次循环
            }
            MessageUser messageUser = new MessageUser();
            messageUser.setMsgId(message.getId());
            messageUser.setUserId(user.getId());
            messageUser.setStatus(0);
            messageUserService.add(messageUser,null);
        }
        return Result.ok();
    }

    @GetMapping("/getMyMessage")
    public Result getMyMessage(@RequestParam Integer userId){
        HashMap<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> unreadMap = new HashMap<>();
        Map<String, Object> readMap = new HashMap<String, Object>();
        Map<String, Object> recycleMap = new HashMap<>();
        List<Message> unreadList = messageService.queryUnreadMsg(userId);
        Integer unreadTotal = unreadList.size();
        unreadMap.put("unreadList",unreadList);
        unreadMap.put("total",unreadTotal);
        List<Message> readList = messageService.queryReadMsg(userId);
        Integer readTotal = readList.size();
        readMap.put("readList",readList);
        readMap.put("total",readTotal);
        List<Message> recycleList = messageService.queryRecycleMsg(userId);
        Integer recycleTotal = recycleList.size();
        recycleMap.put("recycleList",recycleList);
        recycleMap.put("total",recycleTotal);
        map.put("unread",unreadMap);
        map.put("read",readMap);
        map.put("recycle",recycleMap);
        return Result.ok(map);
    }

    @PutMapping("/handleRead")
    public Result handleRead(@RequestBody HashMap map){
        Integer userId = (Integer) map.get("userId");
        Integer msgId = (Integer) map.get("msgId");
        messageUserService.updateStatus(userId,msgId,1);
        return Result.ok();
    }

    @PutMapping("/handleAllRead")
    public Result handleAllRead(@RequestBody HashMap map){
        Integer userId = (Integer) map.get("userId");
        messageUserService.handleAllRead(userId);
        return Result.ok();
    }

    @PutMapping("/handleDelete")
    public Result handleDelete(@RequestBody HashMap map){
        Integer userId = (Integer) map.get("userId");
        Integer msgId = (Integer) map.get("msgId");
        messageUserService.updateStatus(userId,msgId,2);
        return Result.ok();
    }
    @PutMapping("/handleAllDelete")
    public Result handleAllDelete(@RequestBody HashMap map){
        Integer userId = (Integer) map.get("userId");
        messageUserService.handleAllDelete(userId);
        return Result.ok();
    }

    @PutMapping("/handleRestore")
    public Result handleRestore(@RequestBody HashMap map){
        Integer userId = (Integer) map.get("userId");
        Integer msgId = (Integer) map.get("msgId");
        messageUserService.updateStatus(userId,msgId,1);
        return Result.ok();
    }

    @PutMapping("/deleteAll")
    public Result deleteAll(@RequestBody HashMap map){
        Integer userId = (Integer) map.get("userId");
        messageUserService.handleDeleteAll(userId);
        return Result.ok();
    }
}
