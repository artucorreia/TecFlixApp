package br.com.tecflix_app.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.tecflix_app.data.DTO.v1.create.CreateBackDataDTO;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.mapper.Mapper;
import br.com.tecflix_app.model.BankData;
import br.com.tecflix_app.repository.BankDataRepository;

@Service
public class BankDataService {
    private final Logger LOGGER = Logger.getLogger(BankDataService.class.getName());

    private final BankDataRepository repository;
    private final Mapper mapper;

    public BankDataService(
        BankDataRepository repository,
        Mapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void create(
        UserDTO user, 
        CreateBackDataDTO data
    ) {
        LOGGER.info("Saving bank data");
        data.setUser(user);
        data.setAgency(data.getAgency().trim());
        data.setAccountNumber(data.getAccountNumber().trim());
        data.setDv(data.getDv());

        BankData entity = mapper.map(data, BankData.class);

        repository.save(entity);
    }
}
