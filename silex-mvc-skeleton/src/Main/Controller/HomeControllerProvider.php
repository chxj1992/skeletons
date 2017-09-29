<?php
namespace My\Project\Main\Controller;

use Silex\Application;
use Silex\ControllerProviderInterface;


class HomeControllerProvider implements ControllerProviderInterface
{

    private $someService;

    public function __construct(Application $app)
    {
        $this->someService = $app['SomeService'];
    }

    public function connect(Application $app)
    {
        $controllers = $app['controllers_factory'];

        $controllers->get('/', $this->display())//            ->secure('ROLE_USER')
        ;

        return $controllers;
    }

    public function display()
    {
        return function (Application $app) {
            try {
                $this->someService->someMethod();
                return $app->json(['ret' => true, 'data' => 'success']);
            } catch (\Exception $e) {
                return $app->json(['ret' => false, 'data' => 'error: ' . $e->getMessage()]);
            }
        };
    }


}
