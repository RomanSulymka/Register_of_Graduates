package edu.university.program.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
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

    @OneToMany(mappedBy = "graduated")
    private List<Work> works;

    public Graduates() {
    }

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == 0) return null;

        //взяти картинку без підпапки
        //return "/images/" + photos;

        return "/images/" + id + "/" + photos;
    }
}
