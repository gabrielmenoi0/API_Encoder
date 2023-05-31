package com.urlEncoder.builder.controller;
import com.urlEncoder.builder.domain.Url;
import com.urlEncoder.builder.domain.password;
import com.urlEncoder.builder.service.ClienteService;
import com.urlEncoder.builder.service.UrlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@Api(value = "Url")
public class UrlController {

    @Autowired
    private UrlService services;
    @Autowired
    private ClienteService clienteService;

    @Cacheable("url/list")
    @GetMapping(path = "api/url/list/{token}")
    @ApiOperation(value = "Listagem de Urls")
    public ResponseEntity urls(@PathVariable(value = "token") String token){
        var resultClient = clienteService.findByToken(token);
        if(resultClient.get().getToken() ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente já cadastrado!");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(services.findAll());
        }
    }

    @PostMapping(path = "api/url/create/{token}")
    @ApiOperation(value = "Criação de urls")
    public ResponseEntity<String> create(@RequestBody password url, @PathVariable(value = "token") String token){
        if(url.getUrl().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("URL vazia!, insira uma URL");
        }else {
            var resultClient = clienteService.findByToken(token);
            if(resultClient.get().getToken() ==null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente já cadastrado!");
            }else{
                List<Url> checkExist = services.getUrl(url.getUrl().replace("\"", ""));
                if(checkExist != null && !checkExist.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.OK).body(checkExist.get(0).getHash());
                }else {
                    var result = services.save(url.getUrl().replace("\"", ""),resultClient.get());
                    if (result.getHash() != null) {
                        return ResponseEntity.status(HttpStatus.OK).body(result.getHash());
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao encurtar a URL!");
                    }
                }
            }
        }
    }

    @Cacheable("url/id")
    @GetMapping(path = "api/url/id/{id}")
    @ApiOperation(value = "Busca URLs por ID")
    public ResponseEntity<Optional<Url>> findId(@PathVariable(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(services.findById(id));
    }

    @Cacheable("url/user")
    @GetMapping(path = "api/url/user/{token}")
    @ApiOperation(value = "Busca URLs por Usuário")
    public ResponseEntity findId(@PathVariable(value = "token") String token){
        var resultClient = clienteService.findByToken(token);
        if(resultClient.get().getToken() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente já cadastrado!");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(services.findByToken(resultClient.get().getId().toString()));
        }
    }
    @Cacheable("url/hash")
    @GetMapping(path = "api/url/{hash}")
    @ApiOperation(value = "Busca e redireciona URL original pelo HASH")
    public RedirectView findHash(@PathVariable(value = "hash")String url, HttpServletResponse response){
        Url urlToRet = services.getHashUrl(url);
        String urlRedirect = urlToRet.getUrl();
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(urlRedirect); // URL para onde deseja redirecionar
        return redirectView;
    }

    @Cacheable("url/url")
    @GetMapping(path = "api/url/busca/{hash}")
    @ApiOperation(value = "Busca atravez do Hash retorna a URL")
    public ResponseEntity<String> findUrl(@PathVariable(value = "hash")String url){
        Url urlOptional = services.getByHash(url);
        return ResponseEntity.status(HttpStatus.OK).body(urlOptional.getUrl());
    }

    @DeleteMapping(path = "api/url/delete/{token}")
    @ApiOperation(value = "Excluir URL")
    public ResponseEntity delete(@RequestBody Url url,@PathVariable(value = "token") String token){
        var resultClient = clienteService.findByToken(token);
        if(resultClient.get().getToken() ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente já cadastrado!");
        }else {
            services.delete(url);
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
    }
    @DeleteMapping(path = "api/url/delete/{id}/{token}")
    @ApiOperation(value = "Excluir URL por id")
    public ResponseEntity deletebyid(@PathVariable(value = "id")UUID id,@PathVariable(value = "token") String token){
        var resultClient = clienteService.findByToken(token);
        if(resultClient.get().getToken() ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente já cadastrado!");
        }else {
            services.deletebyid(id);
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
    }
}
