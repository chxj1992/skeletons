<?php

namespace My\Project\Test\Functional;

use Silex\WebTestCase;

abstract class BaseWebTestCase extends WebTestCase
{

    protected $db;
    protected $client;

    public function createApplication()
    {
        //register 3rd party services
        $app = require __DIR__ . '/../../app.php';
        //load test environment configuration
        require __DIR__ . '/../../../config/test.php';
        //dependency injection
        require __DIR__ . '/../../services.php';
        require __DIR__ . '/../../routers.php';

        $app['debug'] = true;
        $app['exception_handler']->disable();

        $this->db = $app['db'];
        $app['session.test'] = true;

        return $app;
    }

    protected function assertResponseOKAndFetchContent()
    {
        $response = $this->client->getResponse();
        $this->assertTrue($response->isOk());

        return json_decode($response->getContent());
    }

}