package ar.edu.davinci.dvds20212cg1.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ar.edu.davinci.dvds20212cg1.domain.Prenda;
import ar.edu.davinci.dvds20212cg1.domain.TipoPrenda;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PrendaRepositoryTest {

	private final Logger LOGGER = LoggerFactory.getLogger(PrendaRepositoryTest.class);
	
	// Busca la instancia de la clase PrendaRepository sin tener que hacer la instancia 
	@Autowired
	private PrendaRepository prendaRepository;
	
	@Test
	void testFindAll() {
		assertNotNull(prendaRepository, "El repositorio es nulo.");
		List<Prenda> prendas = prendaRepository.findAll();
	}

	@Test
	void testFindAllByIdIterableOfID() {
		Long id = 4L;
		Prenda prenda = null;
		Optional<Prenda> prendaOpcional = prendaRepository.findById(id);
		if(prendaOpcional.isPresent()) {
			prenda = prendaOpcional.get();
			
			LOGGER.info("Prenda encontrada: " + prenda);
			assertEquals(TipoPrenda.PANTALON, prenda.getTipo());
		} else {
			
			LOGGER.info("Prenda no encontrada para el id: " + id);
			assertNull(prenda);
		}
		
		
	}

}
