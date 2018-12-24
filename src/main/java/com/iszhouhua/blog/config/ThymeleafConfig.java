package com.iszhouhua.blog.config;

import com.iszhouhua.blog.model.Menu;
import com.iszhouhua.blog.service.GlobalService;
import com.iszhouhua.blog.service.MenuService;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import nz.net.ultraq.thymeleaf.decorators.strategies.GroupingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * Thymeleaf配置
 * @author ZhouHua
 * @date 2018/12/9
 */
@Configuration
public class ThymeleafConfig{
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    private GlobalService globalService;

    @Autowired
    private MenuService menuService;

    /**
     * 开启Thymeleaf Layout Dialect支持
     * @return
     */
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect(new GroupingStrategy());
    }

    /**
     * 设置Thymeleaf静态变量
     * 为方便区分，所有静态变量统一以大写命名
     */
    @PostConstruct
    public void setStaticVariables() {
        //全局变量
        Map<String,Object> variables=globalService.findAllGlobal();
        thymeleafViewResolver.setStaticVariables(variables);
        //目录
        List<Menu> menus = menuService.findAllMenu();
        thymeleafViewResolver.addStaticVariable("MENUS",menus);
        menuService.list();
    }
}