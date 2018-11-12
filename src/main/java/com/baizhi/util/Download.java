package com.baizhi.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

public class Download {

    public static String uitils(MultipartFile files, String fileName, HttpSession session) throws IOException {
        String realPath = session.getServletContext().getRealPath(fileName);//获取文件本地路径
        files.transferTo(new File(realPath,files.getOriginalFilename()));
        return fileName+"/"+files.getOriginalFilename();
    }

}
