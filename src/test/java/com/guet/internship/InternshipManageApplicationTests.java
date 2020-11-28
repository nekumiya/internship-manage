//package com.guet.internship;
//
//import com.guet.internship.mbg.mapper.DocumentMapper;
//import com.guet.internship.mbg.model.Document;
//import com.guet.internship.mbg.model.Student;
//import org.apache.commons.collections.ListUtils;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class InternshipManageApplicationTests {
//
//    @Autowired
//    private DocumentMapper documentMapper;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Test
//    public void encodePasswordTest() {
//        String password = "123456";
//
//        String encodePassword = passwordEncoder.encode(password);
//
//        System.out.println("encodePassword = " + encodePassword);
//
//        boolean matches = passwordEncoder.matches("123456", encodePassword);
//        System.out.println("matches = " + matches);
//
//    }
//
//    @Test
//    public void downloadFile(){
////        Document document = documentMapper.selectByPrimaryKey(3);
////        String path = document.getPath();
////        System.out.println("path = " + path);
////
////        path.replace("\\", "\\\\");
//
//        String path = "/root/internship_manage/uploadFile/test/Internship_Application/1800300201/1800300201_2233.docx";
//        String[] split = path.split("/");
//        System.out.println("fileName = " + split[6] + "_" + split[5]);
//        System.out.println("path = " + path);
//    }
//
//    @Test
//    public void testFile(){
//        String path = "/root/internship_manage/uploadFile/test/Internship_Application/1800300201/1800300201_2233.doc";
//        String[] split = path.split("/");
//        String filename = split[6]+"_"+split[5]+split[7].substring(split[7].lastIndexOf("."));
//        System.out.println("filename = " + filename);
//    }
//
//    @Test
//    public void testList(){
//
//        ArrayList<Student> list1 = new ArrayList<>();
//        Student student1 = new Student();
//        Student student2 = new Student();
//        Student student3 = new Student();
//        student1.setName("1");
//        student2.setName("2");
//        student3.setName("3");
//
//        list1.add(student3);
//        list1.add(student2);
//        list1.add(student1);
//
//
//        ArrayList<Student> list2 = new ArrayList<>();
//        list2.add(student1);
//        list2.add(student2);
//
//
//        List<Student> list = ListUtils.subtract(list1, list2);
//
//        System.out.println("list = " + list.toString());
//
//    }
//
//}
