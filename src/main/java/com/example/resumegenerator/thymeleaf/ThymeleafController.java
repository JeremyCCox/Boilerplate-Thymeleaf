package com.example.resumegenerator.thymeleaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    Logger logger = LoggerFactory.getLogger(ThymeleafController.class);

//    @Autowired
//    PdfGenerator pdfGenerator;


    public ThymeleafController(){
    }
//
//    @GetMapping("/template")
//    public ResponseEntity<?> getPdf(){
////        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        String html = pdfGenerator.oldAuditToHtml();
//        ByteArrayOutputStream tmp = pdfGenerator.htmlToPdf(html);
//        ByteArrayResource body = new ByteArrayResource(tmp.toByteArray());
//
//        ResponseEntity<?> response = null;
//        try{
//            if(tmp != null){
//                response = new ResponseEntity<>(body,HttpStatus.OK);
//            }
//        }catch(Exception ex){
//            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return response;
//
//    }
}
