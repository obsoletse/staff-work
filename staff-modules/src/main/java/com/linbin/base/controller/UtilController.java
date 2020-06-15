package com.linbin.base.controller;

import com.linbin.entity.Upload;
import com.linbin.utils.uploadUtils;
import com.linbin.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: LinBin
 * @Date: 2020/4/6 19:42
 * @Description:
 */
@RestController
public class UtilController {

    public static final Logger logger = LoggerFactory.getLogger(UtilController.class);

    /**
     * 图片上传
     * @param picture
     * @param request
     * @return
     */
    @PostMapping("/uploadImg")
    public Result<Upload> uploadImg(@RequestParam("file") MultipartFile picture, HttpServletRequest request) throws Exception {
        Result<Upload> res = new Result();
        Upload img = new Upload();

        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        logger.info("-----原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        logger.info("-----文件类型：" + type);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + name + "." + type;

        InputStream in = null;
        byte[] data = null;
        //定义文件输入流
        File file = uploadUtils.multipartFileToFile(picture);
        try {
            in = new FileInputStream(file);
            //读取文件字节数组
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e){
            e.printStackTrace();
            return res.error500("上传失败！请检查后台系统");
        }finally {
            //删除本地临时文件
            uploadUtils.delteTempFile(file);
        }
        // 对字节数组Base64编码,并存入img对象
        BASE64Encoder encoder = new BASE64Encoder();
        String url = "data:image/gif;base64," + encoder.encode(data);
        img.setUrl(url);
        img.setFileName(fileName);

        logger.info("-----文件url：" + url.substring(0,20) + "...");
        logger.info("-----新文件名称：" + fileName);
        logger.info("-----上传成功");
        res.setResult(img);
       return res.success("上传成功！");
    }

    @PostMapping("/uploadVideo")
    public Result<Upload> uploadVideo(@RequestParam("file") MultipartFile video) throws Exception {
        Result<Upload> res = new Result();
        Upload upload = new Upload();

        //获取原始文件名称(包含格式)
        String originalFileName = video.getOriginalFilename();
        logger.info("-----原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        logger.info("-----文件类型：" + type);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        String fileName = name + "." + type;
        String dir = "E:\\毕业设计\\project\\staff-work\\upload\\video\\";//文件路径
        File fileLocation = new File(dir);
        //此处也可以在应用根目录手动建立目标上传目录
        if(!fileLocation.exists()){
            boolean isCreated  = fileLocation.mkdir();
            if(!isCreated) {
                return null;
            }
        }
        //定义文件输入流
        File file = uploadUtils.multipartFileToFile(video);
        try {
            File uploadFile = new File(dir, fileName);
            OutputStream out = new FileOutputStream(uploadFile);
            InputStream in = new FileInputStream(file);
            byte[] buffer = new byte[1024 * 1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
        } catch (Exception e){
            e.printStackTrace();
            return res.error500("上传失败！请检查后台系统");
        }finally {
            //删除本地临时文件
            uploadUtils.delteTempFile(file);
        }
        upload.setUrl(dir + fileName);
        upload.setFileName(fileName);

        logger.info("-----文件url：" + dir);
        logger.info("-----新文件名称：" + fileName);
        logger.info("-----上传成功");
        res.setResult(upload);
        return res.success("上传成功！");
    }

}
