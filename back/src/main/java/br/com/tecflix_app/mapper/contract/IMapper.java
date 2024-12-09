package br.com.tecflix_app.mapper;

import java.util.List;

public interface IMapper {
    <O, D> D map(O object, Class<D> destiny);
    <O, D> List<D> map(List<O> objects, Class<D> destiny);
}
