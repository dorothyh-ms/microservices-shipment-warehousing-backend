INSERT INTO warehouse.sellers (id, name) VALUES (UUID_TO_BIN('b0fc7bc6-2ecc-4ae4-8c3b-f5d5ad3ae8f4'), 'test_seller1');
INSERT INTO warehouse.sellers(id, name) VALUES (UUID_TO_BIN('5677d31d-d338-4dcc-8b77-0aa49caafdc6'), 'test_seller2');


INSERT INTO warehouse.warehouses(uuid, seller_id, base_amount_tons, material) VALUES (UUID_TO_BIN('57f7f2a0-6928-4fe8-9bb8-afc36915929f'), UUID_TO_BIN('b0fc7bc6-2ecc-4ae4-8c3b-f5d5ad3ae8f4'), 0.0, "CEMENT");
INSERT INTO warehouse.warehouses(uuid, seller_id, base_amount_tons, material) VALUES (UUID_TO_BIN('69fb338a-fb24-48d3-9933-7d16dde1fa2c'), UUID_TO_BIN('b0fc7bc6-2ecc-4ae4-8c3b-f5d5ad3ae8f4'), 0.0, "IRON_ORE");
INSERT INTO warehouse.warehouses(uuid, seller_id, base_amount_tons, material) VALUES (UUID_TO_BIN('d4164423-40d1-4743-a3fc-781a401e95d0'), UUID_TO_BIN('b0fc7bc6-2ecc-4ae4-8c3b-f5d5ad3ae8f4'), 0.0, "PETCOKE");
INSERT INTO warehouse.warehouses(uuid, seller_id, base_amount_tons, material) VALUES (UUID_TO_BIN('48629eef-43f1-45c7-a4bb-522ce0febdeb'), UUID_TO_BIN('b0fc7bc6-2ecc-4ae4-8c3b-f5d5ad3ae8f4'), 0.0, "SLAG");
INSERT INTO warehouse.warehouses(uuid, seller_id, base_amount_tons, material) VALUES (UUID_TO_BIN('38629eef-43f1-45c7-a4bb-522ce0febdeb'), UUID_TO_BIN('b0fc7bc6-2ecc-4ae4-8c3b-f5d5ad3ae8f4'), 0.0, "GYPSUM");

INSERT INTO warehouse.warehouses(uuid, seller_id, base_amount_tons, material) VALUES (UUID_TO_BIN('916120ed-44b1-4297-a67d-485f2b181b81'), UUID_TO_BIN('5677d31d-d338-4dcc-8b77-0aa49caafdc6'), 100000, "CEMENT");
INSERT INTO warehouse.warehouses(uuid, seller_id, base_amount_tons, material) VALUES (UUID_TO_BIN('fcd65074-04b3-4c94-98fb-d949d96d18e2'), UUID_TO_BIN('5677d31d-d338-4dcc-8b77-0aa49caafdc6'), 200000, "IRON_ORE");
INSERT INTO warehouse.warehouses(uuid, seller_id, base_amount_tons, material) VALUES (UUID_TO_BIN('0dcb1a57-ccfb-43a6-9127-c0b6e094c220'), UUID_TO_BIN('5677d31d-d338-4dcc-8b77-0aa49caafdc6'), 500000, "PETCOKE");
INSERT INTO warehouse.warehouses(uuid, seller_id, base_amount_tons, material) VALUES (UUID_TO_BIN('95fdb49c-b8ac-4ca3-a3af-81f05fdedc0a'), UUID_TO_BIN('5677d31d-d338-4dcc-8b77-0aa49caafdc6'), 400000, "SLAG");
INSERT INTO warehouse.warehouses(uuid, seller_id, base_amount_tons, material) VALUES (UUID_TO_BIN('552c939b-c4f1-4118-a5fb-c9f2a0dc0244'), UUID_TO_BIN('5677d31d-d338-4dcc-8b77-0aa49caafdc6'), 300000, "GYPSUM");



