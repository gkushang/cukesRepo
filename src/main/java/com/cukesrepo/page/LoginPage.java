package com.cukesrepo.page;

import java.io.IOException;

import com.cukesrepo.service.login.LoginService;
import org.apache.commons.lang.Validate;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

import static org.rendersnake.HtmlAttributesFactory.class_;
import static org.rendersnake.HtmlAttributesFactory.type;


public class LoginPage extends HeaderFooter implements Renderable
{

    public LoginService _loginService;

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

        html._div();
        html._body()
                ._html();
    }
}


