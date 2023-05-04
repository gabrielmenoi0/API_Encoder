package com.urlEncoder.builder.domain;

import java.util.UUID;

public class ClienteBuilder {

        private String token;
        private UUID id;
        private String nome;
        private String email;
        private String dateRegister;
        private String senha;

        public ClienteBuilder setToken(String token) {
            this.token = token;
            return this;
        }

        public ClienteBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public ClienteBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public ClienteBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public ClienteBuilder setDateRegister(String dateRegister) {
            this.dateRegister = dateRegister;
            return this;
        }

        public ClienteBuilder setSenha(String senha) {
            this.senha = senha;
            return this;
        }

    public String getToken() {
        return token;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDateRegister() {
        return dateRegister;
    }

    public String getSenha() {
        return senha;
    }

    public ClienteBuilder build() {
            return this;
        }

}
