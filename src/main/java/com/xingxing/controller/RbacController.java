package com.xingxing.controller;

import com.xingxing.bean.SysAccess;
import com.xingxing.bean.SysUser;
import com.xingxing.bean.dto.Information;
import com.xingxing.bean.dto.Result;
import com.xingxing.bean.dto.UserPower;
import com.xingxing.dao.RbacMapper;
import com.xingxing.service.PowerService;
import com.xingxing.service.RbacService;
import com.xingxing.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rbac")
public class RbacController {

    private static final Logger log = LoggerFactory.getLogger(RbacController.class);
    @Autowired
    private RbacService rbacService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private PowerService powerService;

    /**
     * 通过userId查询该用户所有的权限，并返回List<AccessDTO>
     * @param id
     * @return
     */

    @RequestMapping("/getUserAccById")
    public Result getUserAndAccess(@RequestParam("id") Integer id){
        try {
            Result success = Result.getSuccess();
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("access",rbacService.getUserAndAccess(id));
            stringObjectHashMap.put("msg","victory");
            success.setData(stringObjectHashMap);
            return success;
        } catch (Exception exception) {
            exception.printStackTrace();
            Result failt =Result.getFaild();
            failt.setMsg("default");
            return failt;
        }
    }




//    @RequestMapping("/getRoleAccess")
//    public Result getRoleAccess(@RequestParam("userId") Integer userId){
//        Result success = Result.getSuccess();
//        SysUser sysUser = new SysUser();
//        HashMap<String, Object> userHashMap = new HashMap<>();
//
//    }

//    @RequestMapping("/selectSur")
//    public Result selectSur(@RequestParam("id") Integer id) {
//        try {
//            Result success = Result.getSuccess();
//            HashMap<String, Object> hashMap = new HashMap<>();
//            hashMap.put("user",sysUserService.getUserById(id));
//            List<UserPower> userAndPowerByXml = powerService.getUserAndPowerByXml(id);
//
//            HashMap<Integer, HashMap> roleHash = new HashMap<>();
//            for (UserPower userPower : userAndPowerByXml) {
//                Integer roleId = userPower.getRoleId();
//                if (roleHash.get(roleId) == null){
//                    HashMap<String, Object> role = new HashMap<>();
//                    role.put("role_id",roleId);
//                    role.put("role_name",userPower.getrName());
//                    role.put("accesses",new ArrayList<SysAccess>());
//                    roleHash.put(roleId,role);
//                }
//            }
//
//            for (UserPower userPower : userAndPowerByXml) {
//                Integer roleId = userPower.getRoleId();
//                ArrayList<SysAccess> accsses = (ArrayList<SysAccess>)roleHash.get(roleId).get("accesses");
//                SysAccess sysAccess = new SysAccess();
//                sysAccess.setId(userPower.getAccessId());
//                sysAccess.setAccessName(userPower.getAccessName());
//                accsses.add(sysAccess);
//            }
//
//            ArrayList<Object> objects = new ArrayList<>();
//            Set<Map.Entry<Integer, HashMap>> entries = roleHash.entrySet();
//            for (Map.Entry<Integer, HashMap> entry : entries) {
//                objects.add(entry.getValue());
//            }
//
//            hashMap.put("role", objects);
//            success.setMsg("访问成功，请查收以下资料");
//            success.setData(hashMap);
//            return success;
//        } catch (Exception e) {
//            e.printStackTrace();
//            Result faild = Result.getFaild();
//            faild.setMsg("错误~");
//            return faild;
//        }
//    }





    /**
     * 通过userId ， 和accessId的集合 对 sys_user_access（用户权限中间表）进行操作
     */

    @RequestMapping("/setAccess")
    public Result getUserAccess(@RequestBody HashMap<String,Object> parm){
        try {
            Result success =Result.getSuccess();
            Integer userId = (Integer)parm.get("userId");
            ArrayList<Integer> access= (ArrayList<Integer>)parm.get("access");
            rbacService.setUserAccess(userId,access);
            success.setMsg("成功");
            return success;
        } catch (Exception exception) {
            exception.printStackTrace();

            Result failt =Result.getFaild();
            failt.setMsg("失败");
            return failt;
        }
    }

//    @RequestMapping(value = "/updateAccess", method = RequestMethod.POST)
//    public Result updateAccess(@RequestParam(value = "id")Integer id,
//                                @RequestParam(value = "userId")Integer userId,
//                                @RequestParam(value = "access")ArrayList<Integer> access){
//        Result success =Result.getSuccess();
//        rbacService

//    }


    @RequestMapping(value = "/updateAccess", method = RequestMethod.POST)
    public Result updateAccess(@RequestBody Information information){
        try {
            log.info(information.toString());
            Result success = Result.getSuccess();
            int f = rbacService.updateUserAccess(information);
            if (f == 1){
            success.setMsg("成功");
            return success;}else {
                Result getresult = Result.getResult();
                getresult.setMsg("您没有此权限");
                getresult.setState(500);
                return getresult;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            Result failt =Result.getFaild();
            failt.setMsg("失败");
            return failt;
        }


    }






}
