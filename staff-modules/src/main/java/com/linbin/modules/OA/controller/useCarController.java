package com.linbin.modules.OA.controller;

import com.linbin.modules.OA.entity.UseCarOrder;
import com.linbin.modules.OA.service.UseCarService;
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
 * @Date: 2020/5/8 17:47
 * @Description:
 */
@Transactional
@RequestMapping("/OA/useCar")
@RestController
public class useCarController {

    @Autowired
    private UseCarService useCarService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageUserService messageUserService;

    @PostMapping("/add")
    public Result add(@RequestBody UseCarOrder useCarOrder){
        useCarOrder.setStatus(0);
        useCarOrder.setSubmitTime(System.currentTimeMillis());
        useCarService.add(useCarOrder,null);
        return Result.ok("用车申请工单添加成功！");
    }

    @GetMapping("/getMyUseCarList")
    public Result<HashMap> getMyUseCarList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("workNo") String workNo,
                                             @RequestParam( value = "queryStartDate" ,required = false) Long queryStartDate ,
                                             @RequestParam( value = "queryEndDate" ,required = false) Long queryEndDate){
        Result<HashMap> res = new Result<>();
        List<UseCarOrder> useCarList = useCarService.getMyUseCarList(page,pageSize,workNo,queryStartDate,queryEndDate);
        Integer total = useCarService.countTotal(workNo,queryStartDate,queryEndDate);
        HashMap map = new HashMap();
        map.put("useCarList",useCarList);
        map.put("total",total);
        res.setResult(map);
        return res.success("加班列表获取成功！");
    }

    @DeleteMapping("/delOne")
    public Result delOne(@RequestParam Integer id){
        useCarService.delete(id);
        return Result.ok();
    }

    @GetMapping("/getOne")
    public Result getOne(@RequestParam Integer id){
        UseCarOrder useCarOrder = useCarService.queryOneById(id);
        return Result.ok(useCarOrder);
    }

    @PostMapping("/updateUseCarOrder")
    public Result updateUseCarOrder(@RequestBody UseCarOrder useCarOrder){
        useCarOrder.setSubmitTime(System.currentTimeMillis());
        useCarService.update(useCarOrder);
        return Result.ok();
    }

    @PutMapping("/submitApproval")
    public Result submitApproval(@RequestBody UseCarOrder useCarOrder){
        useCarOrder.setStatus(1);
        useCarService.update(useCarOrder);
        return Result.ok();
    }

    @GetMapping("/getUseCarApprovalList")
    public Result<HashMap> getUseCarApprovalList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("deptId") Integer deptId,
                                                  @RequestParam( value = "queryStartDate" ,required = false) Long queryStartDate ,
                                                  @RequestParam( value = "queryEndDate" ,required = false) Long queryEndDate){
        Result<HashMap> res = new Result<>();
        List<UseCarOrder> useCarApprovalList = useCarService.getUseCarApprovalList(page,pageSize,deptId,queryStartDate,queryEndDate);
        Integer total = useCarService.countApprovalTotal(deptId,queryStartDate,queryEndDate);
        HashMap map = new HashMap();
        map.put("useCarApprovalList",useCarApprovalList);
        map.put("total",total);
        res.setResult(map);
        return res.success("用车审批列表获取成功！");
    }

    @PostMapping("/approval")
    public Result approval(@RequestBody HashMap map){
        ObjectMapper objectMapper = new ObjectMapper();
        UseCarOrder useCarOrder = objectMapper.convertValue(map.get("useCarOrder"),UseCarOrder.class);
        Integer userId = (Integer) map.get("userId");
        Integer status = (Integer) map.get("status");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Message msg = new Message();
        msg.setTitle("【系统通知】 " + sdf.format(useCarOrder.getSubmitTime()) + "用车审批记录更新");
        msg.setContent("<p>亲！您有一条用车时间为" + sdf.format(useCarOrder.getUseCarStartTime()) + " - " + sdf.format(useCarOrder.getUseCarEndTime())+ "的用车审批记录完成审批！请前往查看！<p>");
        msg.setSendTime(System.currentTimeMillis());
        msg.setType(2);
        messageService.addPersonalMsg(msg);
        MessageUser messageUser = new MessageUser();
        messageUser.setUserId(userId);
        messageUser.setStatus(0);
        messageUser.setMsgId(msg.getId());
        messageUserService.add(messageUser,null);
        useCarOrder.setStatus(status);
        useCarService.update(useCarOrder);
        return Result.ok();
    }
}
