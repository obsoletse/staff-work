package com.linbin.modules.OA.controller;

import com.linbin.base.entity.User;
import com.linbin.base.service.UserService;
import com.linbin.modules.OA.entity.WorkDesertOrder;
import com.linbin.modules.OA.service.WorkDesertService;
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
 * @Date: 2020/5/8 17:48
 * @Description:
 */
@Transactional
@RestController
@RequestMapping("/OA/workDesert")
public class workDesertController {

    @Autowired
    private WorkDesertService workDesertService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageUserService messageUserService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Result add(@RequestBody WorkDesertOrder workDesertOrder){
        workDesertOrder.setStatus(0);
        workDesertOrder.setSubmitTime(System.currentTimeMillis());
        workDesertService.add(workDesertOrder,null);
        return Result.ok("离职申请工单添加成功！");
    }

    @GetMapping("/getMyWorkDesertList")
    public Result<HashMap> getMyWorkDesertList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("workNo") String workNo,
                                            @RequestParam( value = "queryStartDate" ,required = false) Long queryStartDate ,
                                            @RequestParam( value = "queryEndDate" ,required = false) Long queryEndDate){
        Result<HashMap> res = new Result<>();
        List<WorkDesertOrder> workDesertList = workDesertService.getMyWorkDesertList(page,pageSize,workNo,queryStartDate,queryEndDate);
        Integer total = workDesertService.countTotal(workNo,queryStartDate,queryEndDate);
        HashMap map = new HashMap();
        map.put("workDesertList",workDesertList);
        map.put("total",total);
        res.setResult(map);
        return res.success("离职列表获取成功！");
    }

    @DeleteMapping("/delOne")
    public Result delOne(@RequestParam Integer id){
        workDesertService.delete(id);
        return Result.ok();
    }

    @GetMapping("/getOne")
    public Result getOne(@RequestParam Integer id){
        WorkDesertOrder workDesertOrder = workDesertService.queryOneById(id);
        return Result.ok(workDesertOrder);
    }

    @PostMapping("/updateWorkDesertOrder")
    public Result updateWorkDesertOrder(@RequestBody WorkDesertOrder workDesertOrder){
        workDesertOrder.setSubmitTime(System.currentTimeMillis());
        workDesertService.update(workDesertOrder);
        return Result.ok();
    }

    @PutMapping("/submitApproval")
    public Result submitApproval(@RequestBody WorkDesertOrder workDesertOrder){
        workDesertOrder.setStatus(1);
        workDesertService.update(workDesertOrder);
        return Result.ok();
    }

    @GetMapping("/getWorkDesertApprovalList")
    public Result<HashMap> getWorkDesertApprovalList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("deptId") Integer deptId,
                                                  @RequestParam( value = "queryStartDate" ,required = false) Long queryStartDate ,
                                                  @RequestParam( value = "queryEndDate" ,required = false) Long queryEndDate){
        Result<HashMap> res = new Result<>();
        List<WorkDesertOrder> workDesertApprovalList = workDesertService.getWorkDesertApprovalList(page,pageSize,deptId,queryStartDate,queryEndDate);
        Integer total = workDesertService.countApprovalTotal(deptId,queryStartDate,queryEndDate);
        HashMap map = new HashMap();
        map.put("workDesertApprovalList",workDesertApprovalList);
        map.put("total",total);
        res.setResult(map);
        return res.success("离职审批列表获取成功！");
    }

    @PostMapping("/approval")
    public Result approval(@RequestBody HashMap map){
        ObjectMapper objectMapper = new ObjectMapper();
        WorkDesertOrder workDesertOrder = objectMapper.convertValue(map.get("workDesertOrder"),WorkDesertOrder.class);
        Integer userId = (Integer) map.get("userId");
        Integer status = (Integer) map.get("status");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Message msg = new Message();
        msg.setTitle("【系统通知】 " + sdf.format(workDesertOrder.getSubmitTime()) + "离职审批记录更新");
        msg.setContent("<p>亲！您有一条离职时间为" + sdf.format(workDesertOrder.getLeaveTime()) + "的离职审批记录完成审批！请前往查看！<p>");
        msg.setSendTime(System.currentTimeMillis());
        msg.setType(2);
        messageService.addPersonalMsg(msg);
        MessageUser messageUser = new MessageUser();
        messageUser.setUserId(userId);
        messageUser.setStatus(0);
        messageUser.setMsgId(msg.getId());
        messageUserService.add(messageUser,null);
        workDesertOrder.setStatus(status);
        workDesertService.update(workDesertOrder);
        //同意离职更新用户状态为离职员工
        if (status == 2){
            User user = userService.queryOneById(userId);
            user.setIsEnabled(0);
            userService.update(user);
        }
        return Result.ok();
    }
}
