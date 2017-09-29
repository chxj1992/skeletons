package app.controllers.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import app.exceptions.AppException;
import app.annotations.Exist;
import app.exceptions.AuthenticationException;
import app.exceptions.BusinessException;
import app.forms.UserForm;
import app.models.User;
import app.repos.UserRepository;
import app.responses.PageResponse;
import app.responses.Response;
import app.services.UserService;
import app.utils.AuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController("adminUserController")
@RequestMapping("/rest/admin/users")
@Api(tags = "后台-用户管理")
@Validated
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserRepository userRepository;

    private UserService userService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取用户列表", notes = "")
    public PageResponse<User> getList(@RequestParam(value = "username", required = false) String username,
                                      @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        Page<User> data = userService.getList(username, pageable);

        return new PageResponse<>(data);
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取当前用户详情", notes = "")
    public Response<User> getCurrentUser() throws BusinessException, AuthenticationException {
        return new Response<>(AuthUtil.currentUser());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取用户详情", notes = "")
    public Response<User> getUserById(@PathVariable("id") Integer userId) throws BusinessException {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new BusinessException("该用户不存在")
        );

        return new Response<>(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "禁用一个用户", notes = "")
    public Response disable(@PathVariable("id")
                           @Exist(model = "User", column = "id", condition = "status=1")
                                   Integer userId) throws AppException {
        userService.disable(userId);

        return new Response();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "更新用户信息", notes = "")
    public Response update(@PathVariable("id") Integer userId,
                           @RequestBody @Validated(UserForm.UPDATE.class) UserForm userForm) throws BusinessException {
        userService.update(userId, userForm);

        return new Response();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "创建用户", notes = "")
    public Response<User> create(@RequestBody @Validated(UserForm.CREATE.class) UserForm form) {

        User user = userService.create(form);

        return new Response<>(user);
    }

}
