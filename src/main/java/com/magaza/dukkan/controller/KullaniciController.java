package com.magaza.dukkan.controller;

import com.magaza.dukkan.model.User;
import com.magaza.dukkan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kullanici")
public class KullaniciController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public Map<String, Object> getLogin(@RequestParam String userName, @RequestParam String password) {
        Map<String, Object> m = new HashMap<>();
        Boolean result = userService.getLogin(userName,password);
        if (result) {
            Map<String, Object> user = new HashMap<>();
            user.put("id",1);
            user.put("adi","Admin");
            user.put("soyadi","User");

            m.put("user", user);
            m.put("token","thisisJWTtoken");
            m.put("success","true");
            return m;
            /*List<User> lstUser =  userService.getUserByUserName(userName);
            if (lstUser !=null && lstUser.size() > 0){
                m.put("user", lstUser.get(0));
                m.put("token","thisisJWTtoken");
                m.put("success","true");
                return m;
            } else {
                m.put("success","false");
                m.put("hata","Kullanici kodu veya parolasi hatali");
                return m;
            }*/
        } else {
            m.put("success","false");
            m.put("hata","Kullanici kodu veya parolasi hatali");
            return m;
            //return "{\"success\":false,\"hata\":\"Kullanici kodu veya parolasi hatali\"}";
        }

        /*if (userName.equals("Admin") && password.equals("1")) {
            return "{\"success\":true}";
        } else {
            return "{\"success\":false,\"hata\":\"Kullanici kodu veya parolasi hatali\"}";
        }*/
    }



}
