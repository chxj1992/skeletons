package app.serviceImpls;

import app.enums.Role;
import app.enums.Status;
import app.exceptions.AppException;
import app.exceptions.BusinessException;
import app.forms.UserForm;
import app.models.User;
import app.repos.UserRepository;
import app.services.UserService;
import app.utils.CryptUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private EntityManager entityManager;
    private UserRepository userRepository;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> getList(String username, Pageable pageable) {

        return userRepository.findAll(new Specification<User>() {

            List<Predicate> list = Lists.newArrayList();

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                list.add(cb.equal(root.get("status"), Status.NORMAL.getValue()));
                list.add(cb.isMember(Role.ROLE_USER, root.get("roles")));

                if (!"".equals(username)) {
                    list.add(cb.like(root.get("username"), "%" + username + "%"));
                }

                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        }, pageable);
    }

    @Override
    @Transactional
    public User create(UserForm form, Role role) {
        User user = new User();
        user.setUsername(form.getUsername());
        user.setStatus(Status.NORMAL);
        user.setPassword(CryptUtil.passwordEncoder().encode(form.getPassword()));
        user.setRoles(ImmutableList.of(role));

        entityManager.persist(user);

        return user;
    }

    @Override
    @Transactional
    public User create(UserForm form) {
        return create(form, Role.ROLE_USER);
    }

    @Override
    @Transactional
    public void disable(Integer userId) throws AppException {
        userRepository.setStatus(userId, Status.DISABLED.getValue());
    }

    @Override
    @Transactional
    public void update(Integer userId, UserForm form) throws BusinessException {
        User user = userRepository.findOne(userId);

        if (user == null) {
            throw new BusinessException("user with id " + userId + " not exists");
        }

        if (form.getPassword() != null) {
            user.setPassword(CryptUtil.passwordEncoder().encode(form.getPassword()));
        }

        if (form.getUsername() != null) {
            user.setUsername(form.getUsername());
        }

        userRepository.save(user);
    }

}

