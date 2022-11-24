package br.com.Starter.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class StarterController {

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String starterGet(@PathVariable String name) {
        return "Curso api: " + name +" !";
    }


}
