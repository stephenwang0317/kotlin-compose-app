package com.wjm.springmvc.controller;

import com.wjm.springmvc.bean.BaseResponse;
import com.wjm.springmvc.bean.User;
import com.wjm.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    UserService userService;

    @Value("${file-save-path}")
    String fileSavePath;

    @ResponseBody
    @PostMapping("/upload/{id}")
    public BaseResponse uploadImg(MultipartFile img, @PathVariable Integer id) {
        String fileName = img.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString() + fileName;

        String photoPath = fileSavePath;
        File file = new File(photoPath);
        if (!file.exists()) {
            file.mkdir();
        }

        String finalPath = photoPath + File.separator + fileName;
        try {
            img.transferTo(new File(finalPath));

            User user = userService.getUserById(id);
            user.setUser_avater(fileName);
            userService.changeUserInfo(user);

            return new BaseResponse(0, fileName);
        } catch (IOException e) {
//            throw new RuntimeException(e);
            return new BaseResponse(-1, "upload failed");
        }

    }
}
