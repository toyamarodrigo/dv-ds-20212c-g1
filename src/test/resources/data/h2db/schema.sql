DROP TABLE IF EXISTS negocios;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS prendas;
DROP TABLE IF EXISTS ventas;
DROP TABLE IF EXISTS venta_items;
DROP TABLE IF EXISTS ventas_efectivo;
DROP TABLE IF EXISTS ventas_tarjeta;

create table negocios (
	ngc_id BIGINT NOT NULL AUTO_INCREMENT,
	ngc_sucursal VARCHAR(255) NOT NULL,
	ngc_ganancia BIGINT DEFAULT NULL,
	PRIMARY KEY (ngc_id)
);

create TABLE clientes (
  cli_id BIGINT NOT NULL AUTO_INCREMENT,
  cli_apellido VARCHAR(255) DEFAULT NULL,
  cli_nombre VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (cli_id)
);

create TABLE prendas (
  prd_id BIGINT NOT NULL AUTO_INCREMENT,
  prd_descripcion VARCHAR(255) DEFAULT NULL,
  prd_precio_base DECIMAL(19,2) DEFAULT NULL,
  prd_tipo_prenda VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (prd_id)
);

create TABLE ventas (
  vta_id BIGINT NOT NULL AUTO_INCREMENT,
  tipo_venta VARCHAR(31) NOT NULL,
  vta_fecha DATETIME(6) DEFAULT NULL,
  vta_cli_id BIGINT DEFAULT NULL,
  vta_ngc_id BIGINT DEFAULT NULL,
  PRIMARY KEY (vta_id),
  KEY vta_cli_fk (vta_cli_id),
  CONSTRAINT vta_cli_fk FOREIGN KEY (vta_cli_id) REFERENCES clientes (cli_id),
  KEY vta_ngc_fk (vta_ngc_id),
  CONSTRAINT vta_ngc_fk FOREIGN KEY (vta_ngc_id) REFERENCES negocios (ngc_id)
);

create TABLE venta_items (
  itm_id BIGINT NOT NULL AUTO_INCREMENT,
  itm_cantidad INT DEFAULT NULL,
  itm_prd_id BIGINT DEFAULT NULL,
  itm_vta_id BIGINT NOT NULL,
  PRIMARY KEY (itm_id),
  KEY itm_vta_fk (itm_vta_id),
  CONSTRAINT itm_vta_fk FOREIGN KEY (itm_vta_id) REFERENCES ventas (vta_id),
  KEY itm_prd_fk (itm_prd_id),
  CONSTRAINT itm_prd_fk FOREIGN KEY (itm_prd_id) REFERENCES prendas (prd_id)
);

create TABLE ventas_efectivo (
  vta_id BIGINT NOT NULL,
  PRIMARY KEY (vta_id),
  CONSTRAINT vte_vta_fk FOREIGN KEY (vta_id) REFERENCES ventas (vta_id)
);

create TABLE ventas_tarjeta (
  vta_id BIGINT NOT NULL,
  vtt_cantidad_cuotas INT DEFAULT NULL,
  vtt_coeficiente  DECIMAL(2,2) DEFAULT NULL,
  PRIMARY KEY (vta_id),
  CONSTRAINT vtt_vta_fk FOREIGN KEY (vta_id) REFERENCES ventas (vta_id)
);
