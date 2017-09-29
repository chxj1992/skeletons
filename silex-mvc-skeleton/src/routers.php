<?php

//Request::setTrustedProxies(array('127.0.0.1'));


$app->get('/', function () use ($app) {
    return $app['twig']->render('index.html', array('user' => $app['user']));
});

$app->mount('/home', $app['HomeController']);

$app->mount('/simple-user', $userServiceProvider);


