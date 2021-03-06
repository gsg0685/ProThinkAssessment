package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.Client;
import io.swagger.model.ClientCreate;
import io.swagger.model.ClientUpdate;
import io.swagger.service.ClientService;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-08T11:42:54.708Z")

@Controller
public class ClientApiController implements ClientApi {

    private static final Logger log = LoggerFactory.getLogger(ClientApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    ClientService individaulService;

    Error error=null;
    
    @org.springframework.beans.factory.annotation.Autowired
    public ClientApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Object> createClient(@ApiParam(value = "The Client to be created" ,required=true )
    @Valid @RequestBody ClientCreate client) throws NotFoundException{
        String accept = request.getHeader("Accept");
        String str=null;
        if (accept != null && accept.contains("application/json")) {
            try {
            	if(!client.getId().isEmpty() && !client.getFirstName().isEmpty() && client.getId().length()==13) {
            		str=objectMapper.writeValueAsString(client);
                	Client ind=objectMapper.readValue(str, Client.class);
                	Client ind1=individaulService.createClient(ind);
                	return new ResponseEntity<Object>(ind1, HttpStatus.CREATED);
            	}else {
            		error=new Error();
            		error.setCode("400");
            		error.setMessage("ID is empty,Please enter valid id number or ID length is not 13 Digit");
            		error.setReason("Bad request");
            		return new ResponseEntity<Object>(error,HttpStatus.BAD_REQUEST);
            	}
            	           	
            	
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                Error e1=new Error();
                e1.setCode("500");
                e1.setMessage(e.getMessage());
                return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Object>(HttpStatus.NOT_IMPLEMENTED);
    }
    public ResponseEntity<List<Client>> listClient(@ApiParam(value = "Comma-separated properties to be provided in response") @Valid @RequestParam(value = "fields", required = false) String fields,@ApiParam(value = "Requested index for start of resources to be provided in response") @Valid @RequestParam(value = "offset", required = false) Integer offset,@ApiParam(value = "Requested number of resources to be provided in response") @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");
        List<Client> list=new ArrayList<Client>();
        if (accept != null && accept.contains("application/json")) {
            try {            	
            Iterable<Client> itr=individaulService.listClient();
            itr.forEach(list::add);
                return new ResponseEntity<List<Client>>(list, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Client>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Client>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Client> patchClient(@ApiParam(value = "Identifier of the Client",required=true) @PathVariable("id") String id,@ApiParam(value = "The Client to be updated" ,required=true )  @Valid @RequestBody ClientUpdate client) {
        String accept = request.getHeader("Accept");
        String str=null;
        if (accept != null && accept.contains("application/json")) {
            try {
            	str=objectMapper.writeValueAsString(client);
            	Client ind=objectMapper.readValue(str, Client.class);
            	Client ind1=individaulService.patchClient(ind.getId(),ind.getFirstName());
            	
                return new ResponseEntity<Client>(ind1, HttpStatus.OK);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Client>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            catch (NotFoundException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                 return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
             }
        }

        return new ResponseEntity<Client>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Object> retrieveClient(@ApiParam(value = "Identifier of the client",required=true) 
    @PathVariable("id") String id,@ApiParam(value = "Comma-separated properties to provide in response") 
    @Valid @RequestParam(value = "fields", required = false) String fields) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	Client ind=individaulService.retrieveClient(id);  
                return new ResponseEntity<Object>(ind, HttpStatus.OK);
           }catch (NotFoundException e) {
               log.error("Couldn't serialize response for content type application/json", e);
               Error e1=new Error();
               e1.setCode("404");
               e1.setMessage(e.getMessage());
                return new ResponseEntity<Object>(e1,HttpStatus.NOT_FOUND);
            }
        }
 
        return new ResponseEntity<Object>(HttpStatus.NOT_IMPLEMENTED);
    }
    

    
}
