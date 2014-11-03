package com.cukesrepo.page;

import java.io.IOException;

import com.cukesrepo.service.login.LoginService;
import org.apache.commons.lang.Validate;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

import static org.rendersnake.HtmlAttributesFactory.class_;
import static org.rendersnake.HtmlAttributesFactory.type;


/**
 * Created by maduraisamy on 12/20/13.
 */
public class LoginPage extends HeaderFooter implements Renderable
{

    public LoginService _loginService;
    private static final String ERROR_LOGIN_PASSWORD = "error.LoginForm.password";
    private static final String ERROR_LOGIN_USERNAME = "error.LoginForm.username";
    static final String ID_USERNAME = "username";
    static final String ID_PASSWORD = "password";

    static final String VAR_USERNAME = "username";
    static final String VAR_PASSWORD = "password";

    public String username;
    public String password;


    public LoginPage(LoginService loginService)
    {

        Validate.notNull(loginService, "loginService cannot be null");

        _loginService = loginService;

    }

    @Override
    public void renderOn(HtmlCanvas html) throws IOException
    {

        addScriptsAndStyleSheets(html);

        renderHeader(html);

        html.html()
                .body();

        html.br();
        html.br();
        html.div(class_("loginpage-div-title"));


        html.input(type("text").class_("login-input-box username").add("placeholder", "Username")).br().br();
        html.input(type("password").class_("login-input-box password").add("placeholder", "Password")).br().br();
        html.input(type("submit").class_("cukes-button").id("login-button").style("float: center;")
                           .value("Login")).br().br();

        html.a(class_("sign-up-link").id("no-decoration").href("/login/sign-up")).content("Sign up now");

//        html._h2();
        html._div();


        html._body()
                ._html();
    }
}


