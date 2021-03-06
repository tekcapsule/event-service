package com.tekcapsule.event.application.function;

import com.tekcapsule.core.domain.EmptyFunctionInput;
import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.Stage;
import com.tekcapsule.event.application.config.AppConfig;
import com.tekcapsule.event.domain.model.Event;
import com.tekcapsule.event.domain.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
@Slf4j
public class GetAllFunction implements Function<Message<EmptyFunctionInput>, Message<List<Event>>> {

    private final EventService eventService;

    private final AppConfig appConfig;

    public GetAllFunction(final EventService eventService, final AppConfig appConfig) {
        this.eventService = eventService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<List<Event>> apply(Message<EmptyFunctionInput> findAllMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        List<Event> events = new ArrayList<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            log.info("Entering get all events Function");
            events = eventService.findAll();
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage(events, responseHeaders);
    }
}