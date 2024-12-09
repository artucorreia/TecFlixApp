package br.com.tecflix_app.mapper.contract;

import java.util.List;

public interface IMapperService {
    <O, D> D map(O object, Class<D> destiny);
    <O, D> List<D> map(List<O> objects, Class<D> destiny);
}
