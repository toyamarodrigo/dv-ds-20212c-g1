package ar.edu.davinci.dvds20212cg1.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.edu.davinci.dvds20212cg1.domain.Cliente;
import exception.BusinessException;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Override
	public Cliente save(Cliente cliente) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente update(Cliente cliente) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente findById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Cliente> list(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
