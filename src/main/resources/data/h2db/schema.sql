DROP TABLE IF EXISTS ventas_tarjeta;

DROP TABLE IF EXISTS ventas_efectivo;

DROP TABLE IF EXISTS venta_items;

DROP TABLE IF EXISTS negocios;

DROP TABLE IF EXISTS ventas;

DROP TABLE IF EXISTS prendas;

DROP TABLE IF EXISTS clientes;

create table negocios (
	ngc_id bigint NOT NULL auto_increment,
	ngc_sucursal varchar(255) DEFAULT null,
	primary key (ngc_id)
);

create TABLE clientes (
  cli_id bigint NOT NULL AUTO_INCREMENT,
  cli_apellido varchar(255) DEFAULT NULL,
  cli_nombre varchar(255) DEFAULT NULL,
  PRIMARY KEY (cli_id)
);

create TABLE prendas (
  prd_id bigint NOT NULL AUTO_INCREMENT,
  prd_descripcion varchar(255) DEFAULT NULL,
  prd_precio_base decimal(19,2) DEFAULT NULL,
  prd_tipo_prenda varchar(255) DEFAULT NULL,
  PRIMARY KEY (prd_id)
);

create TABLE ventas (
  vta_id bigint NOT NULL AUTO_INCREMENT,
  tipo_venta varchar(31) NOT NULL,
  vta_fecha datetime(6) DEFAULT NULL,
  vta_cli_id bigint DEFAULT NULL,
  vta_ngc_id bigint default null,
  PRIMARY KEY (vta_id),
  KEY vta_cli_fk (vta_cli_id),
  CONSTRAINT vta_cli_fk FOREIGN KEY (vta_cli_id) REFERENCES clientes (cli_id),
  KEY vta_ngc_fk (vta_ngc_id),
  CONSTRAINT vta_ngc_fk FOREIGN KEY (vta_ngc_id) REFERENCES negocios (ngc_id)
);

create TABLE venta_items (
  itm_id bigint NOT NULL AUTO_INCREMENT,
  itm_cantidad int DEFAULT NULL,
  itm_prd_id bigint DEFAULT NULL,
  itm_vta_id bigint NOT NULL,
  PRIMARY KEY (itm_id),
  KEY itm_vta_fk (itm_vta_id),
  CONSTRAINT itm_vta_fk FOREIGN KEY (itm_vta_id) REFERENCES ventas (vta_id),
  KEY itm_prd_fk (itm_prd_id),
  CONSTRAINT itm_prd_fk FOREIGN KEY (itm_prd_id) REFERENCES prendas (prd_id)
);

create TABLE ventas_efectivo (
  vta_id bigint NOT NULL,
  PRIMARY KEY (vta_id),
  CONSTRAINT vte_vta_fk FOREIGN KEY (vta_id) REFERENCES ventas (vta_id)
);

create TABLE ventas_tarjeta (
  vta_id bigint NOT NULL,
  vtt_cantidad_cuotas int DEFAULT NULL,
  vtt_coeficiente  decimal(2,2) DEFAULT NULL,
  PRIMARY KEY (vta_id),
  CONSTRAINT vtt_vta_fk FOREIGN KEY (vta_id) REFERENCES ventas (vta_id)
);
