package br.com.tecflix_app.mapper.implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.com.tecflix_app.mapper.Mapper;

@Service
@Primary
public class ModelMapperImpl implements Mapper {

    private static final ModelMapper modelMapper = new ModelMapper();

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
}
