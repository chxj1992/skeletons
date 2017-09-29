<?php

$app['db.options'] = array(
    'driver' => 'pdo_mysql',
    'host' => 'localhost',
    'dbname' => 'db_test',
    'user' => 'user',
    'password' => 'userpass',
    'charset' => 'utf8',
);

require __DIR__ . '/security.php';
