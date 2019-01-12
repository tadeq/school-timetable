package com.schooltimetable;

import com.schooltimetable.service.SessionService;

public class Application {
    public static void main(String[] args) {
        SessionService.openSession();
        SessionService.closeSession();
        SessionService.closeSessionFactory();
    }
}
