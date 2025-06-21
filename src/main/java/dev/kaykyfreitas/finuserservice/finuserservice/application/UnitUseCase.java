package dev.kaykyfreitas.finuserservice.finuserservice.application;

public abstract class UnitUseCase<IN> {
    public abstract void execute(IN command);
}
