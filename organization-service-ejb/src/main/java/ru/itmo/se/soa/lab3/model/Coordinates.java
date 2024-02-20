package ru.itmo.se.soa.lab3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Coordinates implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private Float x; //Значение поля должно быть больше -631
    private Double y; //Поле не может быть null

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
