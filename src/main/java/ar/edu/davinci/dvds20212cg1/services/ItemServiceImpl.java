package ar.edu.davinci.dvds20212cg1.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.edu.davinci.dvds20212cg1.domain.Item;
import ar.edu.davinci.dvds20212cg1.repository.ItemRepository;
import exception.BusinessException;

@Service
public class ItemServiceImpl implements ItemService {

	private final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

	private final ItemRepository itemRepository;

	@Autowired
	public ItemServiceImpl(final ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Override
	public Item save(Item item) throws BusinessException {
		LOGGER.debug("Guardamos el item con el id: " + item.getId());
		
		if(item.getId() == null) {
			return itemRepository.save(item);
		}
		throw new BusinessException("No se puede crear un item con un id especifico");
	}

	@Override
	public Item update(Item item) throws BusinessException {
		LOGGER.debug("Modificamos el item con el id: " + item.getId());
		
		if(item.getId() != null) {
			return itemRepository.save(item);
		}
		throw new BusinessException("No se puede modificar un item no creado");
	}

	@Override
	public void delete(Item item) {
		LOGGER.debug("Borrando el item con el id: " + item.getId());

		itemRepository.delete(item);
	}

	@Override
	public void delete(Long id) {
		LOGGER.debug("Borrando la item con el id: " + id);

		itemRepository.deleteById(id);
	}

	@Override
	public Item findById(Long id) throws BusinessException {
		LOGGER.debug("Listado de el item por id");
		
		Optional<Item> itemOptional = itemRepository.findById(id);
		if(itemOptional.isPresent()) {
			return itemOptional.get();
		}
		throw new BusinessException("No se encontro el item con id: " + id);
	}

	@Override
	public List<Item> listAll() {
		LOGGER.debug("Listado de todas las items");
		return itemRepository.findAll();
	}

	@Override
	public Page<Item> list(Pageable pageable) {
		LOGGER.debug("Listado de todas las items paginadas");

		return itemRepository.findAll(pageable);
	}

	@Override
	public long count() {
		return itemRepository.count();
	}

}
