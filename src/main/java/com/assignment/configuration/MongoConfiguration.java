package com.assignment.configuration;

import com.assignment.model.Assignment;
import com.assignment.model.User;
import com.assignment.repository.AssignmentRepository;
import com.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.text.SimpleDateFormat;


@EnableMongoRepositories(basePackages = "com.assignment.repository")
@Configuration
public class MongoConfiguration implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;

    //   @Autowired
//    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        Assignment a = new Assignment();

        user.setEmail("sanka@gmail.com");
//    user.setPassword(passwordEncoder.encode("12346"));
        //user.setImage(new byte[]{});
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        a.setAssignmentFile(new byte[]{});
        a.setDescription("sdfsfsdfsdfsdfsdfsddfgdfgdfgdfg  erterterter rtertertertre  rtertertr tretf" +
                "  sdfsfsdfsdfsdfsdfsddfgdfgdfgdfg  erterterter rtertertertre  rtertertr tretf");
        a.setEndDate(null);
//        a.setCreateDate(sdf.parse("2020/08/06"));
        a.setSubject("data mining");

//        assignmentRepository.save(a);
//     userRepository.save(user);
    }
}
