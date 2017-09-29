<?php

/**********************************************************************
 * Dependency Injections
 **********************************************************************/

//Controllers

$app['HomeController'] = function ($app) {
    return new \My\Project\Main\Controller\HomeControllerProvider($app);
};


//Services

$app['SomeService'] = function ($app) {
    return new \My\Project\Main\Service\SomeService($app);
};


//Daos

$app['SomeDao'] = function ($app) {
    return new \My\Project\Main\Dao\SomeDao($app);
};