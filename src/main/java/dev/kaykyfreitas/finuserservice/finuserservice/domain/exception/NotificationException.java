package dev.kaykyfreitas.finuserservice.finuserservice.domain.exception;

import dev.kaykyfreitas.finuserservice.finuserservice.domain.validation.handler.Notification;

public class NotificationException extends DomainException {

    public NotificationException(final String aMessage, final Notification aNotification) {
        super(aMessage, aNotification.getErrors());
    }

}
