package com.linbin.modules.evaluation.controller;

import com.linbin.modules.evaluation.entity.Evaluation;
import com.linbin.modules.evaluation.service.EvaluationService;
import com.linbin.modules.evaluation.service.impl.EvaluationServiceImpl;
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
 * @Date: 2020/5/17 10:00
 * @Description:
 */
@RequestMapping("/evaluation")
@Transactional
@RestController
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageUserService messageUserService;

    @PostMapping("/add")
    public Result add(@RequestBody Evaluation evaluation){
        evaluationService.add(evaluation,null);
        return Result.ok();
    }

    @GetMapping("/getMyEvaluationList")
    public Result<HashMap> getMyEvaluationList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("workNo") String workNo,
                                            @RequestParam( value = "queryStartDate" ,required = false) Long queryStartDate ,
                                            @RequestParam( value = "queryEndDate" ,required = false) Long queryEndDate,
                                            @RequestParam( value = "queryStatus",required = false) Integer queryStatus){
        Result<HashMap> res = new Result<>();
        List<Evaluation> evaluationList = evaluationService.getMyEvaluationList(page,pageSize,workNo,queryStartDate,queryEndDate,queryStatus);
        Integer total = evaluationService.countTotal(workNo,queryStartDate,queryEndDate,queryStatus);
        HashMap map = new HashMap();
        map.put("evaluationList",evaluationList);
        map.put("total",total);
        res.setResult(map);
        return res.success("绩效自评列表获取成功！");
    }

    @GetMapping("/getMyEvaluationList1")
    public Result<HashMap> getMyEvaluationList1(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("workNo") String workNo,
                                               @RequestParam( value = "queryStartDate" ,required = false) Long queryStartDate ,
                                               @RequestParam( value = "queryEndDate" ,required = false) Long queryEndDate){
        Result<HashMap> res = new Result<>();
        List<Evaluation> evaluationList = evaluationService.getMyEvaluationList1(page,pageSize,workNo,queryStartDate,queryEndDate);
        Integer total = evaluationService.countTotal1(workNo,queryStartDate,queryEndDate);
        HashMap map = new HashMap();
        map.put("evaluationList",evaluationList);
        map.put("total",total);
        res.setResult(map);
        return res.success("绩效列表获取成功！");
    }

    @GetMapping("/getAllEvaluationList")
    public Result<HashMap> getAllEvaluationList(@RequestParam("page") Integer page , @RequestParam("pageSize") Integer pageSize , @RequestParam("deptId") Integer deptId,
                                               @RequestParam( value = "queryStartDate" ,required = false) Long queryStartDate ,
                                               @RequestParam( value = "queryEndDate" ,required = false) Long queryEndDate,
                                               @RequestParam( value = "queryStatus",required = false) Integer queryStatus){
        Result<HashMap> res = new Result<>();
        List<Evaluation> evaluationList = evaluationService.getAllEvaluationList(page,pageSize,deptId,queryStartDate,queryEndDate,queryStatus);
        Integer total = evaluationService.countAllTotal(deptId,queryStartDate,queryEndDate,queryStatus);
        HashMap map = new HashMap();
        map.put("evaluationList",evaluationList);
        map.put("total",total);
        res.setResult(map);
        return res.success("绩效审批列表获取成功！");
    }

    @DeleteMapping("/delOne")
    public Result delOne(@RequestParam Integer id){
        evaluationService.delete(id);
        return Result.ok();
    }

    @GetMapping("/getOne")
    public Result getOne(@RequestParam Integer id){
        Evaluation evaluation = evaluationService.queryOneById(id);
        return Result.ok(evaluation);
    }

    @PostMapping("/updateEvaluation")
    public Result updateEvaluation(@RequestBody Evaluation evaluation){
        evaluationService.update(evaluation);
        return Result.ok();
    }

    @PostMapping("/approval")
    public Result approval(@RequestBody HashMap map){
        ObjectMapper objectMapper = new ObjectMapper();
        Evaluation evaluation = objectMapper.convertValue(map.get("evaluation"),Evaluation.class);
        Integer userId = (Integer) map.get("userId");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Message msg = new Message();
        msg.setTitle("【系统通知】 " + sdf.format(evaluation.getDate()) + "绩效审批记录更新");
        msg.setContent("<p>亲！您有一条考评日期为" + sdf.format(evaluation.getDate()) + "绩效审批记录通过审批！请前往查看！<p>");
        msg.setSendTime(System.currentTimeMillis());
        msg.setType(2);
        messageService.addPersonalMsg(msg);
        MessageUser messageUser = new MessageUser();
        messageUser.setUserId(userId);
        messageUser.setStatus(0);
        messageUser.setMsgId(msg.getId());
        messageUserService.add(messageUser,null);
        evaluationService.update(evaluation);
        return Result.ok();
    }

    @PutMapping("/submitApproval")
    public Result submitApproval(@RequestBody Evaluation evaluation){
        evaluation.setStatus(1);
        evaluationService.update(evaluation);
        return Result.ok();
    }

    @GetMapping("/isEvaluation")
    public Result isEvaluation(@RequestParam("date") Long date,@RequestParam("workNo") String workNo){
        Evaluation evaluation = evaluationService.queryOneByDate(date,workNo);
        if (evaluation != null){
            return Result.ok(true);
        }
        return Result.ok(false);
    }
}
