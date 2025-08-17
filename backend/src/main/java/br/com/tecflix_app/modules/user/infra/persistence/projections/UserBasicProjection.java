package br.com.tecflix_app.modules.user.infra.persistence.projections;

import java.util.UUID;

public interface UserBasicProjection {
  UUID getId();

  String getName();
}
