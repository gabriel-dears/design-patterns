package design.pattern.dio.gof.service.impl;

import design.pattern.dio.gof.model.Cliente;
import design.pattern.dio.gof.model.ClienteRepository;
import design.pattern.dio.gof.model.Endereco;
import design.pattern.dio.gof.model.EnderecoRepository;
import design.pattern.dio.gof.service.ClienteService;
import design.pattern.dio.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).get();
    }

    @Override
    public void inserir(Cliente cliente) {

        preencherClienteSalvar(cliente);

    }

    private void preencherClienteSalvar(Cliente cliente) {
        Endereco endereco = enderecoRepository.findById(cliente.getEndereco().getCep()).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cliente.getEndereco().getCep());
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });

        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> optCliente;
        optCliente = clienteRepository.findById(id);
        optCliente.ifPresent(this::preencherClienteSalvar);
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
