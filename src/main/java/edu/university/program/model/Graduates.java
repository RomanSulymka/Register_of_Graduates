package edu.university.program.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name= "graduates")
public class Graduates{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "position", nullable = false )
    private String position;

    @Column(name = "graduation_year", nullable = false)
    private int graduationYear;

    public Graduates() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
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
                ", graduationYear=" + graduationYear +
                '}';
    }
}
