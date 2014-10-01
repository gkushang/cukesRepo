package com.cukesrepo.page;

import com.cukesrepo.service.login.LoginService;
import com.cukesrepo.service.project.ProjectService;
import org.apache.commons.lang.Validate;
import org.rendersnake.HtmlCanvas;
import org.rendersnake.Renderable;

import java.io.IOException;

import static org.rendersnake.HtmlAttributesFactory.*;
import static org.rendersnake.HtmlAttributesFactory.type;

/**
 * Created by maduraisamy on 2/3/14.
 */
public class SignUpPage extends HeaderFooter implements Renderable {

    public LoginService _loginService;

    public SignUpPage(LoginService loginService) {

        Validate.notNull(loginService, "loginService cannot be null");

        _loginService = loginService;

    }

    @Override
    public void renderOn(HtmlCanvas html) throws IOException {

        addScriptsAndStyleSheets(html);

        renderHeader(html);

        html.html()
                .body();

        html.br();
        html.br();
        html.div(class_("signuppage-div-title"));
        html.h2().span(id("signup-title")).content("Sign-Up").br().br();


        html.input(type("text").class_("sign-up name").add("placeholder", "Name")).br().br();
        html.input(type("password").class_("sign-up password").add("placeholder", "Password")).br().br();
        html.input(type("password").class_("sign-up repeat-password").add("placeholder", "Repeat Password")).br().br();
        html.input(type("text").class_("sign-up email").add("placeholder", "Email")).br().br();
        html.input(type("text").class_("sign-up role").add("placeholder", "Role")).br().br();

        html.a(href("#").id("sign-up-navigate"));
        html.input(type("button").class_("cukes-button-add-p").id("sign-up").style("float: left;")
                .value("SignUp"))._a();

        html._h2();
        html._div();


        html._body()
                ._html();

    }
}
