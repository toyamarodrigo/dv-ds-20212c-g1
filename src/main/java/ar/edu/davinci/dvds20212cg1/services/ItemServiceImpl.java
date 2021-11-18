package ar.edu.davinci.dvds20212cg1.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ar.edu.davinci.dvds20212cg1.domain.Item;
import ar.edu.davinci.dvds20212cg1.repository.ItemRepository;
import exception.BusinessException;

public class ItemServiceImpl implements ItemService {

	private final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);

	private final ItemRepository itemRepository;

	@Autowired
	public ItemServiceImpl(final ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Override
	public Item save(Item item) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item update(Item item) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
