package com.schooltimetable;

import com.schooltimetable.service.SessionService;
import com.schooltimetable.view.MainView;

public class Application {
    public static void main(String[] args) {
        SessionService.openSession();
        MainView mainView = new MainView();
        while (mainView.isRunning())
            mainView.displayMainMenu();
        SessionService.closeSession();
        SessionService.closeSessionFactory();
    }
}
