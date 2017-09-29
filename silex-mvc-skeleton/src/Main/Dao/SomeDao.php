<?php

namespace My\Project\Main\Dao;

use Silex\Application;

class SomeDao
{

    private $db;

    function __construct(Application $app)
    {
        $this->db = $app['db'];
    }

    public function someQuery()
    {

        $this->queryBuilder = $this->db->createQueryBuilder();

        // ...

        return ['hello', 'world'];
    }


}