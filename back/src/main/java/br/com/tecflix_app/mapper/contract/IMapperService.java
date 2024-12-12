package br.com.tecflix_app.mapper.contract;

import java.util.List;
import java.util.Set;

public interface IMapperService {
    <O, D> D map(O object, Class<D> destiny);
    <O, D> List<D> map(List<O> objects, Class<D> destiny);
    <O, D> Set<D> map(Set<O> objects, Class<D> destiny);
}
