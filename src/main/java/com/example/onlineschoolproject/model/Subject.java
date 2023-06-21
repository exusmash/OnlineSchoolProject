package com.example.onlineschoolproject.model;

public enum Subject {
    PHYSICS("Физика"),
    CHEMISTRY("Химия"),
    BIOLOGY("Биология"),
    LITERATURE("Литература"),
    GEOGRAPHY("География"),
    HISTORY("История"),
    SOCIAL_STUDIES("Обществознание"),
    ENGLISH("Английский язык"),
    INFORMATICS("Информатика");

    private final String subjectTextDisplay;

    Subject(String text) {
        this.subjectTextDisplay = text;
    }

    public String getSubjectTextDisplay() {
        return this.subjectTextDisplay;
    }
}
