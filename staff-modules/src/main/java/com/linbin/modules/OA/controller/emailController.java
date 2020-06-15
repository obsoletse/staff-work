package com.linbin.modules.OA.controller;

import com.linbin.base.entity.User;
import com.linbin.base.service.UserService;
import com.linbin.modules.OA.entity.InBox;
import com.linbin.modules.OA.entity.OutBox;
import com.linbin.modules.OA.service.InBoxService;
import com.linbin.modules.OA.service.OutBoxService;
import com.linbin.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/11 17:57
 * @Description:
 */
@RequestMapping("/email")
@Transactional
@RestController
public class emailController {
    @Autowired
    private OutBoxService outBoxService;

    @Autowired
    private InBoxService inBoxService;

    @Autowired
    private UserService userService;

    @GetMapping("/getBoxCount")
    public Result<Object> getBoxCount(@RequestParam Integer userId){
        Integer sendBoxCount = outBoxService.getSendBoxCount(userId);
        Integer receiveBoxCount = inBoxService.getReceiveBoxCount(userId);
        Integer draftBoxCount = outBoxService.getDraftBoxCount(userId);
        Integer binBoxCount = inBoxService.getBinBoxCount(userId);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("sendBoxCount",sendBoxCount);
        map.put("receiveBoxCount",receiveBoxCount);
        map.put("draftBoxCount",draftBoxCount);
        map.put("binBoxCount",binBoxCount);
        return Result.ok(map);
    }

    @PostMapping("/sendEmail")
    public Result sendEmail(@RequestBody HashMap map){
        Integer outSender = (Integer) map.get("outSender");
        List receiveId = (List) map.get("receiveId");
        String title = (String) map.get("title");
        String content = (String) map.get("content");
        StringBuffer sb = new StringBuffer();
        /*插入收件箱*/
        for (Object receive: receiveId) {
            User user = userService.queryOneById((Integer) receive);
            sb.append(user.getRealName() + ",");
            InBox inBox = new InBox();
            inBox.setInSender(outSender);
            inBox.setInReceiver((Integer)receive);
            inBox.setTitle(title);
            inBox.setContent(content);
            inBox.setReceiveStatus(1);
            inBox.setEmailStatus(0);
            inBox.setReceiveTime(System.currentTimeMillis());
            inBoxService.add(inBox,"inSenderName");
        }
        /*插入发件箱*/
        OutBox outBox = new OutBox();
        outBox.setOutSender(outSender);
        outBox.setOutReceiver(sb.toString().substring(0,sb.toString().length() - 1));
        outBox.setSendTime(System.currentTimeMillis());
        outBox.setTitle(title);
        outBox.setContent(content);
        outBox.setStatus(1);
        outBoxService.add(outBox,null);
        return Result.ok();
    }

    @PostMapping("/saveDraft")
    public Result saveDraft(@RequestBody HashMap map){
        Integer outSender = (Integer) map.get("outSender");
        List receiveId = (List) map.get("receiveId");
        String title = (String) map.get("title");
        String content = (String) map.get("content");
        StringBuffer sb = new StringBuffer();
        for (Object receive: receiveId) {
            User user = userService.queryOneById((Integer) receive);
            sb.append(user.getRealName() + ",");
        }
        /*插入发件箱*/
        OutBox outBox = new OutBox();
        outBox.setOutSender(outSender);
        outBox.setOutReceiver(sb.toString().substring(0,sb.toString().length() - 1));
        outBox.setSendTime(System.currentTimeMillis());
        outBox.setTitle(title);
        outBox.setContent(content);
        outBox.setStatus(0);
        outBoxService.add(outBox,null);
        return Result.ok();
    }