INSERT INTO landside.sellers(seller_id, name) VALUES (UUID_TO_BIN('b0fc7bc6-2ecc-4ae4-8c3b-f5d5ad3ae8f4'), 'test_seller1');
INSERT INTO landside.sellers(seller_id, name) VALUES (UUID_TO_BIN('5677d31d-d338-4dcc-8b77-0aa49caafdc6'), 'test_seller2');

INSERT INTO landside.warehouses(warehouse_id, seller_id, material, current_tons, x_coord, y_coord) VALUES (UUID_TO_BIN('57f7f2a0-6928-4fe8-9bb8-afc36915929f'), UUID_TO_BIN('b0fc7bc6-2ecc-4ae4-8c3b-f5d5ad3ae8f4'), "CEMENT", 0, 0, 0);
INSERT INTO landside.warehouses(warehouse_id, seller_id, material, current_tons, x_coord, y_coord) VALUES (UUID_TO_BIN('69fb338a-fb24-48d3-9933-7d16dde1fa2c'), UUID_TO_BIN('b0fc7bc6-2ecc-4ae4-8c3b-f5d5ad3ae8f4'), "IRON_ORE", 0, 0, 200);
INSERT INTO landside.warehouses(warehouse_id, seller_id, material, current_tons, x_coord, y_coord) VALUES (UUID_TO_BIN('d4164423-40d1-4743-a3fc-781a401e95d0'), UUID_TO_BIN('b0fc7bc6-2ecc-4ae4-8c3b-f5d5ad3ae8f4'), "PETCOKE", 0, 200, 0);
INSERT INTO landside.warehouses(warehouse_id, seller_id, material, current_tons, x_coord, y_coord) VALUES (UUID_TO_BIN('48629eef-43f1-45c7-a4bb-522ce0febdeb'), UUID_TO_BIN('b0fc7bc6-2ecc-4ae4-8c3b-f5d5ad3ae8f4'), "SLAG", 0, 200, 200);
INSERT INTO landside.warehouses(warehouse_id, seller_id, material, current_tons, x_coord, y_coord) VALUES (UUID_TO_BIN('38629eef-43f1-45c7-a4bb-522ce0febdeb'), UUID_TO_BIN('b0fc7bc6-2ecc-4ae4-8c3b-f5d5ad3ae8f4'), "GYPSUM", 0, 200, 400);


INSERT INTO landside.warehouses(warehouse_id, seller_id, material, current_tons, x_coord, y_coord) VALUES (UUID_TO_BIN('916120ed-44b1-4297-a67d-485f2b181b81'), UUID_TO_BIN('5677d31d-d338-4dcc-8b77-0aa49caafdc6'),  "CEMENT", 100000, 200, 600);
INSERT INTO landside.warehouses(warehouse_id, seller_id,  material,current_tons, x_coord, y_coord) VALUES (UUID_TO_BIN('fcd65074-04b3-4c94-98fb-d949d96d18e2'), UUID_TO_BIN('5677d31d-d338-4dcc-8b77-0aa49caafdc6'),  "IRON_ORE", 200000, 400, 0);
INSERT INTO landside.warehouses(warehouse_id, seller_id,  material, current_tons, x_coord, y_coord) VALUES (UUID_TO_BIN('0dcb1a57-ccfb-43a6-9127-c0b6e094c220'), UUID_TO_BIN('5677d31d-d338-4dcc-8b77-0aa49caafdc6'),  "PETCOKE", 500000, 400, 200);
INSERT INTO landside.warehouses(warehouse_id, seller_id,  material, current_tons, x_coord, y_coord) VALUES (UUID_TO_BIN('95fdb49c-b8ac-4ca3-a3af-81f05fdedc0a'), UUID_TO_BIN('5677d31d-d338-4dcc-8b77-0aa49caafdc6'),  "SLAG", 400000, 400, 400);
INSERT INTO landside.warehouses(warehouse_id, seller_id,  material, current_tons, x_coord, y_coord) VALUES (UUID_TO_BIN('552c939b-c4f1-4118-a5fb-c9f2a0dc0244'), UUID_TO_BIN('5677d31d-d338-4dcc-8b77-0aa49caafdc6'),  "GYPSUM", 300000, 400, 600);


