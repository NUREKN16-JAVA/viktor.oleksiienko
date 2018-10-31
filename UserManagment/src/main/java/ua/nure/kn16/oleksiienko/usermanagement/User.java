package ua.nure.kn16.oleksiienko.usermanagement;

import java.time.LocalDate;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    private static Long newId = 0L;

    public User() {
        this.setId();
    }

    public User(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.setId();
    }

    public Long getId() {
        return id;
    }

    private void setId() {
        if (this.id == null) {
            this.id = ++newId;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != "" || firstName != null) {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != "" || lastName != null) {
            this.lastName = lastName;
        }
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth != null) {
            this.dateOfBirth = dateOfBirth;
        }
    }

    public String getFullName() {
        return lastName + ", " + firstName;
    }

    public int getAge() {
        LocalDate currentDate = LocalDate.now().withDayOfMonth(20);

        int age = currentDate.getYear() - dateOfBirth.getYear();
        /*if (currentDate.getDayOfYear() < dateOfBirth.getDayOfYear()) {
            age--;
        }*/
        if (currentDate.getMonthValue() < dateOfBirth.getMonthValue() ||
                (currentDate.getMonthValue() == dateOfBirth.getMonthValue() &&
                        currentDate.getDayOfMonth() < dateOfBirth.getDayOfMonth())) {
            age--;
        }
        return age;
    }
}