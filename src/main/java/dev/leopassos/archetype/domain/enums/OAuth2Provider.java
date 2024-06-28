package dev.leopassos.archetype.domain.enums;

public enum OAuth2Provider {
    GOOGLE("google"),
    FACEBOOK("facebook"),
    GITHUB("github"),
    LINKEDIN("linkedin");

    private final String provider;

    OAuth2Provider(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }
}
