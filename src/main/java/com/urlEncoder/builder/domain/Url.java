package com.urlEncoder.builder.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Table(name = "tb_url")
public class Url implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @org.hibernate.annotations.Type(type="uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private  String url;
    public String idUser;
    private  String hash;

    public String getUser() {
        return idUser;
    }

    public void setUser(String user) {
        this.idUser = user;
    }

    public LocalDate getDateSave() {
        return dateSave;
    }

    private LocalDate dateSave;
    private LocalDate dateExpired;

    public void setDateSave(LocalDate dateSave) {
        this.dateSave = dateSave;
    }

    public LocalDate getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(LocalDate dateExpired) {
        this.dateExpired = dateExpired;
    }

    public UUID getId() {
        return id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }


    public Url(UUID id, String url, String hash, LocalDate dateSave, LocalDate dateExpired, String user) {
        this.url = url;
        this.hash = hash;
        this.dateSave = dateSave;
        this.dateExpired = dateExpired;
        this.idUser = user;
    }

    public Url() {
    }
    public Url build() {
        return this;
    }


}
