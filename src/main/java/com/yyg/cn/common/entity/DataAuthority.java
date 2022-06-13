package com.yyg.cn.common.entity;

import com.yyg.cn.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据权限
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataAuthority extends BaseEntity {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 组织ID
     */
    private String orgId;
    /**
     * 组织名
     */
    private String orgName;
    /**
     * 角色ID
     */
    private String roleId;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 资源ID
     */
    private String resourceId;
    /**
     * 资源类型
     */
    private Integer resourceType;
    /**
     * 操作类型（0：增，1：删，2：改，3：查）
     */
    private Integer optType;


}
