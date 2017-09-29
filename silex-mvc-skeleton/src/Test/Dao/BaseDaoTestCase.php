<?php

namespace My\Project\Test\Dao;

class BaseDaoTestCase extends \PHPUnit_Framework_TestCase
{

    public function initApp()
    {
        //register 3rd party services
        $app = require __DIR__ . '/../../app.php';
        //load test environment configuration
        require __DIR__ . '/../../../config/test.php';
        //dependency injection
        require __DIR__ . '/../../services.php';

        return $app;
    }

}