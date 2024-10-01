

INSERT INTO landside.warehouses(warehouse_id, seller_id, material_id, current_tons) VALUES (UUID_TO_BIN('57f7f2a0-6928-4fe8-9bb8-afc36915929f'), UUID_TO_BIN('b0fc7bc6-2ecc-4ae4-8c3b-f5d5ad3ae8f4'), UUID_TO_BIN('66f05626-82a0-800b-841d-73b9326ac71e'), 500000);
INSERT INTO landside.warehouses(warehouse_id, seller_id, material_id, current_tons) VALUES (UUID_TO_BIN('69fb338a-fb24-48d3-9933-7d16dde1fa2c'), UUID_TO_BIN('b0fc7bc6-2ecc-4ae4-8c3b-f5d5ad3ae8f4'), UUID_TO_BIN('66f05626-82a0-800b-841d-73b9326ac71e'), 500000);
INSERT INTO landside.warehouses(warehouse_id, seller_id, material_id, current_tons) VALUES (UUID_TO_BIN('d4164423-40d1-4743-a3fc-781a401e95d0'), UUID_TO_BIN('b0fc7bc6-2ecc-4ae4-8c3b-f5d5ad3ae8f4'), UUID_TO_BIN('66f05626-82a0-800b-841d-73b9326ac71e'), 400000);
INSERT INTO landside.warehouses(warehouse_id, seller_id, material_id, current_tons) VALUES (UUID_TO_BIN('48629eef-43f1-45c7-a4bb-522ce0febdeb'), UUID_TO_BIN('b0fc7bc6-2ecc-4ae4-8c3b-f5d5ad3ae8f4'), UUID_TO_BIN('66f05626-82a0-800b-841d-73b9326ac71e'), 100000);
INSERT INTO landside.warehouses(warehouse_id, seller_id, material_id, current_tons) VALUES (UUID_TO_BIN('38629eef-43f1-45c7-a4bb-522ce0febdeb'), UUID_TO_BIN('b0fc7bc6-2ecc-4ae4-8c3b-f5d5ad3ae8f4'), UUID_TO_BIN('66f05626-82a0-800b-841d-73b9326ac71e'), 200000);


INSERT INTO landside.appointments (id, warehouse_id, appointment_date, appointment_hour, material_id, truck_license_plate, status) VALUES (UUID_TO_BIN('66f2fdb0-71d8-800b-afae-98e6824af9f5'), UUID_TO_BIN('38629eef-43f1-45c7-a4bb-522ce0febdeb'), '2023-10-28', 19, UUID_TO_BIN('66f2fdb0-71d8-800b-afae-98e6824af9f5'),  'ppp-pppp-ppp', 'CONSOLIDATED');