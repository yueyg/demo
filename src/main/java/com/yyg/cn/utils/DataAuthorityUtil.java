package com.yyg.cn.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyg.cn.common.entity.DataAuthority;
import com.yyg.cn.common.service.IDataAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class DataAuthorityUtil {
    @Autowired
    private static IDataAuthorityService dataAuthorityService;

    /**
     * 根据资源类型、操作类型、用户id获取资源IDS集合
     * @param resourceType
     * @param optType
     * @param userId
     * @return
     */
    public List<String> getResourceIdsByUserId(Integer resourceType,Integer optType,String userId) {
        return getListByResourceTypeAndOptType(resourceType,optType).stream().filter(x -> x.getUserId().equals(userId)).map( x -> x.getResourceId()).collect(Collectors.toList());
    }
    /**
     * 根据资源类型、操作类型、用户id获取资源IDS字符串
     * @param resourceType
     * @param optType
     * @param userId
     * @return
     */
    public String getResourceIdsStrByUserId(Integer resourceType,Integer optType,String userId) {
        return getListByResourceTypeAndOptType(resourceType,optType).stream().filter(x -> x.getUserId().equals(userId)).map( x -> x.getResourceId()).collect(Collectors.joining());
    }
    /**
     * 根据资源类型、操作类型、组织id集合获取资源IDS集合
     * @param resourceType
     * @param optType
     * @param orgIds
     * @return
     */
    public List<String> getResourceIdsByOrgIds(Integer resourceType,Integer optType,List<String> orgIds) {
        return getListByResourceTypeAndOptType(resourceType,optType).stream().filter(x -> orgIds.contains(x.getRoleId())).map( x -> x.getResourceId()).collect(Collectors.toList());
    }
    /**
     * 根据资源类型、操作类型、组织id集合获取资源IDS字符串
     * @param resourceType
     * @param optType
     * @param orgIds
     * @return
     */
    public String getResourceIdsStrByOrgIds(Integer resourceType,Integer optType,List<String> orgIds) {
        return getListByResourceTypeAndOptType(resourceType,optType).stream().filter(x -> orgIds.contains(x.getRoleId())).map( x -> x.getResourceId()).collect(Collectors.joining());
    }
    /**
     * 根据资源类型、操作类型、角色id集合获取资源IDS集合
     * @param resourceType
     * @param optType
     * @param roleIds
     * @return
     */
    public List<String> getResourceIdsByRoleIds(Integer resourceType,Integer optType,List<String> roleIds) {
        return getListByResourceTypeAndOptType(resourceType,optType).stream().filter(x -> roleIds.contains(x.getRoleId())).map( x -> x.getResourceId()).collect(Collectors.toList());
    }
    /**
     * 根据资源类型、操作类型、角色id集合获取资源IDS字符串
     * @param resourceType
     * @param optType
     * @param roleIds
     * @return
     */
    public String getResourceIdsStrByRoleIds(Integer resourceType,Integer optType,List<String> roleIds) {
        return getListByResourceTypeAndOptType(resourceType,optType).stream().filter(x -> roleIds.contains(x.getRoleId())).map( x -> x.getResourceId()).collect(Collectors.joining());
    }

    /**
     * 根据资源类型、操作类型获取集合
     * @param resourceType
     * @param optType
     * @return
     */
    public static List<DataAuthority> getListByResourceTypeAndOptType(Integer resourceType,Integer optType) {
        return dataAuthorityService.list(new QueryWrapper<DataAuthority>().eq("resource_type",resourceType).eq("opt_type",optType));
    }
}
