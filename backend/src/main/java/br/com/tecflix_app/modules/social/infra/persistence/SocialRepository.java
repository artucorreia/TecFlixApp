package br.com.tecflix_app.modules.social.infra.persistence;

import br.com.tecflix_app.modules.social.infra.persistence.projections.AuthenticatedUserSocialProjection;
import br.com.tecflix_app.modules.social.infra.persistence.projections.SocialUserProfileProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SocialRepository extends JpaRepository<SocialEntity, Long> {
  List<SocialUserProfileProjection> findSocialUserProfileProjectionByUserId(UUID userId);

  List<AuthenticatedUserSocialProjection> findSocialAuthenticatedUserProjectionByUserIdAndDeletedFalse(
      UUID userId);
}
