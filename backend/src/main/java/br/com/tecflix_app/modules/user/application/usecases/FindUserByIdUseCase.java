package br.com.tecflix_app.modules.user.application.usecases;

import br.com.tecflix_app.modules.user.application.domain.entity.User;

import java.util.UUID;

public interface FindUserByIdUseCase {
  User execute(UUID id);
}
