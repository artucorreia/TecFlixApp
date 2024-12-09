package br.com.tecflix_app.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.tecflix_app.data.DTO.v1.create.CreateBackDataDTO;
import br.com.tecflix_app.data.DTO.v1.response.UserDTO;
import br.com.tecflix_app.mapper.contract.IMapper;
import br.com.tecflix_app.model.BankAccountData;
import br.com.tecflix_app.repository.BankAccountDataRepository;

@Service
public class BankAccountDataService {
    private final Logger LOGGER = Logger.getLogger(BankAccountDataService.class.getName());

    private final BankAccountDataRepository repository;
    private final IMapper mapper;

    public BankAccountDataService(
        BankAccountDataRepository repository,
        IMapper mapper
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

        BankAccountData entity = mapper.map(data, BankAccountData.class);

        repository.save(entity);
    }
}
