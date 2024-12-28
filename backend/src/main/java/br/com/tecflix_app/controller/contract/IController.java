package br.com.tecflix_app.controller.contract;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IController<T, ID> {
    ResponseEntity<T> findById(ID id);
    ResponseEntity<List<T>> findAll();
}
