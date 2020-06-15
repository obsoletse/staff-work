package com.linbin.modules.OA.controller;

import com.linbin.modules.OA.entity.WorkOutOrder;
import com.linbin.modules.OA.service.WorkOutService;
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
 * @Date: 2020/5/8 12:05
 * @Description:
 */
@RestController
@Transactional
@RequestMapping("/OA/workOut")
public class workOutController {

    @Autowired
    private WorkOutService workOutService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageUserService messageUserService;

    @PostMapping("/add")
    public Result add(@RequestBody WorkOutOrder workOutOrder){
        workOutOrder.setStatus(0);
        workOutOrder.setSubmitTime(System.currentTimeMillis());
        workOutService.add(workOutOrder,null);
        return Result.ok("外勤工单添加成功！");
    }
    

    @GetMapping("/getMyWorkOutList")
    public Result<HashMap> getMyWorkOutList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("workNo") String workNo,
                                                 @RequestParam( value = "queryStartDate" ,required = false) Long queryStartDate ,
                                                 @RequestParam( value = "queryEndDate" ,required = false) Long queryEndDate){
        Result<HashMap> res = new Result<>();
        List<WorkOutOrder> workOutList = workOutService.getMyWorkOutList(page,pageSize,workNo,queryStartDate,queryEndDate);
        Integer total = workOutService.countTotal(workNo,queryStartDate,queryEndDate);
        HashMap map = new HashMap();
        map.put("workOutList",workOutList);
        map.put("total",total);
        res.setResult(map);
        return res.success("外勤列表获取成功！");
    }

    @DeleteMapping("/delOne")
    public Result delOne(@RequestParam Integer id){
        workOutService.delete(id);
        return Result.ok();
    }

    @GetMapping("/getOne")
    public Result getOne(@RequestParam Integer id){
        WorkOutOrder workOutOrder = workOutService.queryOneById(id);
        return Result.ok(workOutOrder);
    }

    @PostMapping("/updateWorkOutOrder")
    public Result updateWorkOutOrder(@RequestBody WorkOutOrder workOutOrder){
        workOutOrder.setSubmitTime(System.currentTimeMillis());
        workOutService.update(workOutOrder);
        return Result.ok();
    }

    @PutMapping("/submitApproval")
    public Result submitApproval(@RequestBody WorkOutOrder workOutOrder){
        workOutOrder.setStatus(1);
        workOutService.update(workOutOrder);
        return Result.ok();
    }

    @GetMapping("/getWorkOutApprovalList")
    public Result<HashMap> getWorkOutApprovalList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("deptId") Integer deptId,
                                                       @RequestParam( value = "queryStartDate" ,required = false) Long queryStartDate ,
                                                       @RequestParam( value = "queryEndDate" ,required = false) Long queryEndDate){
        Result<HashMap> res = new Result<>();
        List<WorkOutOrder> workOutApprovalList = workOutService.getWorkOutApprovalList(page,pageSize,deptId,queryStartDate,queryEndDate);
        Integer total = workOutService.countApprovalTotal(deptId,queryStartDate,queryEndDate);
        HashMap map = new HashMap();
        map.put("workOutApprovalList",workOutApprovalList);
        map.put("total",total);
        res.setResult(map);
        return res.success("外勤审批列表获取成功！");
    }

    @PostMapping("/approval")
    public Result approval(@RequestBody HashMap map){
        ObjectMapper objectMapper = new ObjectMapper();
        WorkOutOrder workOutOrder = objectMapper.convertValue(map.get("workOutOrder"),WorkOutOrder.class);
        Integer userId = (Integer) map.get("userId");
        Integer status = (Integer) map.get("status");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Message msg = new Message();
        msg.setTitle("【系统通知】 " + sdf.format(workOutOrder.getSubmitTime()) + "外勤审批记录更新");
        msg.setContent("<p>亲！您有一条外勤时间为" + sdf.format(workOutOrder.getWorkOutStartTime()) + " - " + sdf.format(workOutOrder.getWorkOutEndTime())+ "的外勤审批记录完成审批！请前往查看！<p>");
        msg.setSendTime(System.currentTimeMillis());
        msg.setType(2);
        messageService.addPersonalMsg(msg);
        MessageUser messageUser = new MessageUser();
        messageUser.setUserId(userId);
        messageUser.setStatus(0);
        messageUser.setMsgId(msg.getId());
        messageUserService.add(messageUser,null);
        workOutOrder.setStatus(status);
        workOutService.update(workOutOrder);
        return Result.ok();
    }
}
