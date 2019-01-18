package com.schooltimetable.view;

import java.util.Scanner;

public class MainView {
    private boolean running;

    private Scanner scanner;

    public MainView() {
        this.running = true;
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        System.out.println("\n\n\n\n\nSchool timetable\n");
        System.out.println("s - classes timetable");
        System.out.println("t - teachers");
        System.out.println("c - classrooms");
        System.out.println("q - quit");
        String line;
        char c;
        do {
            line = scanner.nextLine();
        } while (line.length() == 0);
        c = line.charAt(0);
        switch (c) {
            case 's':
            case 'S':
                System.out.println("Classes");
                SchoolClassView schoolClassView = new SchoolClassView();
                schoolClassView.showClasses();
                System.out.println("c - choose class");
                System.out.println("a - add class");
                System.out.println("other - back to main menu");
                try {
                    c = scanner.nextLine().charAt(0);
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
                switch (c) {
                    case 'c':
                    case 'C':
                        schoolClassView.showClassTimetable();
                        break;
                    case 'a':
                    case 'A':
                        schoolClassView.addClass();
                        break;
                    default:
                        break;
                }
                break;
            case 't':
            case 'T':
                TeacherView teacherView = new TeacherView();
                System.out.println("Teachers");
                teacherView.showTeachers();
                System.out.println("t - teacher details");
                System.out.println("other - back to main menu");
                try {
                    c = scanner.nextLine().charAt(0);
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
                switch (c) {
                    case 't':
                    case 'T':
                        teacherView.showTeacherDetails();
                        break;
                    default:
                        break;
                }
                break;
            case 'c':
            case 'C':
                break;
            case 'q':
            case 'Q':
                running = false;
                break;
        }

    }

    public boolean isRunning() {
        return running;
    }
}
