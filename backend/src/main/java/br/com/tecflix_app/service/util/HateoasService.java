package br.com.tecflix_app.service.util;

import org.springframework.hateoas.RepresentationModel;

import br.com.tecflix_app.controller.contract.IController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.function.Function;

public class HateoasService {  
    public static <T extends RepresentationModel<T>, C extends IController<T, ID>, ID> T addLiks(
        T obj, 
        Class<C> controller, 
        Function<T, ID> idExtrator,
        String rel
    ) {
        obj.add(linkTo(methodOn(controller).findById(idExtrator.apply(obj))).withSelfRel());
        obj.add(linkTo(methodOn(controller).findAll()).withRel(rel));
        return obj;
    }
    
    public static <T extends RepresentationModel<T>, C extends IController<T, ID>, ID> List<T> addLiks(
        List<T> objList, 
        Class<C> controller, 
        Function<T, ID> idExtrator,
        String rel
    ) {
        return objList.stream().map(
            obj -> obj = HateoasService.addLiks(obj, controller, idExtrator, rel)
        ).toList();
    }
}