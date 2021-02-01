package edu.university.program.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class WorkDto {
    private long id;

    @NotBlank(message = "The 'position' cannot be empty")
    private String position;

    @NotBlank(message = "The 'company' cannot be empty")
    private String company;

    @NotNull
    private String startWork;

    private String endWork;

    @NotNull
    private String statusWork;

    @NotNull
    private long graduatedId;

    public WorkDto() {
    }

    public WorkDto(long id, String position, String company, String startWork,
                   String endWork, String statusWork, long graduatedId) {
        this.id = id;
        this.position = position;
        this.company = company;
        this.startWork = startWork;
        this.endWork = endWork;
        this.statusWork = statusWork;
        this.graduatedId = graduatedId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStartWork() {
        return startWork;
    }

    public void setStartWork(String startWork) {
        this.startWork = startWork;
    }

    public String getEndWork() {
        return endWork;
    }

    public void setEndWork(String endWork) {
        this.endWork = endWork;
    }

    public String getStatusWork() {
        return statusWork;
    }

    public void setStatusWork(String statusWork) {
        this.statusWork = statusWork;
    }

    public long getGraduatedId() {
        return graduatedId;
    }

    public void setGraduatedId(long graduatedId) {
        this.graduatedId = graduatedId;
    }
}
