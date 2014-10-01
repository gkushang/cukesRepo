package com.cukesrepo.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import com.cukesrepo.exceptions.UserNotFoundException;
import com.cukesrepo.page.LoginPage;
import com.cukesrepo.page.SignUpPage;
import com.cukesrepo.service.login.LoginService;
import org.rendersnake.HtmlCanvas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by maduraisamy on 12/20/13.
 */
@Controller
public class LoginPageController
{

    private final LoginService _loginService;

    @Autowired
    public LoginPageController
            (
                    LoginService loginService
            )
    {

        _loginService = loginService;
    }

    @RequestMapping(value = {"/login/"})
    @ResponseBody
    public void renderLoginPage
            (
                    HtmlCanvas html
            ) throws IOException
    {

        html.render(new LoginPage(_loginService));

    }

    @RequestMapping(value = {"/login/verify/{username}/{password}"})
    @ResponseBody
    protected void persistProject(@PathVariable String username, @PathVariable String password) throws UserNotFoundException
    {

        _loginService.login(username, password);

    }

    @RequestMapping(value = {"/login/sign-up"})
    @ResponseBody
    public void renderAddProjectsPage(HtmlCanvas html) throws IOException
    {

        html.render(new SignUpPage(_loginService));

    }

    @RequestMapping(value = {"/login/sign-up/persist"})
    @ResponseBody
    protected void persistUser(HttpServletRequest request) throws IOException
    {

        _loginService.addUser(request.getParameterMap());

    }

//
//    @RequestMapping({"/login/"})
//    public String getLoginPage(@RequestParam(value = "error", required = false) boolean error,
//                               @RequestParam(value = "sessiontimedout", required = false) boolean sessiontimedout
//    )
//    {
////Needs an implementation here
//
//        // Add an error message to the model if login is unsuccessful
//        // The 'error' parameter is set to true based on the when the authentication has failed.
//        // We declared this under the defaultFailureUrl attribute inside the spring-security.xml
//
////        if (error == true) {
////            model.put("error", "You have entered an invalid username or password!");
////        } else if(sessiontimedout=true)
////        {
////            model.put("error", "Your session has been timed out!");
////        }
////        else {
////            model.put("error", "");
////        }
//
//        return "login";
//    }


}
