<?php

ini_set('display_errors', 0);

require_once __DIR__ . '/../vendor/autoload.php';

date_default_timezone_set('UTC'); 

//register 3rd party services
$app = require __DIR__ . '/../src/app.php';
//load prod environment configuration
require __DIR__ . '/../config/prod.php';
//dependency injection
require __DIR__ . '/../src/services.php';
//controller routers
require __DIR__ . '/../src/routers.php';

$app->run();
