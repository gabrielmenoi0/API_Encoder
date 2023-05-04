package com.urlEncoder.builder.domain;

import java.time.LocalDate;
import java.util.UUID;

public class UrlBuilder {
        private Url url;

        public UrlBuilder() {
            this.url = new Url();
        }

        public UrlBuilder withUrl(String url) {
            this.url.setUrl(url);
            return this;
        }

        public UrlBuilder withUserId(UUID userId) {
            this.url.setIdUser(userId.toString());
            return this;
        }

        public UrlBuilder withHash(String hash) {
            this.url.setHash(hash);
            return this;
        }

        public UrlBuilder withDateSave(LocalDate dateSave) {
            this.url.setDateSave(dateSave);
            return this;
        }

        public UrlBuilder withDateExpired(LocalDate dateExpired) {
            this.url.setDateExpired(dateExpired);
            return this;
        }

        public Url build() {
            return this.url;
        }
}
