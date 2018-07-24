package com.mason.blog.dao;

import com.mason.blog.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component(value = "MenuMapper")
public interface MenuMapper {
    /**
     * 通过用户Id得到一级菜单List
     *
     * @param ids
     * @return
     */
    public List<Menu> getParentMenuListById(@Param("ids") String[] ids);

    public List<Menu> getMenuListById(@Param("ids") String[] ids);

    /**
     * 获取menus列表
     *
     * @param pageSize
     * @param start
     * @param menuId
     * @return
     */
    public List<Menu> menusList(@Param("pageSize") int pageSize, @Param("start") int start,
                                      @Param("menuId") String menuId);

    /**
     * 获取menus列表的总量
     *
     * @param pageSize
     * @param start
     * @param menuId
     * @return
     */
    public Integer menusSize(@Param("pageSize") int pageSize, @Param("start") int start,
                             @Param("menuId") String menuId);

    /**
     * 新建菜单信息
     *
     * @param menu
     */
    public void insertMenu(@Param("menu") Menu menu);

    /**
     * 修改菜单信息
     *
     * @param menu
     */
    public void updateMenu(@Param("menu") Menu menu);

    /**
     * 删除菜单信息
     *
     * @param groupId
     */
    public void deleteMenus(@Param("groupId") List<String> groupId);

    /**
     * 通过parentId得到menus列表
     *
     * @param parentId
     * @return
     */
    public List<Menu> menusByParentId(@Param("parentId") int parentId);

    /**
     * 获取二级菜单
     * @return
     */
    public List<Menu> getSubmenus();
}