package com.urlEncoder.builder.service;
import com.urlEncoder.builder.domain.*;
import com.urlEncoder.builder.repository.UrlRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.UUID;


@Service
public class UrlService {
    @Autowired
    private UrlRepository repositoryUrl;
    @Autowired
    private ClienteService clienteService;

    /// Método findAll recupera o uma lista URL cadastradas, buscando com a função disponibilizada pela ORM
    public List<Url> findAll() {
        return repositoryUrl.findAll();
    }

    /// Método findByToken recupera o uma lista URL cadastradas por um usuário, buscando com a função disponibilizada pela ORM
    public List<Url> findByToken(String token) {
        List<Url> list = repositoryUrl.findAll();
        List<Url> urlList = new ArrayList<Url>();
        list.forEach(element -> {
            if(element.getUser().equals(token)){
                urlList.add(element);
            }
        });
        return urlList;
    }
    /// Método Save salva a url Usando o Patter Builder
    public Url save(String url, ClienteBuilder user){
        Url urlBuilder = new UrlBuilder()
                .withUrl(url)
                .withUserId(user.getId())
                .withHash(generateHash(url))
                .withDateSave(LocalDate.now())
                .withDateExpired(LocalDate.now().plusDays(1)).build();
        Url urlSaved = urlBuilder.build();
        return repositoryUrl.save(urlSaved);
    }

    /// Método findById busca e recupera por ID uma URL cadastrada, buscando com a função disponibilizada pela ORM
    public Optional<Url> findById(UUID id) {
        return repositoryUrl.findById(id);
    }
    public Url getByHash(String url) {
        return repositoryUrl.findByhash(url);
    }

    /// Método generateHash gera uma HASH de 6 caracteres utilizando uma classe generica que constroi a função usando o patter Builder
    public String generateHash(String url) {
        LocalDateTime time = LocalDateTime.now();

        Patter<String> encodedUrlPatter = Patter.<String>builder()
                .fromFunction(() -> Hashing.murmur3_32()
                        .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                        .toString().substring(0, 6)).build();

        return encodedUrlPatter.get();
    }

    /// Método getHashUrl recupera o objeto URL que contai a HASH, buscando com a função disponibilizada pela ORM
    public Url getHashUrl(String hash) {
        return repositoryUrl.findByhash(hash);
    }

    /// Método getUrl retorna uma Lista de URL utilizando uma classe generica que constroi a função usando o patter Builder
    public List<Url> getUrl(String url) {
        Patter<List<Url>> urlListPatter = Patter.<List<Url>>builder()
                .fromFunction(() -> {
                    List<Url> urlList = new ArrayList<>();
                    repositoryUrl.findAll().forEach(urlObj -> {
                        if (urlObj.getUrl().equals(url)) {
                            urlList.add(urlObj);
                        }
                    });
                    return urlList;
                }).build();

        return urlListPatter.get();
    }

    /// Método delete apaga uma URL, utilizando com a função disponibilizada pela ORM
    public void delete(Url url){
        repositoryUrl.delete(url);
    }

    /// Método deletebyid apaga uma URL pelo ID, utilizando com a função disponibilizada pela ORM
    public void deletebyid(UUID id){
        repositoryUrl.deleteById(id);
    }
}
