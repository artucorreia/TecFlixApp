package br.com.tecflix_app.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.tecflix_app.data.DTO.v1.create.CreateAddressDTO;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.mapper.IMapper;
import br.com.tecflix_app.model.Address;
import br.com.tecflix_app.repository.AddressRepository;

@Service
public class AddressService {
    private final Logger LOGGER = Logger.getLogger(AddressService.class.getName());

    private final AddressRepository repository;
    private final IMapper mapper;

    public AddressService(
        AddressRepository repository,
        IMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void create(
        UserDTO user, 
        CreateAddressDTO data
    ) {
        LOGGER.info("Saving address");
        data.setUser(user);
        data.setCep(data.getCep().trim());
        data.setNumber(data.getNumber().trim());
        data.setStreet(data.getStreet().trim());
        data.setCity(data.getCity().trim());
        data.setState(data.getState().trim());
        data.setComplement(data.getComplement().trim());

        Address entity = mapper.map(data, Address.class);

        repository.save(entity);
    }
}
