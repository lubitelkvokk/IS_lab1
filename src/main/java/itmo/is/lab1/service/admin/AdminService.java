package itmo.is.lab1.service.admin;

import itmo.is.lab1.DTO.model.data.AdminRequestsDTO;
import itmo.is.lab1.dao.AdminDAO;
import itmo.is.lab1.dao.UserDAO;
import itmo.is.lab1.model.auth.AdminRequests;
import itmo.is.lab1.model.auth.Role;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.mapper.AdminRequestsMapper;
import itmo.is.lab1.mapper.UserMapper;
import itmo.is.lab1.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private AdminRequestsMapper adminRequestsMapper;

    public List<AdminRequestsDTO> getUsersRequests() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date oneDayAgo = calendar.getTime();

        return adminDAO.findAllByAcceptedWithDate(false, oneDayAgo)
                .stream().map(adminRequestsMapper::toDTO).toList();
    }

    public void createRequest(User sender) {
        AdminRequests adminRequests = new AdminRequests();
        adminRequests.setSender(sender);
        adminRequests.setDate(new Date());
        adminDAO.save(adminRequests);
    }


    public Integer submitRequestById(Integer requestId) {
        AdminRequests adminRequests = adminDAO.findById(requestId).orElseThrow();
        adminRequests.setAccepted(true);
        User user = adminRequests.getSender();
        user.setRole(Role.ROLE_ADMIN);
        userDAO.save(user);
        return user.getId();
    }
}
