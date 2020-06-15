package com.linbin.modules.attendance.controller;

import com.linbin.modules.attendance.entity.Attendance;
import com.linbin.modules.attendance.entity.CardReplace;
import com.linbin.modules.attendance.service.AttendanceService;
import com.linbin.modules.attendance.service.CardReplaceService;
import com.linbin.modules.message.entity.Message;
import com.linbin.modules.message.entity.MessageUser;
import com.linbin.modules.message.service.MessageService;
import com.linbin.modules.message.service.MessageUserService;
import com.linbin.vo.Result;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/14 14:42
 * @Description:
 */
@RequestMapping("/cardReplace")
@RestController
@Transactional
public class CardReplaceController {

    @Autowired
    private CardReplaceService cardReplaceService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageUserService messageUserService;

    @PostMapping("/add")
    public Result add(@RequestBody CardReplace cardReplace){
        cardReplace.setApprovalStatus(0);
        cardReplace.setSubmitTime(System.currentTimeMillis());
        cardReplaceService.add(cardReplace,null);
        attendanceService.updateApprovalStatus(cardReplace.getUsername(),cardReplace.getClockTime());
        return Result.ok();
    }

    @GetMapping("/getOneApprovalInfo")
    public Result getOneApprovalInfo(@RequestParam("username") String username , @RequestParam("clockTime") Long clockTime){
        CardReplace cardReplace = cardReplaceService.getOneApprovalInfo(username,clockTime);
        return Result.ok(cardReplace);
    }

    @GetMapping("/getCardReplaceList")
    public Result<HashMap> getCardReplaceList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("deptId") Integer deptId,
                                               @RequestParam( value = "queryType" ,required = false) String queryType ,
                                               @RequestParam( value = "queryStatus" ,required = false) String queryStatus,
                                               @RequestParam( value = "queryApprovalStatus" ,required = false) String queryApproval){
        Result<HashMap> res = new Result<>();
        List<CardReplace> cardReplaceList= cardReplaceService.getCardReplaceList(page,pageSize,deptId,queryType,queryStatus,queryApproval);
        Integer total = cardReplaceService.countTotal(deptId,queryType,queryStatus,queryApproval);
        HashMap map = new HashMap();
        map.put("cardReplaceList",cardReplaceList);
        map.put("total",total);
        res.setResult(map);
        return res.success("补卡审批列表获取成功！");
    }

    @PostMapping("/approval")
    public Result approval(@RequestBody HashMap map){
        ObjectMapper objectMapper = new ObjectMapper();
        CardReplace cardReplace = objectMapper.convertValue(map.get("cardReplace"),CardReplace.class);
        Integer userId = (Integer) map.get("userId");
        Integer approvalStatus = (Integer)map.get("approvalStatus");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Message msg = new Message();
        msg.setTitle("【系统通知】 " + sdf.format(cardReplace.getClockTime()) + "补卡审批记录更新");
        msg.setContent("<p>亲！您有一条打卡日期为" + sdf.format(cardReplace.getClockTime()) + "补卡审批记录完成审批！请前往查看！<p>");
        msg.setSendTime(System.currentTimeMillis());
        msg.setType(2);
        messageService.addPersonalMsg(msg);
        MessageUser messageUser = new MessageUser();
        messageUser.setUserId(userId);
        messageUser.setStatus(0);
        messageUser.setMsgId(msg.getId());
        messageUserService.add(messageUser,null);
        //通过审批，修改打卡状态为已补卡
        if (approvalStatus == 1){
            Attendance attendance = attendanceService.queryOne(cardReplace.getUsername(),cardReplace.getClockTime(),cardReplace.getCardType(),cardReplace.getErrStatus());
            attendance.setIsReplace(1);
            attendanceService.update(attendance);
        }
        cardReplace.setApprovalStatus(approvalStatus);
        cardReplaceService.update(cardReplace);
        return Result.ok();
    }
}
