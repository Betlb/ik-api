package com.magaza.dukkan.service;

import com.magaza.dukkan.model.User;
import com.magaza.dukkan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//personel ve login burda loginde sadce bir sifre username olacak diger personeller icin olmaayack
//user personel degil yonetici ama bu sinif personel sinifi
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Boolean getLogin(String username, String password) {

        if(username.equals("kullaniciadi")&&password.equals("1234")){
            return true;
        }
       /* List<User> users = userRepository.getUserByUsernameEquals(username);
        for (User user : users) {
            if (user.getPassword() != null && user.getPassword().equals(password)) {
                return true;
            }
        }*/
        return false;
    }


    public List<User> getUserByUserName(String userName) {
        List<User> users = userRepository.getUserByUsernameEquals(userName);
        return users;
    }




}

