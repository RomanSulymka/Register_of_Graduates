package edu.university.program.model;


import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
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

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "position", nullable = false )
    private String position;

    @Column(name = "graduation_year", nullable = true)
    private String graduationYear;

    @Column(nullable = true, length = 64)
    private String photos;


    public Graduates(String firstName, String lastName, String company, String position, String graduationYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.position = position;
        this.graduationYear = graduationYear;
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

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
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
        return graduationYear == graduates.graduationYear && Objects.equals(id, graduates.id)
                && Objects.equals(firstName, graduates.firstName)
                && Objects.equals(lastName, graduates.lastName)
                && Objects.equals(company, graduates.company)
                && Objects.equals(position, graduates.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, company, position, graduationYear);
    }

    @Override
    public String toString() {
        return "Graduates{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", graduationYear='" + graduationYear + '\'' +
                ", photos='" + photos + '\'' +
                '}';
    }

}
