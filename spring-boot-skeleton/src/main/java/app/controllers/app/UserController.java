package app.controllers.app;

import app.exceptions.AuthenticationException;
import app.exceptions.BusinessException;
import app.forms.UserForm;
import app.models.User;
import app.responses.PageResponse;
import app.responses.Response;
import app.services.UserService;
import app.utils.AuthUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController("userController")
@RequestMapping("/rest/app/users")
@Api(tags = "用户管理")
@Validated
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

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

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "更新当前用户信息", notes = "")
    public Response update(@RequestBody @Validated(UserForm.UPDATE.class) UserForm userForm) throws BusinessException, AuthenticationException {
        userService.update(AuthUtil.currentUser().getId(), userForm);

        return new Response();
    }

}
