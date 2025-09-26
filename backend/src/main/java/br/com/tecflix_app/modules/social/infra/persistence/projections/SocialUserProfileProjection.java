package br.com.tecflix_app.modules.social.infra.persistence.projections;

public interface SocialUserProfileProjection {
  SocialNameProjection getSocialName();

  String getUrl();

  interface SocialNameProjection {
    String getName();
  }
}
