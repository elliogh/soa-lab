package ru.itmo.se.soa.lab3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates; //Поле не может быть null
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "creation_date")
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @Column(name = "annual_turnover")
    private Float annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    @Column(name = "full_name")
    private String fullName; //Значение этого поля должно быть уникальным, Поле может быть null
    @Column(name = "employees_count")
    private Integer employeesCount; //Значение поля должно быть больше 0
    @Enumerated(EnumType.STRING)
    private OrganizationType type; //Поле может быть null
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postal_address_id")
    private Address postalAddress; //Поле может быть null

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Float getAnnualTurnover() {
        return annualTurnover;
    }

    public void setAnnualTurnover(Float annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(Integer employeesCount) {
        this.employeesCount = employeesCount;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }
}
