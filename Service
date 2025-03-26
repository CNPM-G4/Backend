package com.example.network.Service;

import com.example.network.DTO.*;
import com.example.network.DTO.Response.totalResponseDTO;
import com.example.network.Entity.User;
import com.example.network.Entity.used;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class UserService  {

    @PersistenceContext
    private EntityManager entityManager;

    // @SuppressWarnings("unused")
    @Autowired
    private ModelMapper modelMapper;

    public Boolean register(String phone, String name, String password)
    {

        if( findAccountByPhone(phone) == null)
        {

            User user = new User();
            user.setPhone(phone);
            user.setName(name);
            user.setPassword(password);
            entityManager.persist(user);
            return true;
        }
        else{
            return false;
        }
    }
    
    public User findAccountByName(String name)
    {
        String sql = "select * from user where name = '" + name + "';";
        Query query = entityManager.createNativeQuery(sql, User.class);
        List<User> results = query.getResultList();
        User result = results.stream().findFirst().orElse(null);
        if (result != null) {
            return result;
        }
        return null;
    }

    public User findAccountByPhone(String phone)
    {
        String sql = "select * from user where phone = '" + phone + "';";
        Query query = entityManager.createNativeQuery(sql, User.class);
        List<User> results = query.getResultList();
        User result = results.stream().findFirst().orElse(null);
        if (result != null) {
            return result;
        }
        return null;
    }

    public UserDTO findByPhoneNumber(String phone)
    {
        String sql = "select * from user where phone = '" + phone + "';";
        Query query = entityManager.createNativeQuery(sql, User.class);
        List<User> results = query.getResultList();
        User result = results.getFirst();
        String sql1 = "select * from used where userID = " + result.getID();
        Query query1 = entityManager.createNativeQuery(sql1, used.class);
        List<used> rs = query1.getResultList();
        UserDTO user = new UserDTO();
        user.setName(result.getName());
        user.setID(result.getUserID());
        return user;
    }

    public List<UserDTO> findAll()
    {
        String sql = "select * from user;";
        Query query = entityManager.createNativeQuery(sql, User.class);
        List<User> results = query.getResultList();
        List<UserDTO> all = new ArrayList<>();
        for (User result : results){
            String sql1 = "select * from used where userID = " + result.getID();
            Query query1 = entityManager.createNativeQuery(sql1, used.class);
            List<used> rs = query1.getResultList();
            UserDTO user = new UserDTO();
            user.setName(result.getName());
            all.add(user);
        }
        return all;
    }


    public List<adminDTO> adminFindAll()
    {
        String sql = "select * from user;";
        Query query = entityManager.createNativeQuery(sql, User.class);
        List<User> results = query.getResultList();
        List<adminDTO> all = new ArrayList<>();
        for (User result : results){
            String sql1 = "select * from used where userID = " + result.getID();
            Query query1 = entityManager.createNativeQuery(sql1, used.class);
            List<used> rs = query1.getResultList();
            adminDTO user = new adminDTO();
            user.setName(result.getName());
            user.setID(result.getUserID());
            user.setPhone(result.getPhone());
            user.setPassword(result.getPassword());
            user.setStatus(result.isStatus());
            all.add(user);
        }
        return all;
    }

    public boolean checkDay(User user, String date)
    {
        for (used Used : user.getUsedList()){
            if(Used.getDay().equals(date))
            {
                return true;
            }
        }
        return false;
    }


    // mỗi ngày chạy 1 phát để reset speed và time use, time count
    public void writeUsedData()
    {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateOnly = dateFormat.format(date);
        String sql = "select * from user;";
        Query query = entityManager.createNativeQuery(sql, User.class);
        List<User> results = query.getResultList();

        for (User user : results){
            if (checkDay(user, dateOnly) == false)
            {
                used usage = new used();
                double randomSpeed = 200 + (200 - 60) * Math.random();
                randomSpeed = Math.round(randomSpeed * 10.0) / 10.0;
                double randomTime = 8 + (8-3)*Math.random();
                randomTime = Math.round(randomTime*10.0)/10.0;
                usage.setUser(user);
                usage.setDay(dateOnly);
                usage.setSpeed(randomSpeed);
                usage.setTimeUse(randomTime);
                usage.setTotal(randomSpeed*randomTime);
                entityManager.persist(usage);
            }
        }

    }

    public String getNameByID(int ID)
    {
        String sql = "SELECT name FROM user WHERE userID = " + ID;
        Query query = entityManager.createNativeQuery(sql);
        return (String) query.getSingleResult();
    }

    public homeDTO findByID(int ID)
    {
        homeDTO result = new homeDTO();
        result.setName(getNameByID(ID));
        String sql = "SELECT total FROM used WHERE userID = " + ID;
        Query query1 = entityManager.createNativeQuery(sql);
        List<Double> results =  query1.getResultList();
        if(results.size() > 7)
        {
            List<Double> last7 = new ArrayList<>();
            for (int i = results.size() - 7; i < results.size(); i++)
            {
                last7.add(results.get(i));
            }
            result.setTotal(last7);
            return result;
        }
        result.setTotal(results);
        return result;
    }

    public historyDTO findHistoryByID(int ID)
    {
        historyDTO result = new historyDTO();
        result.setName(getNameByID(ID));
        String sql = "SELECT login FROM logged WHERE userID = " + ID;
        Query query1 = entityManager.createNativeQuery(sql);
        List<String> results =  query1.getResultList();
        if(results.size() > 7)
        {
            List<String> last7 = new ArrayList<>();
            for (int i = results.size() - 7; i < results.size(); i++)
            {
                last7.add(results.get(i));
            }
            result.setDate(last7);
            return result;
        }
        result.setDate(results);
        return result;
    }

    public totalDTO findTotalByID(int ID)
    {
        totalDTO result = new totalDTO();
        result.setName(getNameByID(ID));
        String sql = "SELECT day, total FROM used WHERE userID = " + ID;
        Query query1 = entityManager.createNativeQuery(sql);
        List<totalResponseDTO> results =  query1.getResultList();
        if(results.size() > 7)
        {
            List<totalResponseDTO> last7 = new ArrayList<>();
            for (int i = results.size() - 7; i < results.size(); i++)
            {
                last7.add(results.get(i));
            }
            result.setTotal(last7);
            return result;
        }
        result.setTotal(results);
        return result;
    }

    public detailDTO adminFindDetailInfor(int ID)
    {
        int rant =3 + (int)(Math.random() * (5 - 3 + 1)) ;
        String sql = "select * from used where ID = " + ID;
        Query query1 = entityManager.createNativeQuery(sql, used.class);
        List<used> results = query1.getResultList();
        used temp = results.get(results.size()-1);
        detailDTO result = new detailDTO();
        result.setDay(temp.getDay());
        result.setSpeed(temp.getSpeed());
        result.setCount(rant);
        return result;
    }

}
