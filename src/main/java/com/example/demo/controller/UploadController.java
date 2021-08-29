package com.example.demo.controller;


import com.example.demo.common.ReturnJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UploadController {

    private ReturnJson returnJson;

    //简单的日期格式化时间
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

    //上传图片
    @RequestMapping("/admin/upload/uploadimage")
    public Map uploadimage(@RequestParam(value = "file",required = false) MultipartFile uploadFile, HttpServletRequest req){
        String realPath = req.getServletContext().getRealPath("") + "upload/image/";//路径
        String format = sdf.format(new Date());//时间
        File folder = new File(realPath + format);//生成路径+时间
        //判断是否存在  不存在创建
        if (!folder.isDirectory()) {
            folder.mkdirs();//创建
        }
        String oldName = uploadFile.getOriginalFilename();//获取上传文件名
        if(uploadFile.getSize() > 1024*1024*10){ //判断文件是否超过大小
            return returnJson.returndata(0,"图片最大10M",new HashMap());
        }
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());//重新命名
        try {
            // 文件保存
            uploadFile.transferTo(new File(folder, newName));

            String path = "/upload/image/" + format + newName;

            // 返回上传文件的访问路径
            String filepath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/upload/image/" + format + newName;
            //构建返回方式
            Map data = new HashMap();
            data.put("filepath",path);
            data.put("filepathurl",filepath);
            return returnJson.returndata(1,"上传成功",data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnJson.returndata(0,"上传失败",new HashMap());
    }



}
