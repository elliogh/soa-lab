package ru.itmo.se.soa.lab3.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@JacksonXmlRootElement(localName = "error")
public class BadResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;
    private String message;
    private final String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
}
