package com.example.linkedhuceng.security;

public enum UserAuthority {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    POST_READ("post:read"),
    POST_WRITE("post:write");

    private String authority;

    UserAuthority(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
