INSERT 
	INTO negocios
		(ngc_id,
		ngc_sucursal,
		ngc_ganancia) 
	VALUES
		(1, 'DOT Baires Shopping',NULL),
		(2, 'Abasto Shopping',NULL),
		(3, 'Recoleta Mall',NULL),
		(4, 'Alto Palermo Shopping',NULL);

INSERT
	INTO clientes 
		(cli_id, 
		cli_nombre, 
		cli_apellido)
	VALUES 
		(1, 'Rodri','Toyama'),
		(2, 'Marcelo','Lujan'),
		(3, 'Malena','Tango'),
		(4, 'Nat', 'Oli'),
		(5, 'Federico','Rere');

INSERT
	INTO prendas 
		(prd_id,
		prd_descripcion,
		prd_precio_base,
		prd_tipo_prenda)
	VALUES 
		(1,'Camisa Celeste',10.24,'CAMISA'),
		(2,'Camisa Blanca',10.50,'CAMISA'),
		(3,'Saco Vestir',102.40,'SACO'),
		(4,'Pantalon Gabardina Beige',204.00,'PANTALON'),
		(5,'Campera Trekking Azul',250.24,'CAMPERA'),
		(6,'Tapado Negro',320.45,'TAPADO'),
		(7,'Chaqueta Jean Claro',80.34,'CHAQUETA'),
		(8,'Media Larga Blanca',5.15,'MEDIA'),
		(9,'Bufanda Lana Celeste',25.25,'BUFANDA');

INSERT 
	INTO ventas 
		(vta_id,
		tipo_venta,
		vta_fecha,
		vta_cli_id,
		vta_ngc_id) 
	VALUES
		(1,'EFECTIVO','2021-12-19 00:00:00',1,1),
		(2,'EFECTIVO','2021-12-19 00:00:00',2,2),
		(3,'TARJETA','2021-12-19 00:00:00',3,3),
		(4,'TARJETA','2021-12-19 00:00:00',5,2),
		(5,'EFECTIVO','2021-12-19 00:00:00',4,3),
		(6,'TARJETA','2021-12-19 00:00:00',3,1),
		(7,'EFECTIVO','2021-12-19 00:00:00',3,2),
		(8,'EFECTIVO','2021-12-20 00:00:00',1,1),
		(9,'TARJETA','2021-12-20 00:00:00',2,1),
		(10,'TARJETA','2021-12-20 00:00:00',4,2),
		(11,'TARJETA','2021-12-20 00:00:00',5,4),
		(12,'EFECTIVO','2021-12-20 00:00:00',3,4);

INSERT 
	INTO venta_items 
		(itm_id,
		itm_cantidad,
		itm_prd_id,
		itm_vta_id) 
	VALUES
		(1,1,1,1),
		(2,1,4,1),
		(3,1,2,2),
		(4,1,3,2),
		(5,2,8,2),
		(6,2,5,3),
		(7,1,6,4),
		(8,2,9,5),
		(9,1,7,6),
		(10,1,2,3),
		(11,2,5,7),
		(12,2,9,8),
		(13,1,5,8),
		(14,1,2,9),
		(15,1,6,10),
		(16,1,4,11),
		(17,1,7,12);

INSERT 
	INTO ventas_efectivo 
		(vta_id)
	VALUES
		(1),
		(2),
		(5),
		(7),
		(8),
		(12);

INSERT 
	INTO ventas_tarjeta 
		(vta_id,
		vtt_cantidad_cuotas,
		vtt_coeficiente)
	VALUES
		(3,3,0.01),
		(4,2,0.01),
		(6,3,0.01),
		(9,2,0.01),
		(10,4,0.01),
		(11,1,0.01);