INSERT INTO landside.appointments (id, warehouse_id, appointment_date, appointment_hour, material, truck_license_plate, status, amount_tons) VALUES (UUID_TO_BIN('66f2fdb0-71d8-800b-afae-98e6824af9f5'), UUID_TO_BIN('38629eef-43f1-45c7-a4bb-522ce0febdeb'), '2023-10-28', 19, "CEMENT",  'ppp-pppp-ppp', 'CONSOLIDATED', 5);
INSERT INTO landside.appointments (id, warehouse_id, appointment_date, appointment_hour, material, truck_license_plate, status, amount_tons) VALUES (UUID_TO_BIN('66f2fdb0-71d8-800b-afae-98e6824af995'), UUID_TO_BIN('38629eef-43f1-45c7-a4bb-522ce0febdeb'), '2024-11-04', 19, "CEMENT",  'lll-pppp-ppp', 'CREATED', 5);


INSERT INTO waterside.shipping_orders(id, purchase_order_number, shipping_order_number, vessel_number) VALUES (UUID_TO_BIN('e942297d-3b0e-494f-86c1-af9d3b266ff4'), 'PO_000000', 'SO_00000', 'VS_00000');
INSERT INTO waterside.shipping_orders(id, purchase_order_number, shipping_order_number, vessel_number) VALUES (UUID_TO_BIN('bbf72f41-8107-48f9-99fd-dad4859faa83'), 'PO_000001', 'SO_00001', 'VS_00001');
INSERT INTO waterside.shipping_orders(id, purchase_order_number, shipping_order_number, vessel_number) VALUES (UUID_TO_BIN('c7e273e1-fd32-4fb5-97b0-a6f2d953c2de'), 'PO_000002', 'SO_00002', 'VS_00002');
INSERT INTO waterside.shipping_orders(id, purchase_order_number, shipping_order_number, vessel_number) VALUES (UUID_TO_BIN('1a6ba30b-a762-442f-bc72-2f79ca375e17'), 'PO_000003', 'SO_00003', 'VS_00003');
INSERT INTO waterside.shipping_orders(id, purchase_order_number, shipping_order_number, vessel_number) VALUES (UUID_TO_BIN('99624568-651d-437a-848e-50b717a68e77'), 'PO_000004', 'SO_00004', 'VS_00004');


INSERT INTO waterside.bunkering_operations(id, shipping_order_id, planned_date) VALUES (UUID_TO_BIN('6146d373-8a72-49eb-a70b-31c03002a2e9'), UUID_TO_BIN('e942297d-3b0e-494f-86c1-af9d3b266ff4'), '2024-10-27');
INSERT INTO waterside.bunkering_operations(id, shipping_order_id, planned_date) VALUES (UUID_TO_BIN('b72f64a7-ebde-4d8d-b74a-66cbfbd07334'), UUID_TO_BIN('bbf72f41-8107-48f9-99fd-dad4859faa83'), '2024-10-27');
INSERT INTO waterside.bunkering_operations(id, shipping_order_id, planned_date) VALUES (UUID_TO_BIN('a4f5b13d-3cc9-4fe8-af65-63743e4eb9fe '), UUID_TO_BIN('c7e273e1-fd32-4fb5-97b0-a6f2d953c2de'), '2024-10-27');
INSERT INTO waterside.bunkering_operations(id, shipping_order_id, planned_date) VALUES (UUID_TO_BIN('6454bf9d-75e5-4051-9b3b-9a945c5436f3'), UUID_TO_BIN('1a6ba30b-a762-442f-bc72-2f79ca375e17'), '2024-10-27');