package com.testface.facesearch.controller;

import com.testface.facesearch.facelogin.FaceAdd;
import com.testface.facesearch.facelogin.FaceMatch;
import com.testface.facesearch.facelogin.FaceSearch;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class FaceController {

    @GetMapping("/faceMatch")
    public String FaceMatch(){
        return "faceMatch";
    }

    @PostMapping("/faceMatch")
    @ResponseBody
    public String faceMatch(HttpServletRequest request) {
        String despath = request.getParameter("message");//从前端接受到的base64的数据

        //人脸对比
        FaceMatch match=new FaceMatch();
        String  result=match.match(despath);
        /*
        BigDecimal b= new BigDecimal(result);
        double f1= b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        */

        return result.toString();
    }

    @GetMapping("/successMatch")
    public String successMatch(){

        return "successMatch";
    }

    @GetMapping("/faceSearch")
    public String FaceSearch(){

        return "faceSearch";
    }

    @PostMapping("/faceSearch")
    @ResponseBody
    public String faceSearch(HttpServletRequest request) {

        String despath = request.getParameter("message");//从前端接受到的base64的数据

        //人脸搜索
        FaceSearch serch=new FaceSearch();
        String  result = serch.search(despath);
        /*
        BigDecimal b= new BigDecimal(result);
        double f1= b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        */
        return result.toString();
    }

    @GetMapping("/successSearch")
    public String successSearch(){

        return "successSearch";
    }

    @GetMapping("/faceAdd")
    public String faceAdd(){
        return "faceAdd";
    }

    @PostMapping("/faceAdd")
    @ResponseBody
    public String faceAdd(HttpServletRequest request) {
        String despath = request.getParameter("message");//从前端接受到的base64的数据

        //人脸对比
        FaceAdd faceAdd=new FaceAdd();
        String result=faceAdd.add(despath);

        return result.toString();
    }

    @GetMapping("/successAdd")
    public String successAdd(){
        return "successAdd";
    }


}
