//package com.guet.internship.controller;
//
//
//import org.apache.commons.io.IOUtils;
//import org.jodconverter.DocumentConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//
//@Controller
//public class Mycontroller {
//
//    // 第一步：转换器直接注入
//    @Autowired
//    private DocumentConverter converter;
//
//    @Autowired
//    private HttpServletResponse response;
//
//    @RequestMapping("toPdfFile")
//    public String toPdfFile() {
//        File file = new File("C:\\Users\\Nekumiya\\Desktop\\1800300217黄锦泰Android实验报告（二）.doc");//需要转换的文件
//        try {
//            File newFile = new File("C:\\Users\\Nekumiya\\Desktop");//转换之后文件生成的地址
//            if (!newFile.exists()) {
//                newFile.mkdirs();
//            }
//            //文件转化
//            converter.convert(file).to(new File("C:\\Users\\Nekumiya\\Desktop\\hello.pdf")).execute();
//            //使用response,将pdf文件以流的方式发送的前端
//            ServletOutputStream outputStream = response.getOutputStream();
//            InputStream in = new FileInputStream(new File("C:\\Users\\Nekumiya\\Desktop\\hello.pdf"));// 读取文件
//            // copy文件
//            int i = IOUtils.copy(in, outputStream);
//            System.out.println(i);
//            in.close();
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "This is to pdf";
//    }
//
//}
