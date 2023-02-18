package br.unitins.resource;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import br.unitins.model.Pessoa;


@Path ("/pessoas")
public class PessoaResource {
    
    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Pessoa> test() 
    {    
    ArrayList <Pessoa>  pessoas = new ArrayList<>();
    Pessoa p1 = new Pessoa();
    p1.setNome("João Pedro");
    p1.setIdade( 20);
    p1.setSalario(200.0); 
    p1.setEndereco("Rua 33");
    p1.setProfissao("jogador de Dead cells");
    pessoas.add(p1);
    

    Pessoa p2 = new Pessoa();
    p2.setNome("Mary");
    p2.setIdade( 20);
    p2.setSalario(200000.0); 
    p2.setEndereco("Rua 459");
    p2.setProfissao("Vendedor de curso");
    pessoas.add(p2);    

        return pessoas;
    }

    @GET
    @Path("/all") 
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pessoa> getAll() {
       return Pessoa.findAll().list();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Pessoa insert(Pessoa pessoa) {
        Pessoa attachedPessoa = entityManager.merge(pessoa);
        entityManager.persist(attachedPessoa);
        return attachedPessoa;
    }
    
}
