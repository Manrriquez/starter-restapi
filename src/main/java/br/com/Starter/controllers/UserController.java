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

    @GetMapping(value = "findUser")
    @ResponseBody // REQUESTBODY = DESCRIÇÃO DA RESPOSTA
    // REQUESTPARAM RECEBE OS DADOS PARA BUSCAR
    public ResponseEntity<UserModel> findUser(@RequestParam(name = "iduser") Long idUser) {
        UserModel user = userRepository.findById(idUser).get();

        return new ResponseEntity<UserModel>(user, HttpStatus.OK);
    }

    @PutMapping(value = "edit")
    @ResponseBody // REQUESTBODY = DESCRIÇÃO DA RESPOSTA
    // REQUESTBODY RECEBE OS DADOS PARA SALVAR
    public ResponseEntity<?> editUser(@RequestBody UserModel user) {
        if(user.getId() == null) {
            return new ResponseEntity<String>("id não foi informado para att", HttpStatus.OK);

        }
        UserModel userEdit = userRepository.saveAndFlush(user);
        return new ResponseEntity<UserModel>(userEdit, HttpStatus.OK);
    }

    @GetMapping(value = "findName")
    @ResponseBody // REQUESTBODY = DESCRIÇÃO DA RESPOSTA
    // REQUESTPARAM RECEBE OS DADOS PARA BUSCAR
    public ResponseEntity<List<UserModel>> findName(@RequestParam(name = "name") String name) {
        List<UserModel> userName = userRepository.findName(name.trim().toUpperCase());

        return new ResponseEntity<List<UserModel>>(userName, HttpStatus.OK);
    }

}
