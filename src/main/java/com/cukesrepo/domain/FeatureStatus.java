package com.cukesrepo.domain;


public enum FeatureStatus {

    NEED_REVIEW("Need Review"),

    UNDER_REVIEW("Under Review"),

    APPROVED("Approved");


    private String _status;

    private FeatureStatus(String status) {
        _status = status;
    }

    public String get() {
        return _status;
    }

}
