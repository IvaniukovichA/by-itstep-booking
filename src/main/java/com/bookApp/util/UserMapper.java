package com.bookApp.util;

import com.bookApp.dto.bean.CreateUserBean;
import com.bookApp.dto.bean.UserBean;
import com.bookApp.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserMapper {
    public static User userFromUserBean(UserBean userBean) {
        User user = new User();
        if (Objects.nonNull(userBean)) {
            user.setName(userBean.getName());
            user.setRooms(userBean.getRooms());
            user.setLastName(userBean.getLastName());
            user.setId(userBean.getId());
            user.setRole(userBean.getRole());
            return user;
        }
        return null;

    }

    public static User userFromUserBean(CreateUserBean userBean) {
        User user = null;
        if (Objects.nonNull(userBean)) {
            user = new User();
            user.setName(userBean.getName());
            user.setLastName(userBean.getLastName());
            user.setRole(userBean.getRole());
        }
        return user;
    }

    public static UserBean userBeanFromUser(User user) {
        UserBean userBean = new UserBean();
        if (Objects.nonNull(user)) {
            userBean.setName(user.getName());
            userBean.setRooms(user.getRooms());
            userBean.setLastName(user.getLastName());
            userBean.setId(user.getId());
            userBean.setRole(user.getRole());
            return userBean;
        }
        return null;
    }

    public static CreateUserBean createUserBeanFromUser(User user) {
        CreateUserBean userBean = new CreateUserBean();
        if (Objects.nonNull(user)) {
            userBean.setName(user.getName());
            userBean.setLastName(user.getLastName());
            userBean.setRole(user.getRole());
            return userBean;
        }
        return null;
    }


    public static List<UserBean> userBeanListFomUserList(List<User> users) {
        List<UserBean> userBeanList = new ArrayList<>();
        if (!users.isEmpty()) {
            for (User user : users
            ) {
                userBeanList.add(userBeanFromUser(user));
            }
        }
        return userBeanList;
    }
}
