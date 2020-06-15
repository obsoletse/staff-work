package com.linbin.modules.OA.controller;

import com.linbin.modules.OA.entity.WorkOverOrder;
import com.linbin.modules.OA.service.WorkOverService;
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
 * @Date: 2020/5/8 17:00
 * @Description:
 */
@Transactional
@RequestMapping("/OA/workOver")
@RestController
public class workOverController {

    @Autowired
    private WorkOverService workOverService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageUserService messageUserService;

    @PostMapping("/add")
    public Result add(@RequestBody WorkOverOrder workOverOrder){
        workOverOrder.setStatus(0);
        workOverOrder.setSubmitTime(System.currentTimeMillis());
        workOverService.add(workOverOrder,"proName");
        return Result.ok("加班申请工单添加成功！");
    }

    @GetMapping("/getMyWorkOverList")
    public Result<HashMap> getMyWorkOverList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("workNo") String workNo,
                                            @RequestParam( value = "queryStartDate" ,required = false) Long queryStartDate ,
                                            @RequestParam( value = "queryEndDate" ,required = false) Long queryEndDate){
        Result<HashMap> res = new Result<>();
        List<WorkOverOrder> workOverList = workOverService.getMyWorkOverList(page,pageSize,workNo,queryStartDate,queryEndDate);
        Integer total = workOverService.countTotal(workNo,queryStartDate,queryEndDate);
        HashMap map = new HashMap();
        map.put("workOverList",workOverList);
        map.put("total",total);
        res.setResult(map);
        return res.success("加班列表获取成功！");
    }

    @DeleteMapping("/delOne")
    public Result delOne(@RequestParam Integer id){
        workOverService.delete(id);
        return Result.ok();
    }

    @GetMapping("/getOne")
    public Result getOne(@RequestParam Integer id){
        WorkOverOrder workOverOrder = workOverService.queryOneById(id);
        return Result.ok(workOverOrder);
    }

    @PostMapping("/updateWorkOverOrder")
    public Result updateWorkOverOrder(@RequestBody WorkOverOrder workOverOrder){
        workOverOrder.setSubmitTime(System.currentTimeMillis());
        workOverService.update(workOverOrder);
        return Result.ok();
    }

    @PutMapping("/submitApproval")
    public Result submitApproval(@RequestBody WorkOverOrder workOverOrder){
        workOverOrder.setStatus(1);
        workOverOrder.setProName(null);
        workOverService.update(workOverOrder);
        return Result.ok();
    }

    @PostMapping("/approval")
    public Result approval(@RequestBody HashMap map){
        ObjectMapper objectMapper = new ObjectMapper();
        WorkOverOrder workOverOrder = objectMapper.convertValue(map.get("workOverOrder"),WorkOverOrder.class);
        Integer userId = (Integer) map.get("userId");
        Integer status = (Integer) map.get("status");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Message msg = new Message();
        msg.setTitle("【系统通知】 " + sdf.format(workOverOrder.getSubmitTime()) + "加班审批记录更新");
        msg.setContent("<p>亲！您有一条加班时间为" + sdf.format(workOverOrder.getWorkOverStartTime()) + " - " + sdf.format(workOverOrder.getWorkOverEndTime())+ "的加班审批记录完成审批！请前往查看！<p>");
        msg.setSendTime(System.currentTimeMillis());
        msg.setType(2);
        messageService.addPersonalMsg(msg);
        MessageUser messageUser = new MessageUser();
        messageUser.setUserId(userId);
        messageUser.setStatus(0);
        messageUser.setMsgId(msg.getId());
        messageUserService.add(messageUser,null);
        workOverOrder.setProName(null);
        workOverOrder.setStatus(status);
        workOverService.update(workOverOrder);
        return Result.ok();
    }

    @GetMapping("/getWorkOverApprovalList")
    public Result<HashMap> getWorkOverApprovalList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("deptId") Integer deptId,
                                                       @RequestParam( value = "queryStartDate" ,required = false) Long queryStartDate ,
                                                       @RequestParam( value = "queryEndDate" ,required = false) Long queryEndDate){
        Result<HashMap> res = new Result<>();
        List<WorkOverOrder> workOverApprovalList = workOverService.getWorkOverApprovalList(page,pageSize,deptId,queryStartDate,queryEndDate);
        Integer total = workOverService.countApprovalTotal(deptId,queryStartDate,queryEndDate);
        HashMap map = new HashMap();
        map.put("workOverApprovalList",workOverApprovalList);
        map.put("total",total);
        res.setResult(map);
        return res.success("加班审批列表获取成功！");
    }

}
