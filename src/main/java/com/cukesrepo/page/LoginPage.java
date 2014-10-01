package com.cukesrepo.page;

import com.cukesrepo.domain.Project;
import com.cukesrepo.service.login.LoginService;
import com.cukesrepo.service.project.ProjectService;
import org.apache.commons.lang.Validate;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;
import org.rendersnake.internal.ContextMap;


import java.io.IOException;

import static org.rendersnake.HtmlAttributesFactory.*;
import static org.rendersnake.HtmlAttributesFactory.class_;
import static org.rendersnake.HtmlAttributesFactory.href;

/**
 * Created by maduraisamy on 12/20/13.
 */
public class LoginPage extends HeaderFooter implements Renderable {

        public LoginService _loginService;
    private static final String ERROR_LOGIN_PASSWORD = "error.LoginForm.password";
    private static final String ERROR_LOGIN_USERNAME = "error.LoginForm.username";
    static final String ID_USERNAME = "username";
    static final String ID_PASSWORD = "password";

    static final String VAR_USERNAME = "username";
    static final String VAR_PASSWORD = "password";

    public String username;
    public String password;


        public LoginPage(LoginService loginService) {

            Validate.notNull(loginService, "loginService cannot be null");

            _loginService = loginService;

        }

    @Override
    public void renderOn(HtmlCanvas html) throws IOException {

        addScriptsAndStyleSheets(html);

        renderTitle(html);

        html.html()
                .body();

        html.br();
        html.br();
        html.div(class_("loginpage-div-title"));
        html.h2().span(id("login-title")).content("Login").br().br();



        html.input(type("text").class_("login-page username").add("placeholder", "User Name")).br().br();
        html.input(type("password").class_("login-page password").add("placeholder", "Password")).br().br();
        html.a(href("#"));
        html.input(type("button").class_("cukes-button-add-p").id("login").style("float: left;")
                .value("Login"))._a().br().br();
        html.a(href("/login/sign-up"));
        html.input(type("button").class_("cukes-button-add-p").id("signup").style("float: left;")
                .value("SignUp"))._a();

       html._h2();
        html._div();
        html._body()
                ._html();

    }




    }


