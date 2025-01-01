package com.example.SRM_Backend.sampleData;


import com.example.SRM_Backend.model.Role;
import com.example.SRM_Backend.model.User;
import com.example.SRM_Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SampleUserData {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public List<User> createUsers() {
        List<User> users = new ArrayList<>();

        // Dữ liệu giả lập cho 20 người dùng
        String[] names = {"Nguyen Anh", "Tran Bao", "Le Minh", "Pham Huong", "Hoang Mai",
                "Nguyen Thanh", "Trinh Linh", "Do Thi", "Ngo Bao", "Le Hoang",
                "Duong Duy", "Bui Thi", "Vu Thi", "Ngo Anh", "Mai Minh",
                "Pham Thanh", "Nguyen Tuan", "Le Hoang Anh", "Trinh Lan", "Mai Bao"};
        String[] usernames = {"nguyen.anh", "tran.bao", "le.minh", "pham.huong", "hoang.mai",
                "nguyen.thanh", "trinh.linh", "do.thi", "ngo.bao", "le.hoang",
                "duong.duy", "bui.thi", "vu.thi", "ngo.anh", "mai.minh",
                "pham.thanh", "nguyen.tuan", "le.hoang.anh", "trinh.lan", "mai.bao"};
        String[] emails = {"nguyen.anh@gmail.com", "tran.bao@gmail.com", "le.minh@gmail.com",
                "pham.huong@gmail.com", "hoang.mai@gmail.com", "nguyen.thanh@gmail.com",
                "trinh.linh@gmail.com", "do.thi@gmail.com", "ngo.bao@gmail.com",
                "le.hoang@gmail.com", "duong.duy@gmail.com", "bui.thi@gmail.com",
                "vu.thi@gmail.com", "ngo.anh@gmail.com", "mai.minh@gmail.com",
                "pham.thanh@gmail.com", "nguyen.tuan@gmail.com", "le.hoang.anh@gmail.com",
                "trinh.lan@gmail.com", "mai.bao@gmail.com"};

        // Mật khẩu mã hóa cho tất cả người dùng

        ;  // Bạn sẽ mã hóa mật khẩu sau này
        String password="123456";
        String avatar = "https://res.cloudinary.com/deg6p9a9o/image/upload/v1735007909/rblsybwoz2esdx2twa2u.jpg";

        // Tạo 20 người dùng
        for (int i = 0; i < names.length; i++) {
            User user = new User();
            user.setName(names[i]);
            user.setUsername(usernames[i]);
            user.setDob(new Date()); // Giả sử ngày sinh là ngày hiện tại
            user.setEmail(emails[i]);
            user.setAddress("Address " + (i+1));
            user.setAvatar(avatar);
            user.setRole(Role.USER);  // Tất cả người dùng đều có role là USER
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
        }
        return users;
    }


}

