<?php

$app['security.firewalls'] = array(
    'secured_area' => array(
        'pattern' => '^.*$',
        'anonymous' => true,
        'form' => array(
            'login_path' => '/simple-user/login',
            'check_path' => '/simple-user/login_check',
        ),
        'users' => $app->share(function ($app) {
            return $app['user.manager'];
        }),
    ),
);