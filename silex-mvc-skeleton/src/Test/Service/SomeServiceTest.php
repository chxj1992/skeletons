<?php

namespace My\Project\Main\Service;

use Silex\Application;

/**
 * @property \Prophecy\Prophecy\ObjectProphecy someDao
 */
class SomeServiceTest extends \PHPUnit_Framework_TestCase
{

    private $someService;

    public function setUp()
    {
        $app = new Application();
        $this->someDao = $this->prophesize('My\Project\Main\Dao\SomeDao');
        $app['SomeDao'] = $this->someDao->reveal();

        $this->someService = new SomeService($app);
    }

    public function test_some_method_should_call_some_query_in_dao()
    {
        $this->someDao->someQuery()->shouldBeCalled();

        $this->someService->someMethod();
    }
}
