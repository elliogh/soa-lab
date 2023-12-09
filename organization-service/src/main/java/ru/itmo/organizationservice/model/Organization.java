package ru.itmo.organizationservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "organization")
public class Organization {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates; //Поле не может быть null
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    private String fullName; //Значение этого поля должно быть уникальным, Поле может быть null
    private Integer employeesCount; //Значение поля должно быть больше 0
    @Enumerated(EnumType.STRING)
    private OrganizationType type; //Поле может быть null
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "postal_address_id")
    private Address postalAddress; //Поле может быть null
}
