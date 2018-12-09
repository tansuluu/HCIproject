package com.example.HCI.service;


import com.example.HCI.model.Role;
import com.example.HCI.model.User;
import com.example.HCI.repository.RoleRepository;
import com.example.HCI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


@Service("userService")
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user,String role) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole(role);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public ArrayList<User> getAllByStatus(String status){
        ArrayList<User> list=(ArrayList<User>)userRepository.getAllByStatus(status);
        ArrayList<User> finalist=new ArrayList<>();
        finalist.add(list.get(0));
        finalist.add(list.get(1));
        finalist.add(list.get(2));
        return finalist;
    }
    public ArrayList<User> getAll()
    {
        return (ArrayList) userRepository.getAllByOrderByAboutMe();
    }

    public ArrayList<User> findByName(String name){
        return (ArrayList<User>) userRepository.findAllByName(name);
    }
}
