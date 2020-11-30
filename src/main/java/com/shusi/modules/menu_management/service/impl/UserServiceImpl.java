package com.shusi.modules.menu_management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shusi.modules.menu_management.base.BadRequestException;
import com.shusi.modules.menu_management.base.NotExistException;
import com.shusi.modules.menu_management.dao.UserDao;
import com.shusi.modules.menu_management.domain.TestUser;
import com.shusi.modules.menu_management.domain.dto.UserAddDto;
import com.shusi.modules.menu_management.domain.dto.UserQueryDto;
import com.shusi.modules.menu_management.service.UserService;
import com.shusi.modules.menu_management.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linqb
 * @since 2020-11-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, TestUser> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TestUser addUser(UserAddDto userAddDto) {
        TestUser testUser = BeanUtil.copyProperties(userAddDto, TestUser.class);
        //新增
        boolean flag = this.save(testUser);
        if (flag) {
            return testUser;
        }
        throw new BadRequestException("新增失败");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeUser(String userId) {
        boolean flag = removeById(userId);
        if (!flag) {
            throw new BadRequestException("删除失败");
        }
    }

    @Override
    public TestUser updateUser(TestUser testUser) {
        if (DataUtil.isNull(testUser.getId())) {
            throw new BadRequestException("没有用户ID,无法更新");
        }
        //获取用户
        TestUser byId = getById(testUser.getId());
        //判断用户是否存在
        if (byId == null) {
            throw new NotExistException(testUser.getId(), "没有该用户,无法更新");
        }
        //更新
        boolean flag = updateById(testUser);
        if (flag) {
            return testUser;
        }

        throw new BadRequestException("更新失败");
    }

    @Override
    public List<TestUser> getUser(UserQueryDto userQueryDto) {
        QueryWrapper<TestUser> wrapper = new QueryWrapper<>();
        //判断是否有查询条件,有则带上查询条件
        if (!DataUtil.isNull(userQueryDto)) {
            if (!DataUtil.isNull(userQueryDto.getUsername())) {
                wrapper.eq("username", userQueryDto.getUsername());
            }
            if (!DataUtil.isNull(userQueryDto.getSex())) {
                Integer sex = Integer.valueOf(userQueryDto.getSex());
                wrapper.eq("sex", sex);
            }
            List<LocalDate> employmentTime = userQueryDto.getEmploymentTime();
            if (!DataUtil.isNull(employmentTime)) {
                LocalDate start = employmentTime.get(0);
                LocalDate end = employmentTime.get(1);
                wrapper.between("emplymentDate", start, end);
            }
        }
        //查询并返回
        return list(wrapper);
    }

    @Override
    public Page<TestUser> listPage(Page<TestUser> page) {
        IPage<TestUser> iPage = this.baseMapper.selectPage(page, null);
        List<TestUser> list = iPage.getRecords();
        page.setTotal(iPage.getTotal());
        page.setRecords(list);
        return page;
    }
}
