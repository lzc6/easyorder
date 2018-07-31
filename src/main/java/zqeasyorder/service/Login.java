package zqeasyorder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import zqeasyorder.domain.ResultMap;

import java.util.List;
import java.util.Map;

@Service
public class Login {
    @Autowired
    @Qualifier("easyorder")
    protected JdbcTemplate easyorder;
    public ResultMap check(String phonenumber, String password){
        List<Map<String, Object>> num = easyorder.queryForList("select count(phonenumber) as num from prpduser where phonenumber= ?",phonenumber);
        String num1 = num.get(0).get("num").toString();
         int num2 = Integer.parseInt(num1);
         if (num2 == 1){

             List<Map<String, Object>> result = easyorder.queryForList("SELECT IF((select password from prpduser where phonenumber= ?) = ?,true,false) as result",phonenumber,password);
             String result1 = result.get(0).get("result").toString();
             int result2 = Integer.parseInt(result1);
             if (result2==1){
                 List<Map<String, Object>> namearry = easyorder.queryForList("select * from prpduser where phonenumber = ?",phonenumber);
                 String name = namearry.get(0).get("username").toString();
                 return new ResultMap(1, "", true, name,"");
             }else{
                 return new ResultMap(0, "", true, "", "");
             }
         }else{
             return new ResultMap(0, "", true, "", "");
         }
    }
//    public ResultMap alterpassword(String gh,String password){
//        int a = easyorder.update("update user set password = md5(?) where gh= ?",password,gh);
//        if (a==1){
//            return new ResultMap(1, "", true, "", "");
//        }else {
//            return new ResultMap(0, "", true, "", "");
//        }
//    }
}

 