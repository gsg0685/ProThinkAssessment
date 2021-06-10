package io.swagger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.api.NotFoundException;
import io.swagger.model.Client;
import io.swagger.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;

	public Client createClient(Client client)  {
		
		clientRepository.save(client);
		
		return client;
	}

	public Client retrieveClient(String id) throws NotFoundException {

		Client ind = clientRepository.findById(id).orElse(null);
		if (ind == null)
			throw new NotFoundException(404, "Client ID is not found, please enter valid ID");
		return ind;

	}

	
	public Iterable<Client> listClient() {

		return clientRepository.findAll();

	}

	public Client patchClient(String id, String firstName) throws NotFoundException {

		Client clientlDBObj = retrieveClient(id);
		clientlDBObj.setFirstName(firstName);
		return clientRepository.save(clientlDBObj);

	}

	
	
	
}
