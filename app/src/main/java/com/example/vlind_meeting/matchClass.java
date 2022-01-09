package com.example.vlind_meeting;

public class matchClass {
    public String match_nickname;
    public String match_filename;

    public String getNickname() {
        return match_nickname;
    }

    public String getFilename() {
        return match_filename;
    }

    public void setNickname(String nickname) {
        this.match_nickname = nickname;
    }

    public void setFilename(String filename) {
        this.match_filename = filename;
    }

    public matchClass(String nickname, String filename) {
        this.match_nickname = nickname;
        this.match_filename = filename;
    }
}
