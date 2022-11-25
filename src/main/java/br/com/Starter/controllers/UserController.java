package br.com.Starter.controllers;


import br.com.Starter.Repositories.UserRepository;
import br.com.Starter.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String starterGet(@PathVariable String name) {
        return "Curso api: " + name +" !";
    }

    @GetMapping(value = "listAll")
    @ResponseBody //RETORNA OS DADOS PARA O CORPO DA REQUISIÇÃO
    public ResponseEntity<List<UserModel>> listUser(){
        List<UserModel> users = userRepository.findAll(); //EXECUTA A CONSULTA NO DATABASE

        //RETORNA LISTA EM JSON
        return new ResponseEntity<List<UserModel>>(users, HttpStatus.OK);
    }

    @PostMapping(value = "save")
    @ResponseBody // REQUESTBODY = DESCRIÇÃO DA RESPOSTA
    // REQUESTBODY RECEBE OS DADOS PARA SALVAR
    public ResponseEntity<UserModel> saveUser(@RequestBody UserModel user) {
       UserModel userSave = userRepository.save(user);

       return new ResponseEntity<UserModel>(userSave, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "delete")
    @ResponseBody // REQUESTBODY = DESCRIÇÃO DA RESPOSTA
    // REQUESTPARAM RECEBE OS DADOS PARA DELETAR
    public ResponseEntity<String> deleteUser(@RequestParam Long idUser) {
        userRepository.deleteById(idUser);

        return new ResponseEntity<String>("Usuario deletado com sucesso", HttpStatus.OK);
    }

}
