package itmo.is.lab1.service;

import itmo.is.lab1.DTO.model.auth.UserDTO;
import itmo.is.lab1.dao.AdminDAO;
import itmo.is.lab1.model.auth.AdminRequests;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.objMapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Service
public class AdminService {
    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private UserMapper userMapper;

    public List<UserDTO> getUsersRequests() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date oneDayAgo = calendar.getTime();

        return adminDAO.findAllByAcceptedWithDate(false, oneDayAgo).stream()
                .map(adminRequests -> userMapper.toDTO(adminRequests.getSender()))
                .toList();
    }

    public void createRequest(User sender){
        AdminRequests adminRequests = new AdminRequests();
        adminRequests.setSender(sender);
        adminRequests.setDate(new Date());
        adminDAO.save(adminRequests);
    }
}
