package br.com.tecflix_app.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import br.com.tecflix_app.mapper.contract.IMapperService;

@Service
@Primary
public class ModelMapperService implements IMapperService {

    private static final  ModelMapper modelMapper = new ModelMapper();

    @Override
    public <O, D> D map(O object, Class<D> destiny) {
        return modelMapper.map(object, destiny);
    }

    @Override
    public <O, D> List<D> map(List<O> objects, Class<D> destiny) {
        List<D> destinyList = new ArrayList<>();
        for (O object : objects) {
            destinyList.add(modelMapper.map(object, destiny));
        }
        return destinyList;
    }

    @Override
    public <O, D> Set<D> map(Set<O> objects, Class<D> destiny) {
        Set<D> destinyList = new HashSet<>();
        for (O object : objects) {
            destinyList.add(modelMapper.map(object, destiny));
        }
        return destinyList;
    }
}
