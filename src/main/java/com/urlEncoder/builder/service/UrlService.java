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

    public List<Url> findAll() {
        return repositoryUrl.findAll();
    }
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
    public Url save(String url, ClienteBuilder user){
       UrlBuilder urlBuilder = new UrlBuilder()
                .withUrl(url)
                .withUserId(user.getId())
                .withHash(generateHash(url))
                .withDateSave(LocalDate.now())
                .withDateExpired(LocalDate.now().plusDays(1));
        Url urlSaved = urlBuilder.build();
        return repositoryUrl.save(urlSaved);
    }

    public Optional<Url> findById(UUID id) {
        return repositoryUrl.findById(id);
    }
    public Url getByHash(String url) {
        return repositoryUrl.findByhash(url);
    }

//    private String generateHash(String url) {
//        LocalDateTime time = LocalDateTime.now();
//        String encodedUrl = Hashing.murmur3_32()
//                .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
//                .toString().substring(0, 6);
//        return encodedUrl;
//    }
    public String generateHash(String url) {
        LocalDateTime time = LocalDateTime.now();

        Patter<String> encodedUrlPatter = Patter.<String>builder()
                .fromFunction(() -> Hashing.murmur3_32()
                        .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                        .toString().substring(0, 6))
                .build();

        return encodedUrlPatter.get();
    }
    public Url getHashUrl(String hash) {
        Url urlToRet = repositoryUrl.findByhash(hash);
        return urlToRet;
    }

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
                })
                .build();

        return urlListPatter.get();
    }
//    public List<Url> getUrl(String url) {
//        List<Url> urlList = new ArrayList<>();
//        repositoryUrl.findAll().forEach(urlObj -> {
//            if (urlObj.getUrl().equals(url)) {
//                urlList.add(urlObj);
//            }
//        });
//        return urlList;
//    }

    public void delete(Url url){
        repositoryUrl.delete(url);
    }
    public void deletebyid(UUID id){
        repositoryUrl.deleteById(id);
    }
}
