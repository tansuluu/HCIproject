package com.example.HCI.service;


import com.example.HCI.model.Role;
import com.example.HCI.model.User;
import com.example.HCI.repository.RoleRepository;
import com.example.HCI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;


@Service("userService")
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

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

    public void sendTokenToReset(User user, HttpServletRequest request){
        user.setToken(UUID.randomUUID().toString());

        String appUrl = request.getScheme() + "://" + request.getServerName();

        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(user.getEmail());
        registrationEmail.setSubject("Registration Confirmation");
        registrationEmail.setText("To reset your password on opentravel site, please click the link below:\n"
                + appUrl + ":8080/confirm?token=" + user.getToken());
        registrationEmail.setFrom("noreply@domain.com");
        emailService.sendEmail(registrationEmail);
    }
}
