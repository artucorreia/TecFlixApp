package br.com.tecflix_app.projection;

import br.com.tecflix_app.model.enums.SocialName;

public interface SocialProjection {
    SocialName getName();

    String getUrl();
}
