package com.tekcapsule.event.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class CreateInput {
    private String name;
    private String eventDate;
    private String imageUrl;
    private String description;
    private String registrationUrl;
}