    @GetMapping("/getSendBoxList")
    public Result<HashMap<String, Object>> getSendBoxList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("userId") Integer userId){
        Result<HashMap<String, Object>> res = new Result<HashMap<String, Object>>();
        List<OutBox> sendBoxList = outBoxService.getSendBoxList(page,pageSize,userId);
        Integer total = outBoxService.getSendBoxCount(userId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("sendBoxList",sendBoxList);
        map.put("total",total);
        res.setResult(map);
        return res.success("发件箱邮件获取成功！");
    }

    @GetMapping("/getDraftBoxList")
    public Result<HashMap<String, Object>> getDraftBoxList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("userId") Integer userId){
        Result<HashMap<String, Object>> res = new Result<HashMap<String, Object>>();
        List<OutBox> draftBoxList = outBoxService.getDraftBoxList(page,pageSize,userId);
        Integer total = outBoxService.getDraftBoxCount(userId);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("draftBoxList",draftBoxList);
        map.put("total",total);
        res.setResult(map);
        return res.success("草稿箱邮件获取成功！");
    }

    @PostMapping("/delSendEmail")
    public Result delSendEmail(@RequestBody List<OutBox> delEmails){
        for (OutBox outBox : delEmails){
            outBoxService.delete(outBox.getId());
        }
        return Result.ok();
    }

    @PostMapping("/updateEmail")
    public Result updateEmail(@RequestBody HashMap map){
        Integer emailId = (Integer) map.get("id");
        Integer outSender = (Integer) map.get("outSender");
        List receiveId = (List) map.get("receiveId");
        String title = (String) map.get("title");
        String content = (String) map.get("content");
        StringBuffer sb = new StringBuffer();
        /*插入收件箱*/
        for (Object receive: receiveId) {
            User user = userService.queryOneById((Integer) receive);
            sb.append(user.getRealName() + ",");
            InBox inBox = new InBox();
            inBox.setInSender(outSender);
            inBox.setInReceiver((Integer)receive);
            inBox.setTitle(title);
            inBox.setContent(content);
            inBox.setReceiveStatus(1);
            inBox.setEmailStatus(0);
            inBox.setReceiveTime(System.currentTimeMillis());
            inBoxService.add(inBox,"inSenderName");
        }
        /*更新发件箱*/
        OutBox outBox = new OutBox();
        outBox.setId(emailId);
        outBox.setOutSender(outSender);
        outBox.setOutReceiver(sb.toString().substring(0,sb.toString().length() - 1));
        outBox.setSendTime(System.currentTimeMillis());
        outBox.setTitle(title);
        outBox.setContent(content);
        outBox.setStatus(1);
        outBoxService.update(outBox);
        return Result.ok();
    }

    @GetMapping("/getOneEmail")
    public Result getOneEmail(@RequestParam Integer id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        OutBox outBox = outBoxService.queryOneById(id);
        String receivers = outBox.getOutReceiver();
        String[] receiverName = receivers.split(",");
        List<Integer> receiverId = new ArrayList<Integer>();
        for (String str : receiverName){
            Integer userId = userService.queryOneByRealName(str).getId();
            receiverId.add(userId);
        }
        map.put("email",outBox);
        map.put("receiverId",receiverId);
        return Result.ok(map);
    }

    @GetMapping("/getReceiveBoxList")
    public Result<HashMap<String, Object>> getReceiveBoxList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("userId") Integer userId){
        Result<HashMap<String, Object>> res = new Result<HashMap<String, Object>>();
        List<InBox> receiveBoxList = inBoxService.getReceiveBoxList(page,pageSize,userId);
        Integer total = inBoxService.getReceiveBoxCount(userId);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("receiveBoxList",receiveBoxList);
        map.put("total",total);
        res.setResult(map);
        return res.success("收件箱邮件获取成功！");
    }

    @GetMapping("/getBinBoxList")
    public Result<HashMap<String, Object>> getBinBoxList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("userId") Integer userId){
        Result<HashMap<String, Object>> res = new Result<HashMap<String, Object>>();
        List<InBox> binBoxList = inBoxService.getBinBoxList(page,pageSize,userId);
        Integer total = inBoxService.getBinBoxCount(userId);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("binBoxList",binBoxList);
        map.put("total",total);
        res.setResult(map);
        return res.success("垃圾箱邮件获取成功！");
    }

    @PostMapping("/moveToBinBox")
    public Result moveToBinBox(@RequestBody List<InBox> moveEmails){
        for (InBox inBox : moveEmails){
            inBoxService.updateReceiveStatus(0,inBox.getId());
        }
        return Result.ok();
    }

    @PostMapping("/moveToReceiveBox")
    public Result moveToReceiveBox(@RequestBody List<InBox> moveEmails){
        for (InBox inBox : moveEmails){
            inBoxService.updateReceiveStatus(1,inBox.getId());
        }
        return Result.ok();
    }

    @PostMapping("/delReceiveEmail")
    public Result delReceiveEmail(@RequestBody List<InBox> delEmails){
        for (InBox inBox : delEmails){
            inBoxService.delete(inBox.getId());
        }
        return Result.ok();
    }

    @PutMapping("/updateEmailStatus")
    public Result updateEmailStatus(@RequestBody HashMap map){
        Integer id = (Integer) map.get("id");
        inBoxService.updateEmailStatus(id);
        return Result.ok();
    }
}
