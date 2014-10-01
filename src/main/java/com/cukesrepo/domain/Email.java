package com.cukesrepo.domain;

public class Email {

    private String to;

    public void setBody(String body) {
        this.body = body;
    }

    private String body;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTo(String to) {
        this.to = to;
    }

    private String subject;

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
