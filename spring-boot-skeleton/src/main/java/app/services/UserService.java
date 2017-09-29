package app.services;

import app.enums.Role;
import app.exceptions.AppException;
import app.exceptions.BusinessException;
import app.forms.UserForm;
import app.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> getList(String username, Pageable pageable);

    User create(UserForm form, Role role);

    User create(UserForm form);

    void disable(Integer userId) throws AppException;

    void update(Integer userId, UserForm form) throws BusinessException;

}