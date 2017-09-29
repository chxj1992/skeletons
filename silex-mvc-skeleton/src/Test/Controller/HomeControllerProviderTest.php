<?php
namespace My\Project\Main\Controller;

use Silex\Application;


/**
 * @property \Prophecy\Prophecy\ObjectProphecy someService
 */
class HomeControllerProviderTest extends \PHPUnit_Framework_TestCase
{
    public $homeController;
    private $app;

    public function setUp()
    {
        $this->app = new Application();
        $this->someService = $this->prophesize('My\Project\Main\Service\SomeService');
        $this->app['SomeService'] = $this->someService->reveal();

        $this->homeController = new HomeControllerProvider($this->app);
    }

    public function test_home_controller_should_call_some_method_in_service()
    {
        $this->someService->someMethod()->shouldBeCalled();

        $homeAction = $this->homeController->display();
        $homeAction($this->app);
    }

}
