CREATE DATABASE landside;
GRANT ALL
    ON landside.* TO 'root'@'%';
GRANT SHOW
    DATABASES ON *.* TO 'root'@'%';
FLUSH PRIVILEGES;

CREATE DATABASE waterside;
GRANT ALL
    ON waterside.* TO 'root'@'%';
GRANT SHOW
    DATABASES ON *.* TO 'root'@'%';
FLUSH PRIVILEGES;

CREATE DATABASE warehouse;
GRANT ALL
    ON warehouse.* TO 'root'@'%';
GRANT SHOW
    DATABASES ON *.* TO 'root'@'%';
FLUSH PRIVILEGES;

