package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员管理业务层管理
 * @author wangxin
 * @version 1.0
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    @Override
    public void add(Member dbMember) {
        memberDao.add(dbMember);
    }

    /**
     * 根据年月查询会员数
     * @param months
     * @return
     */
    @Override
    public List<Integer> findMemberCountBeforeDate(List<String> months) {
        List<Integer> memberCount = new ArrayList<>();
        //遍历年月 2018-12          select count(id) from t_member where regTime <= '2018-12-31'
        for (String month : months) {
            String newYearMonthDay = month + "-31";
            Integer monthCount = memberDao.findMemberCountBeforeDate(newYearMonthDay);
            memberCount.add(monthCount);
        }
        return memberCount;
    }

    //统计会员年龄
    @Override
    public Map<String, Object> getMemberByAge() {
        Map<String,Object> rsMap = new HashMap<>();//业务层返回结果
        List<String> memberNames = new ArrayList<>();//会员年龄集合
        List<Map> memberCount = memberDao.getMemberByAge();//会员年龄对应预约数量
        if(memberCount!=null && memberCount.size()>0){
            for (Map map : memberCount) {
                String name = (String)map.get("name");//会员年龄
                memberNames.add(name);//将会员年龄数量中会员年龄分段取出放到setmealNames
            }
        }
        //会员年龄名称
        rsMap.put("memberNames",memberNames);
        //会员年龄对应的数量
        rsMap.put("memberCount",memberCount);
        return rsMap;
    }

    //统计会员性别
    @Override
    public Map<String, Object> getMemberByGender() {
        Map<String,Object> rsMap = new HashMap<>();//业务层返回结果
        List<String> memberNames = new ArrayList<>();//会员性别集合
        List<Map> memberCount = memberDao.getMemberByGender();//会员性别对应预约数量
        if(memberCount!=null && memberCount.size()>0){
            for (Map map : memberCount) {
                String name = (String)map.get("name");//会员性别
                memberNames.add(name);//将会员性别数量中会员性别名称取出放到setmealNames
            }
        }
        //会员性别名称
        rsMap.put("memberNames",memberNames);
        //会员性别对应的数量
        rsMap.put("memberCount",memberCount);
        return rsMap;
    }

}
