<?php

namespace My\Project\Test\Functional;

class HomeActionTest extends BaseWebTestCase
{

    public function setUp()
    {
        parent::setUp();
        $this->client = $this->createClient();
    }

    public function test_home_page_response_OK()
    {
        $this->client->request('GET', '/home/');

        $content = $this->assertResponseOKAndFetchContent();
        $this->assertTrue($content->ret);
    }


}