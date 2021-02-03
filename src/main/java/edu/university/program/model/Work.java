package edu.university.program.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode
@Getter
@Setter
@ToString
@Entity
@Table(name = "works")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "The 'position' cannot be empty")
    @Column(name = "position", nullable = true)
    private String position;

    @NotBlank(message = "The 'company' cannot be empty")
    @Column(name = "company")
    private String company;

    @Column(name = "start_work", nullable = true)
    private String startWork;

    @Value("present")
    @Column(name = "end_work", nullable = true)
    private String endWork;

    @ManyToOne
    @JoinColumn(name = "graduates_id")
    private Graduates graduated;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus;

    public Work() {
    }
}
