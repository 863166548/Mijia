package com.xingxing.service;

import com.xingxing.bean.SysRoleAccess;
import com.xingxing.bean.SysUserAccess;
import com.xingxing.bean.dto.AccessDto;
import com.xingxing.bean.dto.Information;
import com.xingxing.dao.RbacMapper;
import com.xingxing.dao.SysRoleAccessMapper;
import com.xingxing.dao.SysUserAccessMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RbacService {

    private static final Logger log = LoggerFactory.getLogger(RbacService.class);
    @Autowired
    private RbacMapper rbacMapper;
    @Autowired
    private SysUserAccessMapper sysUserAccessMapper;
    @Autowired
    private SysRoleAccessMapper sysRoleAccessMapper;

    public List<AccessDto> getUserAndAccess(Integer id) {
        return rbacMapper.getUserAndAccess(id);
    }


    public int setUserAccess(Integer userId, ArrayList<Integer> access) {
        if (userId == null) {
            return 0;
        }
        sysUserAccessMapper.deleteByUserId(userId);
        ArrayList<SysUserAccess> sysUserAccesses = new ArrayList<>();
        for (Integer integer :
                access) {
            SysUserAccess sUAccess = new SysUserAccess();
            sUAccess.setUserId(userId);
            sUAccess.setAccessId(integer);
            sysUserAccesses.add(sUAccess);
        }

        log.info(sysUserAccesses + "");
        sysUserAccessMapper.insertForeach(sysUserAccesses);
        return 1;

    }


    public int updateUserAccess(Information information) {
        int i = information.getId();
        log.info(i + "传入传入传入传入传入");
        int s = sysUserAccessMapper.selectUserAccess(i);
        int x = sysUserAccessMapper.selectuseraccess(i);
        log.info(s + "传出传出传出传出传出");
        log.info(x + "~~~~~~~~~~~~~~~");
        if (s == 0 && x == 0) {
            return 0;
        }
        int t = information.getRoleId();
        sysRoleAccessMapper.deleteByRoleId(t);
        ArrayList<SysRoleAccess> sysRoleAccess = new ArrayList<>();
        for (Integer integer :
                information.getaccess()) {
            SysRoleAccess sua = new SysRoleAccess();
            sua.setRoleId(t);
            sua.setAccessId(integer);
            sysRoleAccess.add(sua);
        }
        sysRoleAccessMapper.insertForeach(sysRoleAccess);
        return 1;
    }

}
