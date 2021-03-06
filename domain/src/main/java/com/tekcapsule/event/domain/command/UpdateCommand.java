package com.tekcapsule.event.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.Command;
import lombok.Builder;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UpdateCommand extends Command {
    private String code;
    private String name;
    private String eventDate;
    private String imageUrl;
    private String description;
    private String registrationUrl;
}
