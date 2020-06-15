package com.linbin.modules.OA.controller;

import com.linbin.modules.OA.entity.WorkLeaveOrder;
import com.linbin.modules.OA.service.WorkLeaveService;
import com.linbin.modules.message.entity.Message;
import com.linbin.modules.message.entity.MessageUser;
import com.linbin.modules.message.service.MessageService;
import com.linbin.modules.message.service.MessageUserService;
import com.linbin.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: LinBin
 * @Date: 2020/5/7 21:27
 * @Description:
 */
@Slf4j
@RestController
@Transactional
@RequestMapping("/OA/workLeave")
public class workLeaveController {

    @Autowired
    private WorkLeaveService workLeaveService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageUserService messageUserService;

    @PostMapping("/add")
    public Result add(@RequestBody WorkLeaveOrder workLeaveOrder){
        workLeaveOrder.setStatus(0);
        workLeaveOrder.setSubmitTime(System.currentTimeMillis());
        workLeaveService.add(workLeaveOrder,null);
        return Result.ok("请假工单添加成功！");
    }

    @GetMapping("/getMyWorkLeaveList")
    public Result<HashMap> getMyWorkLeaveList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("workNo") String workNo,
                                               @RequestParam( value = "queryStartDate" ,required = false) Long queryStartDate ,
                                               @RequestParam( value = "queryEndDate" ,required = false) Long queryEndDate){
        Result<HashMap> res = new Result<>();
        List<WorkLeaveOrder> workLeaveOrderList = workLeaveService.getMyWorkLeaveList(page,pageSize,workNo,queryStartDate,queryEndDate);
        Integer total = workLeaveService.countTotal(workNo,queryStartDate,queryEndDate);
        HashMap map = new HashMap();
        map.put("workLeaveList",workLeaveOrderList);
        map.put("total",total);
        res.setResult(map);
        return res.success("请假列表获取成功！");
    }

    @GetMapping("/getWorkLeaveApprovalList")
    public Result<HashMap> getWorkLeaveApprovalList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("deptId") Integer deptId,
                                              @RequestParam( value = "queryStartDate" ,required = false) Long queryStartDate ,
                                              @RequestParam( value = "queryEndDate" ,required = false) Long queryEndDate){
        Result<HashMap> res = new Result<>();
        List<WorkLeaveOrder> workLeaveApprovalList = workLeaveService.getWorkLeaveApprovalList(page,pageSize,deptId,queryStartDate,queryEndDate);
        Integer total = workLeaveService.countApprovalTotal(deptId,queryStartDate,queryEndDate);
        HashMap map = new HashMap();
        map.put("workLeaveApprovalList",workLeaveApprovalList);
        map.put("total",total);
        res.setResult(map);
        return res.success("请假审批列表获取成功！");
    }

    @DeleteMapping("/delOne")
    public Result delOne(@RequestParam Integer id){
        workLeaveService.delete(id);
        return Result.ok();
    }

    @GetMapping("/getOne")
    public Result getOne(@RequestParam Integer id){
        WorkLeaveOrder workLeaveOrder = workLeaveService.queryOneById(id);
        return Result.ok(workLeaveOrder);
    }

    @PostMapping("/updateWorkLeaveOrder")
    public Result updateWorkLeaveOrder(@RequestBody WorkLeaveOrder workLeaveOrder){
        workLeaveOrder.setSubmitTime(System.currentTimeMillis());
        workLeaveService.update(workLeaveOrder);
        return Result.ok();
    }

    @PutMapping("/submitApproval")
    public Result submitApproval(@RequestBody WorkLeaveOrder workLeaveOrder){
        workLeaveOrder.setStatus(1);
        workLeaveService.update(workLeaveOrder);
        return Result.ok();
    }

    @PostMapping("/approval")
    public Result approval(@RequestBody HashMap map){
        ObjectMapper objectMapper = new ObjectMapper();
        WorkLeaveOrder workLeaveOrder = objectMapper.convertValue(map.get("workOrder"),WorkLeaveOrder.class);
        Integer userId = (Integer) map.get("userId");
        Integer status = (Integer) map.get("status");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Message msg = new Message();
        msg.setTitle("【系统通知】 " + sdf.format(workLeaveOrder.getSubmitTime()) + "请假审批记录更新");
        msg.setContent("<p>亲！您有一条请假时间为" + sdf.format(workLeaveOrder.getWorkLeaveStartTime()) + " - " + sdf.format(workLeaveOrder.getWorkLeaveEndTime())+ "的请假审批记录完成审批！请前往查看！<p>");
        msg.setSendTime(System.currentTimeMillis());
        msg.setType(2);
        messageService.addPersonalMsg(msg);
        MessageUser messageUser = new MessageUser();
        messageUser.setUserId(userId);
        messageUser.setStatus(0);
        messageUser.setMsgId(msg.getId());
        messageUserService.add(messageUser,null);
        workLeaveOrder.setStatus(status);
        workLeaveService.update(workLeaveOrder);
        return Result.ok();
    }
}
