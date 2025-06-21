package dev.kaykyfreitas.finuserservice.finuserservice.application;

public abstract class UseCase<IN, OUT> {
    public abstract OUT execute(IN command);
}
