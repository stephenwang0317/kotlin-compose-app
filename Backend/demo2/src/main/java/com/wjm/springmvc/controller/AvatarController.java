package com.wjm.springmvc.controller;

import com.wjm.springmvc.bean.BaseResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@CrossOrigin
@Controller
@RequestMapping("/avatar")
public class AvatarController {

    @Value("${file-save-path}")
    String fileSavePath;

    @ResponseBody
    @PostMapping("/upload")
    public BaseResponse uploadImg(MultipartFile img, HttpSession session) throws IOException {
        String fileName = img.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString() + fileName;

        ServletContext servletContext = session.getServletContext();
//        String photoPath = fileSavePath;
        String photoPath = servletContext.getRealPath("img");
        File file = new File(photoPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String finalPath = photoPath + File.separator + fileName;
        img.transferTo(new File(finalPath));
        return new BaseResponse(0, "upload success");
    }
}
