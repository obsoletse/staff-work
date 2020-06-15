package com.linbin.modules.OA.controller;

import com.linbin.modules.OA.entity.WorkBusinessOrder;
import com.linbin.modules.OA.entity.WorkBusinessOrder;
import com.linbin.modules.OA.service.WorkBusinessService;
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
 * @Date: 2020/5/7 22:12
 * @Description:
 */
@RestController
@Transactional
@RequestMapping("/OA/workBusiness")
public class workBusinessController {

    @Autowired
    private WorkBusinessService workBusinessService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageUserService messageUserService;

    @PostMapping("/add")
    public Result add(@RequestBody WorkBusinessOrder workBusinessOrder){
        workBusinessOrder.setStatus(0);
        workBusinessOrder.setSubmitTime(System.currentTimeMillis());
        workBusinessService.add(workBusinessOrder,null);
        return Result.ok("出差申请工单添加成功！");
    }

    @GetMapping("/getMyWorkBusinessList")
    public Result<HashMap> getMyWorkBusinessList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("workNo") String workNo,
                                              @RequestParam( value = "queryStartDate" ,required = false) Long queryStartDate ,
                                              @RequestParam( value = "queryEndDate" ,required = false) Long queryEndDate){
        Result<HashMap> res = new Result<>();
        List<WorkBusinessOrder> workBusinessList = workBusinessService.getMyWorkBusinessList(page,pageSize,workNo,queryStartDate,queryEndDate);
        Integer total = workBusinessService.countTotal(workNo,queryStartDate,queryEndDate);
        HashMap map = new HashMap();
        map.put("workBusinessList",workBusinessList);
        map.put("total",total);
        res.setResult(map);
        return res.success("出差列表获取成功！");
    }

    @DeleteMapping("/delOne")
    public Result delOne(@RequestParam Integer id){
        workBusinessService.delete(id);
        return Result.ok();
    }

    @GetMapping("/getOne")
    public Result getOne(@RequestParam Integer id){
        WorkBusinessOrder workBusinessOrder = workBusinessService.queryOneById(id);
        return Result.ok(workBusinessOrder);
    }

    @PostMapping("/updateWorkBusinessOrder")
    public Result updateWorkBusinessOrder(@RequestBody WorkBusinessOrder workBusinessOrder){
        workBusinessOrder.setSubmitTime(System.currentTimeMillis());
        workBusinessService.update(workBusinessOrder);
        return Result.ok();
    }

    @PutMapping("/submitApproval")
    public Result submitApproval(@RequestBody WorkBusinessOrder workBusinessOrder){
        workBusinessOrder.setStatus(1);
        workBusinessService.update(workBusinessOrder);
        return Result.ok();
    }

    @GetMapping("/getWorkBusinessApprovalList")
    public Result<HashMap> getWorkBusinessApprovalList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("deptId") Integer deptId,
                                                    @RequestParam( value = "queryStartDate" ,required = false) Long queryStartDate ,
                                                    @RequestParam( value = "queryEndDate" ,required = false) Long queryEndDate){
        Result<HashMap> res = new Result<>();
        List<WorkBusinessOrder> workBusinessApprovalList = workBusinessService.getWorkBusinessApprovalList(page,pageSize,deptId,queryStartDate,queryEndDate);
        Integer total = workBusinessService.countApprovalTotal(deptId,queryStartDate,queryEndDate);
        HashMap map = new HashMap();
        map.put("workBusinessApprovalList",workBusinessApprovalList);
        map.put("total",total);
        res.setResult(map);
        return res.success("出差审批列表获取成功！");
    }
    
    @PostMapping("/approval")
    public Result approval(@RequestBody HashMap map){
        ObjectMapper objectMapper = new ObjectMapper();
        WorkBusinessOrder workBusinessOrder = objectMapper.convertValue(map.get("workBusinessOrder"),WorkBusinessOrder.class);
        Integer userId = (Integer) map.get("userId");
        Integer status = (Integer) map.get("status");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Message msg = new Message();
        msg.setTitle("【系统通知】 " + sdf.format(workBusinessOrder.getSubmitTime()) + "出差审批记录更新");
        msg.setContent("<p>亲！您有一条出差时间为" + sdf.format(workBusinessOrder.getWorkBusinessStartTime()) + " - " + sdf.format(workBusinessOrder.getWorkBusinessEndTime())+ "的出差审批记录完成审批！请前往查看！<p>");
        msg.setSendTime(System.currentTimeMillis());
        msg.setType(2);
        messageService.addPersonalMsg(msg);
        MessageUser messageUser = new MessageUser();
        messageUser.setUserId(userId);
        messageUser.setStatus(0);
        messageUser.setMsgId(msg.getId());
        messageUserService.add(messageUser,null);
        workBusinessOrder.setStatus(status);
        workBusinessService.update(workBusinessOrder);
        return Result.ok();
    }
}
