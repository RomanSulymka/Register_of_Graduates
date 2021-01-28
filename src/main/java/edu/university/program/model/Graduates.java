package edu.university.program.model;


import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Table(name= "graduates")
public class Graduates{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Value(" ")
    @Column(name = "middle_name", nullable = true)
    private String middleName;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "position", nullable = false )
    private String position;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "gender", nullable = true)
    private String gender;

    @Pattern(regexp = "(19[789]\\d|20[01]\\d)", message = "1970-2019 years")
    @Column(name = "graduation_year_from_bachelor", nullable = true)
    private String graduationYearFromBachelor;

    @Value(" - ")
    @Pattern(regexp = "(19[789]\\d|20[01]\\d)", message = "1970-2019 years")
    @Column(name = "graduation_year_from_master", nullable = true)
    private String graduationYearFromMaster;

    @Value(" - ")
    @Pattern(regexp = "(19[789]\\d|20[01]\\d)", message = "1970-2019 years")
    @Column(name = "graduation_year_from_specialist", nullable = true)
    private String graduationYearFromOrkSpecialist;

    @Column(nullable = true, length = 64)
    private String photos;

    public Graduates(long id, String firstName, String lastName, String middleName,
                     String company, String position, String email, String gender, String graduationYearFromBachelor,
                     String graduationYearFromMaster, String graduationYearFromOrkSpecialist) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.company = company;
        this.position = position;
        this.email = email;
        this.gender = gender;
        this.graduationYearFromBachelor = graduationYearFromBachelor;
        this.graduationYearFromMaster = graduationYearFromMaster;
        this.graduationYearFromOrkSpecialist = graduationYearFromOrkSpecialist;
    }

    public Graduates() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGraduationYearFromBachelor() {
        return graduationYearFromBachelor;
    }

    public void setGraduationYearFromBachelor(String graduationYearFromBachelor) {
        this.graduationYearFromBachelor = graduationYearFromBachelor;
    }

    public String getGraduationYearFromMaster() {
        return graduationYearFromMaster;
    }

    public void setGraduationYearFromMaster(String graduationYearFromMaster) {
        this.graduationYearFromMaster = graduationYearFromMaster;
    }

    public String getGraduationYearFromOrkSpecialist() {
        return graduationYearFromOrkSpecialist;
    }

    public void setGraduationYearFromOrkSpecialist(String graduationYearFromOrkSpecialist) {
        this.graduationYearFromOrkSpecialist = graduationYearFromOrkSpecialist;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == 0) return null;

        //взяти картинку без підпапки
        //return "/images/" + photos;

        return "/images/" + id + "/" + photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graduates graduates = (Graduates) o;
        return id == graduates.id
                && Objects.equals(firstName, graduates.firstName)
                && Objects.equals(lastName, graduates.lastName) && Objects.equals(middleName, graduates.middleName)
                && Objects.equals(company, graduates.company) && Objects.equals(position, graduates.position)
                && Objects.equals(email, graduates.email) && Objects.equals(gender, graduates.gender)
                && Objects.equals(graduationYearFromBachelor, graduates.graduationYearFromBachelor)
                && Objects.equals(graduationYearFromMaster, graduates.graduationYearFromMaster)
                && Objects.equals(graduationYearFromOrkSpecialist, graduates.graduationYearFromOrkSpecialist)
                && Objects.equals(photos, graduates.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, middleName, company, position, email, gender,
                graduationYearFromBachelor, graduationYearFromMaster, graduationYearFromOrkSpecialist, photos);
    }

    @Override
    public String toString() {
        return "Graduates{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", graduationYearFromBachelor='" + graduationYearFromBachelor + '\'' +
                ", graduationYearFromMaster='" + graduationYearFromMaster + '\'' +
                ", graduationYearFromOrkSpecialist='" + graduationYearFromOrkSpecialist + '\'' +
                '}';
    }
}
