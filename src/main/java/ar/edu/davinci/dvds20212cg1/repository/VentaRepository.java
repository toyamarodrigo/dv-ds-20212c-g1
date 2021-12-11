package ar.edu.davinci.dvds20212cg1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.davinci.dvds20212cg1.domain.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{

}
