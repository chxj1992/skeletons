<?php

namespace My\Project\Main\Dao;

use My\Project\Test\Dao\BaseDaoTestCase;

class SomeDaoTest extends BaseDaoTestCase
{

    private $app;
    private $someDao;

    public function setUp()
    {
        $this->app = $this->initApp();

        $this->someDao = $this->app['SomeDao'];
    }

    // this is more likely to be an integration test.
    public function test_for_some_query()
    {
        $data = $this->someDao->someQuery();

        $this->assertEquals(['hello', 'world'], $data);
    }


}
